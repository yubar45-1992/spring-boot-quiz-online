package com.example.online_quiz2.dto.question;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
public class QuestionRequestDto implements Serializable {

    @NotBlank(message = "text Question is null")

    private String questionText;

    @NotBlank(message = "title Question is null")

    private String title;

    @NotNull(message = "course Question is null")
    private Long courseId;

}
