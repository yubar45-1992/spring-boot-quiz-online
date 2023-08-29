package com.example.online_quiz2.service.impl;

import com.example.online_quiz2.base.exception.BadRequestRuntimeException;
import com.example.online_quiz2.base.service.impl.BaseDTOServiceImpl;
import com.example.online_quiz2.config.UserDetailService;
import com.example.online_quiz2.domain.AnsweredExam;
import com.example.online_quiz2.dto.answered.exam.AnsweredExamDto;
import com.example.online_quiz2.dto.answered.exam.AnsweredExamResponseDto;
import com.example.online_quiz2.dto.exam.ExamDto;
import com.example.online_quiz2.dto.user.UserDto;
import com.example.online_quiz2.enumaration.ExamStatus;
import com.example.online_quiz2.mapper.AnsweredExamMapperEntityWithDto;
import com.example.online_quiz2.mapper.AnsweredExamMapperResponseDtoWithDto;
import com.example.online_quiz2.repository.AnsweredExamRepository;
import com.example.online_quiz2.service.AnsweredExamService;
import com.example.online_quiz2.service.ExamService;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CompletableFuture;

@Service
@Log4j2
@Transactional(readOnly = true)
public class AnsweredExamServiceImpl extends
        BaseDTOServiceImpl<AnsweredExamDto,
                AnsweredExam,
                Long,
                AnsweredExamMapperEntityWithDto,
                AnsweredExamRepository>
        implements AnsweredExamService {

    @Autowired
    private ExamService examService;

    @Autowired
    private AnsweredExamMapperResponseDtoWithDto answeredExamMapperResponseDtoWithDto;

    @Autowired
    private UserDetailService userDetailService;

    public AnsweredExamServiceImpl(AnsweredExamRepository repository, AnsweredExamMapperEntityWithDto mapper) {
        super(repository, mapper);
    }

    @Override // only for save
    @Transactional
    public AnsweredExamResponseDto createAnswerExam(Long examId) throws Exception {
        Optional<ExamDto> optionalExamDto = examService.findById(examId);
        if (optionalExamDto.isEmpty()) {
            throw new BadRequestRuntimeException("exam not found");
        }
        if (optionalExamDto.get().getDate().isBefore(LocalDate.now())) {
            throw new BadRequestRuntimeException("your exam date is ended");
        }
        if (!optionalExamDto.get().getDate().equals(LocalDate.now())) {
            throw new BadRequestRuntimeException("your exam date is not now");
        }

        if (optionalExamDto.get().getStartTime().isAfter(LocalTime.now())) {
            throw new BadRequestRuntimeException("your exam time not start");
        }

        if (optionalExamDto.get().getEndTime().isBefore(LocalTime.now())) {
            throw new BadRequestRuntimeException("your exam time finished");
        }

        AnsweredExamDto answeredExamDto = new AnsweredExamDto();
        answeredExamDto.setExam(optionalExamDto.get());
        answeredExamDto.setStudent(getCurrentUser());
        if (optionalExamDto.get().getEndTime().isBefore(LocalTime.now())) {
            answeredExamDto.setExamStatus(ExamStatus.DONE);
            save(answeredExamDto);
            throw new BadRequestRuntimeException("exam time is over");
        }
        answeredExamDto.setExamStatus(ExamStatus.PROGRESS);
        answeredExamDto.setTime(LocalTime.now().plusMinutes(optionalExamDto.get().getMinutesTime()));
        if (answeredExamDto.getTime().isAfter(optionalExamDto.get().getEndTime())) {
            answeredExamDto.setTime(optionalExamDto.get().getEndTime());
        }
        AnsweredExamDto answeredExamDtoTwo = save(answeredExamDto);

        multiThreadExam multiThreadExam = new multiThreadExam(answeredExamDtoTwo.getTime().getSecond(), answeredExamDtoTwo.getId());
        multiThreadExam.start();


        return answeredExamMapperResponseDtoWithDto.convertFirstDtoToSecondDto(answeredExamDtoTwo);
    }

    private UserDto getCurrentUser() {
        return userDetailService.getUserRequestDTO();
    }

    @Async("taskExecutor")
    public CompletableFuture<AnsweredExamDto> startExamUser(AnsweredExamDto answeredExamDtoTwo) throws Exception {
        log.info("exam time started");
        TimerTask timerTask = new TimerTask() {
            @SneakyThrows
            @Override
            public void run() {

                log.info("exam time ended");
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, answeredExamDtoTwo.getTime().getSecond() * 1000L);
        return CompletableFuture.completedFuture(answeredExamDtoTwo);
    }

    private class multiThreadExam extends Thread {
        int sleepSecondTime;
        Long answeredExamId;

        multiThreadExam(int sleepSecondTime, Long answeredExamId) {
            this.sleepSecondTime = sleepSecondTime;
            this.answeredExamId = answeredExamId;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(sleepSecondTime * 1000);
                Optional<AnsweredExamDto> optionalAnsweredExamDto = findById(answeredExamId);
                if (optionalAnsweredExamDto.isPresent()) {
                    optionalAnsweredExamDto.get().setExamStatus(ExamStatus.DONE);
                    try {
                        save(optionalAnsweredExamDto.get());
                    } catch (SQLIntegrityConstraintViolationException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}




