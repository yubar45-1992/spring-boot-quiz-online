package com.example.online_quiz2.dto.question;

import com.example.online_quiz2.base.dto.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class QuestionForAnswerResponseDto extends BaseDto<Long> {
    protected String questionText;
    protected String title;

}
