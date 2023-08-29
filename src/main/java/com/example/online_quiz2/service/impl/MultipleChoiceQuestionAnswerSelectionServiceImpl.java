package com.example.online_quiz2.service.impl;

import com.example.online_quiz2.base.service.impl.BaseDTOServiceImpl;
import com.example.online_quiz2.domain.MultipleChoiceQuestionAnswerSelection;
import com.example.online_quiz2.dto.question.multichoicequestionanwer.MultipleChoiceQuestionAnswerSelectionDto;
import com.example.online_quiz2.mapper.MultipleChoiceQuestionAnswerMapperEntityWithDto;
import com.example.online_quiz2.repository.MultipleChoiceQuestionAnswerSelectionRepository;
import com.example.online_quiz2.service.MultipleChoiceQuestionAnswerSelectionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MultipleChoiceQuestionAnswerSelectionServiceImpl extends
        BaseDTOServiceImpl<MultipleChoiceQuestionAnswerSelectionDto,
                MultipleChoiceQuestionAnswerSelection,
                Long,
                MultipleChoiceQuestionAnswerMapperEntityWithDto,
                MultipleChoiceQuestionAnswerSelectionRepository>
        implements MultipleChoiceQuestionAnswerSelectionService {


    public MultipleChoiceQuestionAnswerSelectionServiceImpl(MultipleChoiceQuestionAnswerSelectionRepository repository, MultipleChoiceQuestionAnswerMapperEntityWithDto mapper) {
        super(repository, mapper);
    }
}




