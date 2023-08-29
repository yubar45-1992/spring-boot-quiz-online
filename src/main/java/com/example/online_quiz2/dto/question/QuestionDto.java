package com.example.online_quiz2.dto.question;

import com.example.online_quiz2.base.dto.BaseDto;
import com.example.online_quiz2.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
public class QuestionDto extends BaseDto<Long> {

    @NotBlank(message = "text Question is null")

    protected String questionText;

    @NotBlank(message = "title Question is null")

    protected String title;

    @NotNull(message = "teacher Question is null")
    protected User teacher;


    protected String classType;


}
