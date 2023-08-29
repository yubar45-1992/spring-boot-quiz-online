package com.example.online_quiz2.base.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Setter
@Getter
@NoArgsConstructor
public abstract class BaseEntity<ID extends Serializable>
        implements Serializable {


    private static final long serialVersionUID = 7015334937884726130L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    protected ID id;
    @Version
    protected Integer version;

    public BaseEntity(Integer version) {
        this.version = version;
    }
}
