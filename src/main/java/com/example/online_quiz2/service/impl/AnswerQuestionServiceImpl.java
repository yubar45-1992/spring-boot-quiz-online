package com.example.online_quiz2.service.impl;

import com.example.online_quiz2.base.exception.BadRequestRuntimeException;
import com.example.online_quiz2.base.exception.EntityNotFoundRuntimeException;
import com.example.online_quiz2.base.service.impl.BaseDTOServiceImpl;
import com.example.online_quiz2.config.UserDetailService;
import com.example.online_quiz2.domain.AnswerQuestion;
import com.example.online_quiz2.dto.answer_question.AnswerQuestionDto;
import com.example.online_quiz2.dto.answer_question.AnswerQuestionRequestDto;
import com.example.online_quiz2.dto.answered.exam.AnsweredExamDto;
import com.example.online_quiz2.dto.question.multichoicequestionanwer.MultipleChoiceQuestionAnswerSelectionDto;
import com.example.online_quiz2.dto.question.multipleChoiceQuestion.MultipleChoiceQuestionDto;
import com.example.online_quiz2.dto.scored.question.ScoredQuestionDto;
import com.example.online_quiz2.dto.user.UserDto;
import com.example.online_quiz2.mapper.AnswerQuestionMapperEntityWithDto;
import com.example.online_quiz2.repository.AnswerQuestionRepository;
import com.example.online_quiz2.service.AnswerQuestionService;
import com.example.online_quiz2.service.AnsweredExamService;
import com.example.online_quiz2.service.MultipleChoiceQuestionService;
import com.example.online_quiz2.service.ScoredQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class AnswerQuestionServiceImpl extends
        BaseDTOServiceImpl<AnswerQuestionDto,
                AnswerQuestion,
                Long,
                AnswerQuestionMapperEntityWithDto,
                AnswerQuestionRepository>
        implements AnswerQuestionService {

    @Autowired
    MultipleChoiceQuestionService multipleChoiceQuestionService;
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private ScoredQuestionService scoredQuestionService;
    @Autowired
    private AnsweredExamService answeredExamService;


    public AnswerQuestionServiceImpl(AnswerQuestionRepository repository, AnswerQuestionMapperEntityWithDto mapper) {
        super(repository, mapper);
    }

    @Override
    @Transactional
    public Boolean answerToQuestion(AnswerQuestionRequestDto answerQuestionRequestDto) {
        Optional<ScoredQuestionDto> scoredQuestionDtoOptional = scoredQuestionService.findById(answerQuestionRequestDto.getScoredQuestionId());


        if (scoredQuestionDtoOptional.isEmpty()) {
            throw new EntityNotFoundRuntimeException("there is no scored question with this id");
        }
        Optional<AnsweredExamDto> answeredExamDtoOptional = answeredExamService.findById(answerQuestionRequestDto.getAnsweredExamId());
        if (answeredExamDtoOptional.isEmpty()) {
            throw new EntityNotFoundRuntimeException("there is no exam with this id");

        }
        if (!answeredExamDtoOptional.get().getStudent().getId().equals(getCurrentUser().getId())) {
            throw new BadRequestRuntimeException("you can not access to this exam");
        }

        if (answeredExamDtoOptional.get().getExam().getDate().isBefore(LocalDate.now())) {
            throw new BadRequestRuntimeException("exam is not started yet");
        }

        if (answeredExamDtoOptional.get().getExam().getStartTime().isAfter(LocalTime.now())) {
            throw new BadRequestRuntimeException("exam is not started yet");
        }


        if (answeredExamDtoOptional.get().getExam().getDate().isAfter(LocalDate.now())) {
            throw new BadRequestRuntimeException("time of exam is over");
        }
        if (answeredExamDtoOptional.get().getExam().getEndTime().isBefore(LocalTime.now())) {
            throw new BadRequestRuntimeException("time of exam is over");

        }


        if (answeredExamDtoOptional.get().getExam().
                getScoredQuestions().
                stream().
                findAny().
                map(scoredQuestion -> scoredQuestion.getId() == scoredQuestionDtoOptional.get().getId()).isEmpty()) {
            throw new BadRequestRuntimeException("this exam have not any scored Question with this id");
        }
        AnswerQuestionDto answerQuestionDto = new AnswerQuestionDto();

        answerQuestionDto.setAnswer(answerQuestionRequestDto.getAnswer());
        answerQuestionDto.setScoredQuestion(scoredQuestionDtoOptional.get());
        answerQuestionDto.setAnsweredExam(answeredExamDtoOptional.get());
        answerQuestionDto.setStudent(getCurrentUser());
        try {
            save(answerQuestionDto);
        } catch (SQLIntegrityConstraintViolationException e) {
            e.initCause(new BadRequestRuntimeException("You already answered this question "));
        }

        return true;
    }

    @Override
    @Transactional
    public Boolean correctTheMultipleChoiceQuestions(Long studentId, Long answeredExamId) throws SQLIntegrityConstraintViolationException {

        List<AnswerQuestion> answerQuestions = repository.findByAnsweredExamIdAndStudentId(answeredExamId, studentId);
        List<AnswerQuestionDto> answerQuestionDtos = new ArrayList<>();
        answerQuestions.forEach(answerQuestion -> {
            if (answerQuestion.getScoredQuestion().getQuestion().getClassType().equals("MultipleChoiceQuestion")) {
                Optional<MultipleChoiceQuestionDto> multipleChoiceQuestionDtoOptional = multipleChoiceQuestionService.
                        findById(answerQuestion.getScoredQuestion().getQuestion().getId());

                Optional<MultipleChoiceQuestionAnswerSelectionDto> trueAnswer = multipleChoiceQuestionDtoOptional.
                        get().
                        getSelections().
                        stream().filter(multipleChoiceQuestionAnswerSelectionDto -> multipleChoiceQuestionAnswerSelectionDto.getAnswerCheck()
                        .equals(true)).
                        findFirst();
                if (trueAnswer.isPresent()) {
                    if (answerQuestion.getAnswer().equals(trueAnswer.get().getAnswer())) {
                        answerQuestion.setTakenScore(answerQuestion.getScoredQuestion().getScore());
                    } else {

                        answerQuestion.setTakenScore(0F);

                    }
                }

                AnswerQuestionDto answerQuestionDto = mapper.convertEntityToDTO(answerQuestion);
                answerQuestionDtos.add(answerQuestionDto);

            }


        });
        saveAll(answerQuestionDtos);
        return true;

    }

    @Override
    public List<AnswerQuestionDto> getDescriptiveQuestionsAnswered(Long studentId, Long examId) {
        List<AnswerQuestion> answerQuestions = repository.findByAnsweredExamIdAndStudentId(examId, studentId);
        List<AnswerQuestion> descriptiveQuestions = answerQuestions.stream().
                filter(answerQuestion -> answerQuestion.getScoredQuestion().
                        getQuestion()
                        .getClassType().
                                equals("DescriptiveQuestion")).
                collect(Collectors.toList());

        return mapper.convertListEntityToDTOList(descriptiveQuestions);


    }

    @Override
    @Transactional
    public Boolean CorrectTheDescriptiveAnswerQuestion(Long answerQuestionId, Float score) throws SQLIntegrityConstraintViolationException {
        Optional<AnswerQuestionDto> optionalAnswerQuestionDto = findById(answerQuestionId);
        if (score > optionalAnswerQuestionDto.get().getScoredQuestion().getScore()) {
            throw new BadRequestRuntimeException("taken score cannot be greater than question score");
        }

        optionalAnswerQuestionDto.get().setTakenScore(score);
        save(optionalAnswerQuestionDto.get());
        return true;
    }

    private UserDto getCurrentUser() {
        return userDetailService.getUserRequestDTO();
    }
}




