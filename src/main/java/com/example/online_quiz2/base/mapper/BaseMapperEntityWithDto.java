package com.example.online_quiz2.base.mapper;

import com.example.online_quiz2.base.domain.BaseEntity;
import com.example.online_quiz2.base.dto.BaseDto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface BaseMapperEntityWithDto<E extends BaseEntity<ID>, D extends BaseDto<ID>, ID extends Serializable> {

    E convertDTOToEntity(D d);

    D convertEntityToDTO(E e);

    List<E> convertListDTOToEntityList(List<D> dtoList);

    List<D> convertListEntityToDTOList(List<E> entityList);

    Set<E> convertSetDTOToEntitySet(Set<D> dtoSet);

    Set<D> convertSetEntityToDTOSet(Set<E> entitySet);

}
