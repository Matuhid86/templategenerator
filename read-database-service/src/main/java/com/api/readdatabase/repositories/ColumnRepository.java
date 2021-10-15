package com.api.readdatabase.repositories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.api.readdatabase.entities.Column;
import com.api.readdatabase.filters.FilterBase;
import com.api.readdatabase.filters.FilterColumn;

@Repository
@SuppressWarnings("incomplete-switch")
public class ColumnRepository extends BaseRepository<Column> {

	@Value("${com.api.database}")
	private com.api.readdatabase.enums.DataBase database;

	@Value("${com.api.database.mysql.queryColumns}")
	private String queryColumnsMySQL;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	protected JdbcTemplate getJDBCTemplate() {
		return this.jdbcTemplate;
	}

	@Override
	protected Column getEntity(ResultSet resultSet) {
		return null;
	}

	@Override
	public Column save(Column entity) {
		return null;
	}

	@Override
	public Column update(Column entity) {
		return null;
	}

	@Override
	public void delete(Column entity) {
	}

	@Override
	public List<Column> find(FilterBase filterBase) {
		String query = null;

		switch (this.database) {
		case MYSQL:
			query = this.queryColumnsMySQL;
		}

		if (query != null) {
			if (filterBase != null && filterBase instanceof FilterColumn) {
				FilterColumn filter = (FilterColumn) filterBase;

				if (filter.getTable() != null)
					query = query.replace("?1", filter.getTable());
				if (filter.getDataBase() != null)
					query = query.replace("?2", filter.getDataBase());
			}

			return this.getJDBCTemplate().query(query, (rs, rowNum) -> this.getEntity(rs, filterBase));
		}

		return new ArrayList<Column>();
	}

	@Override
	public Column findById(Object id) {
		return null;
	}

	@Override
	public Integer count(FilterBase filter) {
		return this.find(filter).size();
	}

	private Column getEntity(ResultSet resultSet, FilterBase filterBase) {
		Column entity = new Column();

		try {
			entity.setName(resultSet.getString(1));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			entity.setType(resultSet.getString(2));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			entity.setIsNullable(!resultSet.getString(3).equals("NO"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			entity.setKey(resultSet.getString(4));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			entity.setDefaultValue(resultSet.getString(5));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			entity.setExtra(resultSet.getString(6));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (filterBase != null && filterBase instanceof FilterColumn) {
			FilterColumn filter = (FilterColumn) filterBase;

			entity.setDataBaseName(filter.getDataBase());
			entity.setTableName(filter.getTable());
		}

		return entity;
	}
}
