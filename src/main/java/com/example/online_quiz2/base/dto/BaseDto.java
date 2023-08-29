package com.example.online_quiz2.base.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDto<ID extends Serializable> implements Serializable {

    private static final long serialVersionUID = -3971996706276330342L;


    private ID id;

    private Integer version;
}
