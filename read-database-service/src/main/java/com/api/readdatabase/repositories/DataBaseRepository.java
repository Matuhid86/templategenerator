package com.api.readdatabase.repositories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.api.readdatabase.entities.DataBase;
import com.api.readdatabase.entities.Table;
import com.api.readdatabase.filters.FilterBase;
import com.api.readdatabase.filters.FilterTable;

@Repository
@SuppressWarnings("incomplete-switch")
public class DataBaseRepository extends BaseRepository<DataBase> {

	@Value("${com.api.database}")
	private com.api.readdatabase.enums.DataBase database;

	@Value("${com.api.database.mysql.queryDatabases}")
	private String queryDataBasesMySQL;

	@Autowired
	private TableRepository tableRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	protected JdbcTemplate getJDBCTemplate() {
		return this.jdbcTemplate;
	}

	@Override
	protected DataBase getEntity(ResultSet resultSet) {
		DataBase entity = new DataBase();

		try {
			entity.setName(resultSet.getString(1));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		entity.setTables(this.getTables(entity.getName()));

		return entity;
	}

	@Override
	public DataBase save(DataBase entity) {
		return null;
	}

	@Override
	public DataBase update(DataBase entity) {
		return null;
	}

	@Override
	public void delete(DataBase entity) {
	}

	@Override
	public List<DataBase> find(FilterBase filter) {
		String query = null;

		switch (this.database) {
		case MYSQL:
			query = this.queryDataBasesMySQL;
		}

		if (query != null)
			return this.getJDBCTemplate().query(query, (rs, rowNum) -> this.getEntity(rs));
		return new ArrayList<DataBase>();
	}

	@Override
	public DataBase findById(Object id) {
		return null;
	}

	@Override
	public Integer count(FilterBase filter) {
		return this.find(filter).size();
	}

	private List<Table> getTables(String dataBaseName) {
		FilterTable filter = new FilterTable();
		filter.setDataBase(dataBaseName);

		return this.tableRepository.find(filter);
	}
}
