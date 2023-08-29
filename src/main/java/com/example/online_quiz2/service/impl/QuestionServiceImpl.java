package com.example.online_quiz2.service.impl;

import com.example.online_quiz2.base.exception.BadRequestRuntimeException;
import com.example.online_quiz2.base.service.impl.BaseDTOServiceImpl;
import com.example.online_quiz2.config.UserDetailService;
import com.example.online_quiz2.domain.Question;
import com.example.online_quiz2.dto.question.QuestionDto;
import com.example.online_quiz2.dto.question.QuestionResponseDto;
import com.example.online_quiz2.mapper.QuestionMapperEntityWithDto;
import com.example.online_quiz2.mapper.QuestionMapperResponseDtoWithDto;
import com.example.online_quiz2.repository.QuestionRepository;
import com.example.online_quiz2.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class QuestionServiceImpl extends
        BaseDTOServiceImpl<QuestionDto,
                Question,
                Long,
                QuestionMapperEntityWithDto,
                QuestionRepository>
        implements QuestionService {


    @Autowired
    private QuestionMapperResponseDtoWithDto questionMapperResponseDtoWithDto;

    @Autowired
    private UserDetailService userDetailService;


    public QuestionServiceImpl(QuestionRepository repository, QuestionMapperEntityWithDto mapper) {
        super(repository, mapper);
    }


    @Override
    public List<QuestionResponseDto> findByCourse(Long courseId) {
        List<Question> questions = repository.findAllByTeacherId(getTeacherId());
        if (questions.isEmpty()) {
            throw new BadRequestRuntimeException("not find");
        }
        List<QuestionDto> questionDtos = mapper.convertListEntityToDTOList(questions);
        return questionMapperResponseDtoWithDto.convertListFirstDtoToSecondDtoList(questionDtos);
    }

    private Long getTeacherId() {
        return userDetailService.getUserRequestDTO().getId();
    }


}




