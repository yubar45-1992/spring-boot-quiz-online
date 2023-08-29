  package com.example.online_quiz2.base.service;

import com.example.online_quiz2.base.dto.BaseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public interface BaseDTOService<D extends BaseDto<ID>, ID extends Serializable> {

    D save(D e) throws SQLIntegrityConstraintViolationException;

    List<D> saveAll(List<D> d) throws SQLIntegrityConstraintViolationException;

    long count();

    Optional<D> findById(ID id);

    List<D> findAll();

    void deleteById(ID id);

    Page<D> findAll(Pageable pageable);

    boolean existsById(ID id);

}
