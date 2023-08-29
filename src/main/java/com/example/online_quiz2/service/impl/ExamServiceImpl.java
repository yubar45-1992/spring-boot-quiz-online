package com.example.online_quiz2.service.impl;

import com.example.online_quiz2.base.exception.BadRequestRuntimeException;
import com.example.online_quiz2.base.exception.DTONotFoundRuntimeException;
import com.example.online_quiz2.base.service.impl.BaseDTOServiceImpl;
import com.example.online_quiz2.config.UserDetailService;
import com.example.online_quiz2.domain.Course;
import com.example.online_quiz2.domain.Exam;
import com.example.online_quiz2.domain.ScoredQuestion;
import com.example.online_quiz2.domain.User;
import com.example.online_quiz2.dto.course.CourseDto;
import com.example.online_quiz2.dto.exam.CreateExamDto;
import com.example.online_quiz2.dto.exam.ExamDto;
import com.example.online_quiz2.dto.exam.ExamResponseDto;
import com.example.online_quiz2.dto.exam.ExamResponseWithOutQuestionDto;
import com.example.online_quiz2.dto.question.QuestionDto;
import com.example.online_quiz2.dto.scored.question.ScoredQuestionDto;
import com.example.online_quiz2.dto.user.UserDto;
import com.example.online_quiz2.enumaration.Role;
import com.example.online_quiz2.mapper.*;
import com.example.online_quiz2.repository.ExamRepository;
import com.example.online_quiz2.service.*;
import com.example.online_quiz2.util.ScoreUtil;
import com.example.online_quiz2.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.validation.Valid;
import java.nio.file.AccessDeniedException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ExamServiceImpl extends
        BaseDTOServiceImpl<ExamDto,
                Exam,
                Long,
                ExamMapperEntityWithDto,
                ExamRepository>
        implements ExamService {

    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;

    @Autowired
    private UserMapperEntityWithDto userMapper;

    @Autowired
    private CourseMapperEntityWithDto courseMapper;

    @Autowired
    private ExamMapperResponseDtoWithDto examMapperResponseDtoWithDto;

    @Autowired
    private ExamMapperResponseDtoWithEntity examMapperResponseDtoWithEntity;

    @Autowired
    private ScoredQuestionMapperEntityWithDto scoredQuestionMapperEntityWithDto;

    @Autowired
    private ScoredQuestionService scoredQuestionService;

    @Autowired
    private ExamMapperResponseExamDtoWithDto examMapperResponseExamDtoWithDto;


    public ExamServiceImpl(ExamRepository repository, ExamMapperEntityWithDto mapper) {
        super(repository, mapper);
    }

    @Override
    @Transactional
    public void customSave(@Valid CreateExamDto createExamDto) throws SQLIntegrityConstraintViolationException {
        Optional<CourseDto> course = courseService.findById(createExamDto.getCourseId());
        Optional<UserDto> teacher = userService.findById(getTeacherId());
        if (course.isEmpty() || !course.get().getProfessor().getId().equals(getTeacherId()) || teacher.isEmpty()) {
            throw new BadRequestRuntimeException("bad request !!!");
        }//okay
        if (course.get().getStartDate().isAfter(createExamDto.getDate())) {
            throw new BadRequestRuntimeException("exam date should be over start course date");
        }
        if (CollectionUtils.isEmpty(course.get().getStudents())) {
            throw new BadRequestRuntimeException("the course not have any student");
        }
        if (createExamDto.getDate().isBefore(LocalDate.now())) {
            throw new BadRequestRuntimeException("you can not add exam for old date");
        }
        if (createExamDto.getStartTime().isAfter(createExamDto.getEndTime())) {
            throw new BadRequestRuntimeException("end time should after start time");
        }
        ExamDto examDto = new ExamDto();
        Course course1 = courseMapper.convertDTOToEntity(course.get());
        examDto.setCourse(course1);
        User teacherTwo = userMapper.convertDTOToEntity(teacher.get());
        examDto.setTeacher(teacherTwo);
        examDto.setDate(createExamDto.getDate());
        examDto.setStartTime(createExamDto.getStartTime());
        examDto.setEndTime(createExamDto.getEndTime());
        examDto.setMinutesTime(createExamDto.getMinutesTime());
        examDto.setScore(createExamDto.getScore());
        save(examDto);
    }

    @Override
    public List<ExamResponseWithOutQuestionDto> findAllByCourseId(Long courseId, Integer pageNumber, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        List<Exam> exams = repository.findAllByCourseId(courseId, paging);
        List<ExamDto> examDtos = mapper.convertListEntityToDTOList(exams);
        if (CollectionUtils.isEmpty(exams)) {
            throw new DTONotFoundRuntimeException("exam not found");
        }
        return examMapperResponseExamDtoWithDto.convertListFirstDtoToSecondDtoList(examDtos);
    }

    @Override
    public List<ExamResponseDto> findAllByTeacherIdAndCourseId(Long courseId, Integer pageNumber, Integer pageSize, String sortBy) {
        Optional<UserDto> optionalUserDto = userService.findById(getTeacherId());
        if (optionalUserDto.isEmpty()) {
            throw new BadRequestRuntimeException("teacher not found");
        }
        if (!optionalUserDto.get().getRole().equals(Role.TEACHER)) {
            throw new BadRequestRuntimeException("user is not a teacher");
        }
        User teacher = userMapper.convertDTOToEntity(optionalUserDto.get());
        Optional<CourseDto> optionalCourseDto = courseService.findById(courseId);

        if (optionalCourseDto.isEmpty()) {
            throw new BadRequestRuntimeException("course id not find");
        }
        CourseDto courseDto = optionalCourseDto.get();
        Course course = courseMapper.convertDTOToEntity(courseDto);
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Exam> result = repository.findExamsByTeacherAndCourse(teacher, course, paging);
        if (result.hasContent()) {
            List<ExamDto> examDtos = mapper.convertListEntityToDTOList(result.getContent());
//            return examDtos.parallelStream().map(this::convertExamDtoToResponseDto).collect(Collectors.toList());

            return examMapperResponseDtoWithDto.convertListFirstDtoToSecondDtoList(examDtos);
        }
        throw new DTONotFoundRuntimeException("exam not exist");
    }

    private ExamResponseDto convertExamDtoToResponseDto(ExamDto examDto) {
        return examMapperResponseDtoWithDto.convertFirstDtoToSecondDto(examDto);
    }

    @Override
    @Transactional
    public Boolean addQuestionFromTeacherQuestionBank(Long questionId, Long examId, Float questionScore) throws AccessDeniedException, SQLIntegrityConstraintViolationException {
        ScoreUtil.checkScoreValidation(questionScore);
        Optional<QuestionDto> foundQuestion = questionService.findById(questionId);
        if (foundQuestion.isEmpty()) {
            throw new DTONotFoundRuntimeException("there is no question with this id");
        }
        Optional<ExamDto> foundExam = findById(examId);
        if (foundExam.isEmpty()) {
            throw new DTONotFoundRuntimeException("there is no exam with this id");
        }

        SecurityUtil.checkCurrentUser(foundQuestion.get().getTeacher().getId(), userDetailService);
        SecurityUtil.checkCurrentUser(foundExam.get().getTeacher().getId(), userDetailService);

        ScoredQuestionDto scoredQuestionDto = new ScoredQuestionDto();
        scoredQuestionDto.setScore(questionScore);
        scoredQuestionDto.setQuestion(foundQuestion.get());

        ScoredQuestionDto saveScoredQuestionDto = scoredQuestionService.save(scoredQuestionDto);
        ScoredQuestion savedScoredQuestion = scoredQuestionMapperEntityWithDto.convertDTOToEntity(saveScoredQuestionDto);
        foundExam.get().getScoredQuestions().add(savedScoredQuestion);
        save(foundExam.get());
        return true;
    }

    private Long getTeacherId() {
        return userDetailService.getUserRequestDTO().getId();

    }
}




