package com.example.online_quiz2.service.impl;

import com.example.online_quiz2.base.service.impl.BaseDTOServiceImpl;
import com.example.online_quiz2.domain.ScoredQuestion;
import com.example.online_quiz2.dto.scored.question.ScoredQuestionDto;
import com.example.online_quiz2.mapper.ScoredQuestionMapperEntityWithDto;
import com.example.online_quiz2.repository.ScoredQuestionRepository;
import com.example.online_quiz2.service.ScoredQuestionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
@Service
public class ScoredQuestionServiceImpl extends BaseDTOServiceImpl<
        ScoredQuestionDto, ScoredQuestion, Long, ScoredQuestionMapperEntityWithDto, ScoredQuestionRepository>
        implements ScoredQuestionService {


    public ScoredQuestionServiceImpl(ScoredQuestionRepository repository, ScoredQuestionMapperEntityWithDto mapper) {
        super(repository, mapper);
    }
}
