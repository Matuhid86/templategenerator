package com.api.readdatabase.services;

import java.util.ArrayList;
import java.util.List;

import com.api.readdatabase.dtos.BaseDto;
import com.api.readdatabase.entities.BaseEntity;
import com.api.readdatabase.filters.FilterBase;
import com.api.readdatabase.mappers.BaseMapper;
import com.api.readdatabase.repositories.BaseRepository;

public abstract class BaseService<D extends BaseDto, E extends BaseEntity> {

	protected abstract BaseRepository<E> getRepository();

	protected abstract BaseMapper<E, D> getMapper();

	protected void validateSave(D dto) throws Exception {
	}

	protected void validateDelete(D dto) throws Exception {
	}

	public List<D> find(FilterBase filter) throws Exception {
		List<E> entities = this.getRepository().find(filter);

		if (entities != null && entities.size() > 0)
			return this.getMapper().toDtos(entities);

		return new ArrayList<D>();
	}

	public List<D> findAll() throws Exception {
		List<E> entities = this.getRepository().findAll();

		if (entities != null && entities.size() > 0)
			return this.getMapper().toDtos(entities);

		return new ArrayList<D>();
	}

	public D findOne(Object id) throws Exception {
		E entity = this.getRepository().findById(id);

		if (entity != null)
			return this.getMapper().toDto(entity);

		return null;
	}

	public void save(D dto) throws Exception {
		this.validateSave(dto);

		this.getRepository().save(this.getMapper().toEntity(dto));
	}

	public void delete(Object id) throws Exception {
		E entity = this.getRepository().findById(id);

		this.validateDelete(this.getMapper().toDto(entity));

		this.delete(entity);
	}
}
