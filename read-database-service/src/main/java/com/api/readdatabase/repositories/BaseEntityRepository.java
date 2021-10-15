package com.api.readdatabase.repositories;

import java.util.HashMap;
import java.util.List;

import com.api.readdatabase.filters.FilterBase;

public abstract class BaseEntityRepository<E> extends BaseRepository<E> {

	protected abstract String getTable();

	@Override
	public E save(E entity) {
		HashMap<String, Object> data = this.getDataForSaveOrUpdate(entity);

		Integer quantityRows = this.save(this.getTable(), data);

		if (quantityRows == 1)
			return entity;
		return null;
	}

	@Override
	public E update(E entity) {
		HashMap<String, Object> data = this.getDataForSaveOrUpdate(entity);
		HashMap<String, Object> filters = this.getFiltersForUpdate(entity);

		Integer quantityRows = this.update(this.getTable(), data, filters);

		if (quantityRows == 1)
			return entity;
		return null;
	}

	@Override
	public void delete(E entity) {
		HashMap<String, Object> filters = this.getFiltersForDelete(entity);
		this.delete(this.getTable(), filters);
	}

	@Override
	public List<E> find(FilterBase filter) {
		return this.find(this.getTable(), this.getFilters(filter));
	}

	@Override
	public E findById(Object id) {
		HashMap<String, Object> filters = this.getFiltersForId(id);
		List<E> entities = this.find(this.getTable(), filters);

		if (entities != null && entities.size() == 1)
			return entities.get(0);
		return null;
	}

	@Override
	public Integer count(FilterBase filter) {
		return this.count(this.getTable(), this.getFilters(filter));
	}

	protected HashMap<String, Object> getDataForSaveOrUpdate(E entity) {
		return new HashMap<String, Object>();
	}

	protected HashMap<String, Object> getFilters(FilterBase filter) {
		return new HashMap<String, Object>();
	}

	protected HashMap<String, Object> getFiltersForId(Object id) {
		return new HashMap<String, Object>();
	}

	protected HashMap<String, Object> getFiltersForUpdate(E entity) {
		return new HashMap<String, Object>();
	}

	protected HashMap<String, Object> getFiltersForDelete(E entity) {
		return new HashMap<String, Object>();
	}
}
