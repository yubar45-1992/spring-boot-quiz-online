package com.example.online_quiz2.service.impl;

import com.example.online_quiz2.base.exception.BadRequestRuntimeException;
import com.example.online_quiz2.base.exception.DTONotFoundRuntimeException;
import com.example.online_quiz2.base.service.impl.BaseDTOServiceImpl;
import com.example.online_quiz2.config.UserDetailService;
import com.example.online_quiz2.domain.Course;
import com.example.online_quiz2.domain.MultipleChoiceQuestion;
import com.example.online_quiz2.domain.ScoredQuestion;
import com.example.online_quiz2.domain.User;
import com.example.online_quiz2.dto.course.CourseDto;
import com.example.online_quiz2.dto.exam.ExamDto;
import com.example.online_quiz2.dto.question.multichoicequestionanwer.MultipleChoiceQuestionAnswerSelectionDto;
import com.example.online_quiz2.dto.question.multipleChoiceQuestion.MultipleChoiceQuestionDto;
import com.example.online_quiz2.dto.question.multipleChoiceQuestion.MultipleChoiceQuestionRequestDto;
import com.example.online_quiz2.dto.question.multipleChoiceQuestion.MultipleChoiceQuestionRequestEditQuestionDto;
import com.example.online_quiz2.dto.scored.question.ScoredQuestionDto;
import com.example.online_quiz2.dto.user.UserDto;
import com.example.online_quiz2.enumaration.Role;
import com.example.online_quiz2.mapper.*;
import com.example.online_quiz2.repository.MultipleChoiceQuestionRepository;
import com.example.online_quiz2.service.*;
import com.example.online_quiz2.util.ScoreUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.validation.Valid;
import java.nio.file.AccessDeniedException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class MultipleChoiceQuestionServiceImpl extends
        BaseDTOServiceImpl<MultipleChoiceQuestionDto,
                MultipleChoiceQuestion,
                Long,
                MultipleChoiceQuestionMapperEntityWithDto,
                MultipleChoiceQuestionRepository>
        implements MultipleChoiceQuestionService {
    @Autowired
    private UserMapperEntityWithDto userMapper;

    @Autowired
    private ScoredQuestionService scoredQuestionService;

    @Autowired
    private ExamService examService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseMapperEntityWithDto courseMapper;
    @Autowired
    private ScoredQuestionMapperEntityWithDto scoredQuestionMapper;
    @Autowired
    private MultipleChoiceQuestionAnswerMapperEntityWithDto multipleChoiceQuestionAnswerMapper;

    @Autowired
    private QuestionService questionService;
    @Autowired
    private MultipleChoiceQuestionAnswerSelectionService multipleChoiceQuestionAnswerSelectionService;

    @Autowired
    private MultipleChoiceQuestionAnswerMapperRequestDtoWithDto multipleChoiceQuestionAnswerMapperRequestDtoWithDto;

    @Autowired
    private MultiChoiceQuestionMapperRequestChangeDtoWithDto multiChoiceQuestionMapperRequestChangeDtoWithDto;

    @Autowired
    private UserDetailService userDetailService;

    public MultipleChoiceQuestionServiceImpl(MultipleChoiceQuestionRepository repository, MultipleChoiceQuestionMapperEntityWithDto mapper) {
        super(repository, mapper);
    }

    @Override
    @Transactional
    public MultipleChoiceQuestionDto save(@Valid MultipleChoiceQuestionDto multipleChoiceQuestionDto)
            throws SQLIntegrityConstraintViolationException {
        ScoreUtil.checkScoreValidation(multipleChoiceQuestionDto.getScore());

        check(CollectionUtils.isEmpty(multipleChoiceQuestionDto.getSelections()), "request has not selection list of answer");
        check(multipleChoiceQuestionDto.getSelections().size() < 1, "question should be have more than one answer selection for choice");
        check(multipleChoiceQuestionDto.getSelections().parallelStream().noneMatch(MultipleChoiceQuestionAnswerSelectionDto::getAnswerCheck), "question not have true answer checked");
        check(multipleChoiceQuestionDto.getSelections().parallelStream().filter(MultipleChoiceQuestionAnswerSelectionDto::getAnswerCheck).collect(Collectors.toList()).size() > 1, "question can not have more than one true answer");

        MultipleChoiceQuestionDto multipleChoiceQuestionDtoTwo = super.save(multipleChoiceQuestionDto);
        ExamDto exam = multipleChoiceQuestionDto.getExam();
        Float score = multipleChoiceQuestionDto.getScore();

        BeanUtils.copyProperties(multipleChoiceQuestionDtoTwo, multipleChoiceQuestionDto);
        multipleChoiceQuestionDtoTwo.setExam(exam);
        multipleChoiceQuestionDtoTwo.setScore(score);
        ScoredQuestionDto scoredQuestionDto = new ScoredQuestionDto();
        scoredQuestionDto.setQuestion(multipleChoiceQuestionDtoTwo);
        scoredQuestionDto.setScore(score);
        ScoredQuestionDto scoredQuestionDtoTwo = scoredQuestionService.save(scoredQuestionDto);
        exam.getScoredQuestions().add(scoredQuestionMapper.convertDTOToEntity(scoredQuestionDtoTwo));
        examService.save(exam);
        return multipleChoiceQuestionDto;
    }
    @Override
    @Transactional
    public MultipleChoiceQuestionDto editMultipleChoiceQuestion(@Valid MultipleChoiceQuestionDto multipleChoiceQuestionDto)
            throws SQLIntegrityConstraintViolationException {
        ScoreUtil.checkScoreValidation(multipleChoiceQuestionDto.getScore());

        if (CollectionUtils.isEmpty(multipleChoiceQuestionDto.getSelections())) {
            throw new BadRequestRuntimeException("request has not selection list of answer");
        }
        if (multipleChoiceQuestionDto.getSelections().size() < 1) {
            throw new BadRequestRuntimeException("question should be have more than one answer selection for choice");
        }
        if (multipleChoiceQuestionDto.getSelections().parallelStream().noneMatch(MultipleChoiceQuestionAnswerSelectionDto::getAnswerCheck)) {
            throw new BadRequestRuntimeException("question not have true answer checked");
        }
        if (multipleChoiceQuestionDto.getSelections().parallelStream().filter(MultipleChoiceQuestionAnswerSelectionDto::getAnswerCheck).collect(Collectors.toList()).size() > 1) {
            throw new BadRequestRuntimeException("question can not have more than one true answer");
        }

        MultipleChoiceQuestionDto multipleChoiceQuestionDtoTwo = super.save(multipleChoiceQuestionDto);
        return multipleChoiceQuestionDto;
    }


    @Override
    @Transactional
    public Boolean requestSave(MultipleChoiceQuestionRequestDto multipleChoiceQuestionRequestDto) throws SQLIntegrityConstraintViolationException {
        MultipleChoiceQuestionDto multipleChoiceQuestionDto = new MultipleChoiceQuestionDto();
        BeanUtils.copyProperties(multipleChoiceQuestionRequestDto, multipleChoiceQuestionDto);

        multipleChoiceQuestionDto.setSelections(
                multipleChoiceQuestionAnswerMapperRequestDtoWithDto.convertSetSecondDtoToFirstSet(
                        (multipleChoiceQuestionRequestDto.getSelections()))
        );
        check(multipleChoiceQuestionRequestDto.getExamId() == null ||
                getUserDto() == null ||
                multipleChoiceQuestionRequestDto.getCourseId() == null, "bad request");

        check(!getUserDto().getRole().equals(Role.TEACHER), "only teacher users can use this service");

        User user = userMapper.convertDTOToEntity(getUserDto());
        multipleChoiceQuestionDto.setTeacher(user);

        Optional<CourseDto> optionalCourseDto = courseService.findById(
                multipleChoiceQuestionRequestDto.getCourseId());

        if (optionalCourseDto.isEmpty()) {
            throw new DTONotFoundRuntimeException("course not found");
        }
        CourseDto courseDto = optionalCourseDto.get();
        Course course = courseMapper.convertDTOToEntity(courseDto);



        Optional<ExamDto> optionalExamDto = examService.findById(multipleChoiceQuestionRequestDto.getExamId());
        if (optionalExamDto.isEmpty()) {
            throw new DTONotFoundRuntimeException("exam not found");
        }
        Float sumOfScores = calculateQuestionsScores(optionalExamDto.get().getScoredQuestions());
        if (sumOfScores+multipleChoiceQuestionRequestDto.getScore()>optionalExamDto.get().getScore()){
            throw new BadRequestRuntimeException("you cannot choose this score");
        }
        multipleChoiceQuestionDto.setExam(optionalExamDto.get());
        List<MultipleChoiceQuestionAnswerSelectionDto> multipleChoiceQuestionAnswerSelectionDtosSaved =
                multipleChoiceQuestionAnswerSelectionService.saveAll(
                        multipleChoiceQuestionDto.getSelections().parallelStream().collect(Collectors.toList()));

        Set<MultipleChoiceQuestionAnswerSelectionDto> multipleChoiceQuestionAnswerSelectionDtosSetSaved = multipleChoiceQuestionAnswerSelectionDtosSaved.parallelStream().collect(Collectors.toSet());
        multipleChoiceQuestionDto.setSelections(multipleChoiceQuestionAnswerSelectionDtosSetSaved);
        multipleChoiceQuestionDto.setClassType("MultipleChoiceQuestion");
        MultipleChoiceQuestionDto savedMultiChoiceQuestion = save(multipleChoiceQuestionDto);
        ScoredQuestionDto scoredQuestionDto = new ScoredQuestionDto();
        scoredQuestionDto.setQuestion(savedMultiChoiceQuestion);

        scoredQuestionDto.setScore(multipleChoiceQuestionRequestDto.getScore());
        // scoredQuestionService.save(scoredQuestionDto);
        return true;
    }

    private void check(boolean check, String message) {
        if (check) {
            throw new BadRequestRuntimeException(message);
        }
    }

    @Override
    @Transactional
    public Boolean editMultipleChoiceQuestion(MultipleChoiceQuestionRequestEditQuestionDto multipleChoiceQuestionRequestEditQuestionDto) throws SQLIntegrityConstraintViolationException, AccessDeniedException {
        check(multipleChoiceQuestionRequestEditQuestionDto.getId() == null, "id must not be null");

        Optional<MultipleChoiceQuestionDto> question = findById(multipleChoiceQuestionRequestEditQuestionDto.getId());
        check(question.isEmpty(), "there is no question with this data type");
        check(!question.get().getClassType().equals("MultipleChoiceQuestion"), "data type of question is not valid");
        MultipleChoiceQuestionDto multipleChoiceQuestionDto = multiChoiceQuestionMapperRequestChangeDtoWithDto
                .convertSecondDtoToFirstDto
                        (multipleChoiceQuestionRequestEditQuestionDto);
        multipleChoiceQuestionDto.setTeacher(userMapper.convertDTOToEntity(getUserDto()));
        save(multipleChoiceQuestionDto);
        return true;
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




