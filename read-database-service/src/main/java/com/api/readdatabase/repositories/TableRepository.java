package com.api.readdatabase.repositories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.api.readdatabase.entities.Column;
import com.api.readdatabase.entities.Index;
import com.api.readdatabase.entities.Table;
import com.api.readdatabase.filters.FilterBase;
import com.api.readdatabase.filters.FilterColumn;
import com.api.readdatabase.filters.FilterIndex;
import com.api.readdatabase.filters.FilterTable;

@Repository
@SuppressWarnings("incomplete-switch")
public class TableRepository extends BaseRepository<Table> {

	@Value("${com.api.database}")
	private com.api.readdatabase.enums.DataBase database;

	@Value("${com.api.database.mysql.queryTables}")
	private String queryTablesMySQL;

	@Value("${com.api.database.sqlite.queryTables}")
	private String queryTablesSQLITE;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ColumnRepository columnRepository;

	@Autowired
	private IndexRepository indexRepository;

	@Override
	protected JdbcTemplate getJDBCTemplate() {
		return this.jdbcTemplate;
	}

	@Override
	protected Table getEntity(ResultSet resultSet, HashMap<String, FilterBase> initializers) {
		return null;
	}

	@Override
	public Table save(Table entity) {
		return null;
	}

	@Override
	public Table update(Table entity) {
		return null;
	}

	@Override
	public void delete(Table entity) {
	}

	@Override
	public List<Table> find(FilterBase filterBase) {
		String query = null;

		switch (this.database) {
		case MYSQL:
			query = this.queryTablesMySQL;
			break;
		case SQLITE:
			query = this.queryTablesSQLITE;
			break;
		}

		if (query != null) {
			if (filterBase != null && filterBase instanceof FilterTable) {
				FilterTable filter = (FilterTable) filterBase;

				if (filter.getDataBase() != null)
					query = query.replace("?", filter.getDataBase());
			}

			return this.getJDBCTemplate().query(query, (rs, rowNum) -> this.getEntity(rs, filterBase));
		}

		return new ArrayList<Table>();
	}

	@Override
	public Table findById(Object id) {
		return null;
	}

	@Override
	public Integer count(FilterBase filter) {
		return this.find(filter).size();
	}

	private Table getEntity(ResultSet resultSet, FilterBase filterBase) {
		Table entity = new Table();

		try {
			entity.setName(resultSet.getString(1));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (filterBase != null && filterBase instanceof FilterTable) {
			FilterTable filter = (FilterTable) filterBase;

			entity.setDataBaseName(filter.getDataBase());
		}

		if (filterBase.getInitializers().containsKey("columns"))
			entity.setColumns(this.getColumns(entity.getName(), entity.getDataBaseName(),
					filterBase.getInitializers().get("columns")));

		if (filterBase.getInitializers().containsKey("indexs"))
			entity.setIndexs(this.getIndexs(entity.getName(), entity.getDataBaseName(),
					filterBase.getInitializers().get("indexs")));

		return entity;
	}

	private List<Column> getColumns(String tableName, String dataBaseName, FilterBase filterBase) {
		FilterColumn filter = new FilterColumn();

		if (filterBase != null && filterBase instanceof FilterColumn)
			filter = (FilterColumn) filterBase;

		filter.setDataBase(dataBaseName);
		filter.setTable(tableName);

		return this.columnRepository.find(filter);
	}

	private List<Index> getIndexs(String tableName, String dataBaseName, FilterBase filterBase) {
		FilterIndex filter = new FilterIndex();

		if (filterBase != null && filterBase instanceof FilterIndex)
			filter = (FilterIndex) filterBase;

		filter.setDataBase(dataBaseName);
		filter.setTable(tableName);

		return this.indexRepository.find(filter);
	}
}
