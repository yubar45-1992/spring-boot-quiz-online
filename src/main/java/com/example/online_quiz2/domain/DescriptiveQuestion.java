package com.example.online_quiz2.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class DescriptiveQuestion extends Question implements Serializable {
    private String answer;


}
