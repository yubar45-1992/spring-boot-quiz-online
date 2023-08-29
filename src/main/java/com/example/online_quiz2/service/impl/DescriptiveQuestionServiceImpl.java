package com.example.online_quiz2.service.impl;

import com.example.online_quiz2.base.exception.BadRequestRuntimeException;
import com.example.online_quiz2.base.exception.DTONotFoundRuntimeException;
import com.example.online_quiz2.base.service.impl.BaseDTOServiceImpl;
import com.example.online_quiz2.config.UserDetailService;
import com.example.online_quiz2.domain.Course;
import com.example.online_quiz2.domain.DescriptiveQuestion;
import com.example.online_quiz2.domain.ScoredQuestion;
import com.example.online_quiz2.domain.User;
import com.example.online_quiz2.dto.course.CourseDto;
import com.example.online_quiz2.dto.exam.ExamDto;
import com.example.online_quiz2.dto.question.DescriptiveQuestionDto;
import com.example.online_quiz2.dto.question.DescriptiveQuestionRequestDto;
import com.example.online_quiz2.dto.scored.question.ScoredQuestionDto;
import com.example.online_quiz2.dto.user.UserDto;
import com.example.online_quiz2.enumaration.Role;
import com.example.online_quiz2.mapper.*;
import com.example.online_quiz2.repository.DescriptiveQuestionRepository;
import com.example.online_quiz2.service.*;
import com.example.online_quiz2.util.ScoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Transactional(readOnly = true)
public class DescriptiveQuestionServiceImpl
        extends
        BaseDTOServiceImpl<DescriptiveQuestionDto,
                DescriptiveQuestion,
                Long,
                DescriptiveQuestionMapperEntityWithDto,
                DescriptiveQuestionRepository>
        implements DescriptiveQuestionService {

    @Autowired
    private ExamService examService;

    @Autowired
    private ScoredQuestionService scoredQuestionService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseMapperEntityWithDto courseMapper;
    @Autowired
    private UserMapperEntityWithDto userMapper;
    @Autowired
    private ScoredQuestionMapperEntityWithDto scoredQuestionMapper;

    @Autowired
    private DescriptiveQuestionMapperResponseDtoWithDto questionMapperResponseDtoWithDto;

    @Autowired
    private DescriptiveQuestionMapperRequestDtoWithDto descriptiveQuestionMapperRequestDtoWithDto;

    @Autowired
    private UserMapperResponseDtoWithDto userMapperResponseDtoWithDto;

    @Autowired
    private UserDetailService userDetailService;

    public DescriptiveQuestionServiceImpl(DescriptiveQuestionRepository repository, DescriptiveQuestionMapperEntityWithDto mapper) {
        super(repository, mapper);
    }

    @Override
    @Transactional
    public DescriptiveQuestionDto save(@Valid DescriptiveQuestionDto descriptiveQuestionDto) throws SQLIntegrityConstraintViolationException {
        ScoreUtil.checkScoreValidation(descriptiveQuestionDto.getScore());
        descriptiveQuestionDto.setClassType("DescriptiveQuestion");
        DescriptiveQuestionDto descriptiveQuestion = super.save(descriptiveQuestionDto);
        ScoredQuestionDto scoredQuestionDto = new ScoredQuestionDto();
        scoredQuestionDto.setQuestion(descriptiveQuestion);
        scoredQuestionDto.setScore(descriptiveQuestionDto.getScore());
        ScoredQuestionDto scoredQuestionDtoTwo = scoredQuestionService.save(scoredQuestionDto);
        ScoredQuestion scoredQuestion = scoredQuestionMapper.convertDTOToEntity(scoredQuestionDtoTwo);
        descriptiveQuestionDto.getExamDto().getScoredQuestions().add(scoredQuestion);
        examService.save(descriptiveQuestionDto.getExamDto());
        return descriptiveQuestion;
    }

    @Override
    @Transactional
    public Boolean requestSave(@Valid DescriptiveQuestionRequestDto descriptiveQuestionRequestDto)
            throws SQLIntegrityConstraintViolationException {
        DescriptiveQuestionDto descriptiveQuestionDto = descriptiveQuestionMapperRequestDtoWithDto.convertSecondDtoToFirstDto(descriptiveQuestionRequestDto);
        check(descriptiveQuestionRequestDto.getExamId() == null ||
                getUserDto() == null ||
                descriptiveQuestionRequestDto.getCourseId() == null, "bad request");

        check(!getUserDto().getRole().equals(Role.TEACHER), "only teachers can use this service");

        User user = userMapper.convertDTOToEntity(
                getUserDto()
        );
        descriptiveQuestionDto.setTeacher(user);

        Optional<CourseDto> optionalCourseDto = courseService.findById(descriptiveQuestionRequestDto.getCourseId());
        if (optionalCourseDto.isEmpty()) {
            throw new DTONotFoundRuntimeException("course not found");
        }
        CourseDto courseDto = optionalCourseDto.get();
        Course course = courseMapper.convertDTOToEntity(courseDto);

        Optional<ExamDto> optionalExamDto = examService.findById(descriptiveQuestionRequestDto.getExamId());
        if (optionalExamDto.isEmpty()) {
            throw new DTONotFoundRuntimeException("exam not found");
        }
        Float sumOfScores = calculateQuestionsScores(optionalExamDto.get().getScoredQuestions());
        if (sumOfScores+descriptiveQuestionRequestDto.getScore()>optionalExamDto.get().getScore()){
            throw new BadRequestRuntimeException("you cannot choose this score");
        }
        ExamDto examDto = optionalExamDto.get();
        descriptiveQuestionDto.setExamDto(examDto);
        save(descriptiveQuestionDto);
        return true;
    }

    private void check(boolean role, String message) {
        if (role) {
            throw new BadRequestRuntimeException(message);
        }
    }

    private UserDto getUserDto() {
        return userDetailService.getUserRequestDTO();
    }
    private Float calculateQuestionsScores(Collection<ScoredQuestion> scoredQuestions){
        AtomicReference<Float> sumOfScores = new AtomicReference<>(0f);
        scoredQuestions.forEach(scoredQuestion -> {
            sumOfScores.updateAndGet(v -> v + scoredQuestion.getScore());
        });

        return sumOfScores.get();
    }
}




