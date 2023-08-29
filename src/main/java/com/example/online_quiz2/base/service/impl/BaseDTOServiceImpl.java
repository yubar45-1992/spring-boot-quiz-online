package com.example.online_quiz2.base.service.impl;

import com.example.online_quiz2.base.domain.BaseEntity;
import com.example.online_quiz2.base.dto.BaseDto;
import com.example.online_quiz2.base.mapper.BaseMapperEntityWithDto;
import com.example.online_quiz2.base.repository.BaseEntityRepository;
import com.example.online_quiz2.base.service.BaseDTOService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;


@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BaseDTOServiceImpl<D extends BaseDto<ID>,
        E extends BaseEntity<ID>,
        ID extends Serializable,
        M extends BaseMapperEntityWithDto<E, D, ID>,
        R extends BaseEntityRepository<E, ID>>
        implements BaseDTOService<D, ID> {

    protected final R repository;
    protected final M mapper;

    @Override
    @Transactional
    public D save(D d) throws SQLIntegrityConstraintViolationException {
        E e = mapper.convertDTOToEntity(d);
        return mapper.convertEntityToDTO(repository.save(e));
    }

    @Override
    public List<D> saveAll(List<D> d) throws SQLIntegrityConstraintViolationException {
        List<E> setE = mapper.convertListDTOToEntityList(d);
        return mapper.convertListEntityToDTOList(repository.saveAll(setE));
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public Optional<D> findById(ID id) {
        return repository.findById(id).map(mapper::convertEntityToDTO);
    }

    @Override
    public List<D> findAll() {
        return mapper.convertListEntityToDTOList(repository.findAll());
    }

    @Override
    @Transactional
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public Page<D> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::convertEntityToDTO);
    }

    @Override
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }
}
