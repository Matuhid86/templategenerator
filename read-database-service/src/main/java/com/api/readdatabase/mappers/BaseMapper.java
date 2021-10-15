package com.api.readdatabase.mappers;

import java.util.List;

import com.api.readdatabase.dtos.BaseDto;
import com.api.readdatabase.entities.BaseEntity;

public interface BaseMapper<E extends BaseEntity, D extends BaseDto> {

	E toEntity(D dto);

	D toDto(E entity);

	List<E> toEntities(List<D> dtos);

	List<D> toDtos(List<E> entities);
}
