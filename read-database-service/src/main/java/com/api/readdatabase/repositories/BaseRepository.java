package com.api.readdatabase.repositories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.api.readdatabase.filters.FilterBase;

public abstract class BaseRepository<E> {

	protected abstract JdbcTemplate getJDBCTemplate();

	protected abstract E getEntity(ResultSet resultSet, HashMap<String, FilterBase> initializers);

	public abstract E save(E entity);

	public abstract E update(E entity);

	public abstract void delete(E entity);

	public abstract List<E> find(FilterBase filter);

	public abstract E findById(Object id);

	public abstract Integer count(FilterBase filter);

	public List<E> findAll() {
		return this.find(new FilterBase());
	}

	protected Integer save(String table, HashMap<String, Object> data) {
		String fields = "";
		String valuesByInsert = "";
		List<Object> values = new ArrayList<Object>();

		for (String field : data.keySet()) {
			if (fields != "")
				fields = fields + ", ";
			if (valuesByInsert != "")
				valuesByInsert = valuesByInsert + ", ";
			fields = fields + field;
			valuesByInsert = valuesByInsert + "?";
			values.add(data.get(field));
		}

		String query = "INSERT INTO " + table + "(" + fields + ") VALUES (" + valuesByInsert + ")";

		return this.getJDBCTemplate().update(query, values);
	}

	protected Integer update(String table, HashMap<String, Object> data, HashMap<String, Object> filters) {
		String fields = "";
		String where = "";
		List<Object> parameters = new ArrayList<Object>();

		for (String field : data.keySet()) {
			if (fields != "")
				fields = fields + ", ";
			fields = fields + field + " = ?";
			parameters.add(data.get(field));
		}

		if (filters != null && filters.size() > 0) {
			for (String filter : filters.keySet()) {
				if (where != "")
					where = where + ", ";
				where = where + filter + " = ?";
				parameters.add(filters.get(filter));
			}
		}

		if (where != "")
			where = "WHERE " + where;

		String query = "UPDATE " + table + " SET " + fields + where;

		return this.getJDBCTemplate().update(query, parameters);
	}

	protected Integer delete(String table, HashMap<String, Object> filters) {
		String where = "";
		List<Object> parameters = new ArrayList<Object>();

		if (filters != null && filters.size() > 0) {
			for (String filter : filters.keySet()) {
				if (where != "")
					where = where + ", ";
				where = where + filter + " = ?";
				parameters.add(filters.get(filter));
			}
		}

		if (where != "")
			where = "WHERE " + where;

		String query = "DELETE " + table + where;

		return this.getJDBCTemplate().update(query, parameters);
	}

	protected List<E> find(String table, HashMap<String, Object> filters, HashMap<String, FilterBase> initializers) {
		String where = "";
		List<Object> parameters = new ArrayList<Object>();

		if (filters != null && filters.size() > 0) {
			for (String filter : filters.keySet()) {
				if (where != "")
					where = where + ", ";
				where = where + filter + " = ?";
				parameters.add(filters.get(filter));
			}
		}

		if (where != "")
			where = "WHERE " + where;

		String query = "SELECT * FROM " + table + where;

		return this.getJDBCTemplate().query(query, (rs, rowNum) -> this.getEntity(rs, initializers), parameters);
	}

	protected Integer count(String table, HashMap<String, Object> filters) {
		String query = "SELECT COUNT(*) FROM " + table;

		return this.getJDBCTemplate().queryForObject(query, Integer.class);
	}
}
