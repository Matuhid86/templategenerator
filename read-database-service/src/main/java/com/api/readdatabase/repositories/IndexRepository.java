package com.api.readdatabase.repositories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.api.readdatabase.entities.FieldIndex;
import com.api.readdatabase.entities.Index;
import com.api.readdatabase.filters.FilterBase;
import com.api.readdatabase.filters.FilterIndex;

@Repository
@SuppressWarnings("incomplete-switch")
public class IndexRepository extends BaseRepository<Index> {

	@Value("${com.api.database}")
	private com.api.readdatabase.enums.DataBase database;

	@Value("${com.api.database.mysql.queryIndexs}")
	private String queryIndexsMySQL;

	@Value("${com.api.database.sqlite.queryIndexs}")
	private String queryIndexsSQLITE;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	protected JdbcTemplate getJDBCTemplate() {
		return this.jdbcTemplate;
	}

	@Override
	protected Index getEntity(ResultSet resultSet, HashMap<String, FilterBase> initializers) {
		return null;
	}

	@Override
	public Index save(Index entity) {
		return null;
	}

	@Override
	public Index update(Index entity) {
		return null;
	}

	@Override
	public void delete(Index entity) {
	}

	@Override
	public List<Index> find(FilterBase filterBase) {
		String query = null;

		switch (this.database) {
		case MYSQL:
			query = this.queryIndexsMySQL;
			break;
		case SQLITE:
			query = this.queryIndexsSQLITE;
			break;
		}

		if (query != null) {
			if (filterBase != null && filterBase instanceof FilterIndex) {
				FilterIndex filter = (FilterIndex) filterBase;

				if (filter.getTable() != null)
					query = query.replace("?1", filter.getTable());
				if (filter.getDataBase() != null)
					query = query.replace("?2", filter.getDataBase());
			}

			return this.getOrderedIndexs(
					this.getJDBCTemplate().query(query, (rs, rowNum) -> this.getEntity(rs, filterBase)));
		}

		return new ArrayList<Index>();
	}

	@Override
	public Index findById(Object id) {
		return null;
	}

	@Override
	public Integer count(FilterBase filter) {
		return this.find(filter).size();
	}

	private List<Index> getOrderedIndexs(List<Index> indexs) {
		List<Index> orderedIndexs = new ArrayList<Index>();
		HashMap<String, Index> orderedIndexsByName = new HashMap<String, Index>();

		for (Index index : indexs) {
			if (orderedIndexsByName.containsKey(index.getName()))
				orderedIndexsByName.get(index.getName()).getFields().add(index.getFields().get(0));
			else
				orderedIndexsByName.put(index.getName(), index);
		}

		for (String indexName : orderedIndexsByName.keySet())
			orderedIndexs.add(orderedIndexsByName.get(indexName));

		return orderedIndexs;
	}

	private Index getEntity(ResultSet resultSet, FilterBase filterBase) {
		Index entity = new Index();

		try {
			switch (this.database) {
			case MYSQL:
				entity.setName(resultSet.getString(3));
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			switch (this.database) {
			case MYSQL:
				entity.setIsPrimaryKey(resultSet.getString(3).equals("PRIMARY"));
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			switch (this.database) {
			case MYSQL:
				entity.setIsUnique(resultSet.getString(3).equals("0"));
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			FieldIndex fieldIndex = new FieldIndex();
			fieldIndex.setIndexName(entity.getName());

			switch (this.database) {
			case MYSQL:
				fieldIndex.setFieldName(resultSet.getString(5));
				fieldIndex.setPosition(resultSet.getInt(4));
				entity.getFields().add(fieldIndex);
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (filterBase != null && filterBase instanceof FilterIndex) {
			FilterIndex filter = (FilterIndex) filterBase;

			entity.setDataBaseName(filter.getDataBase());
			entity.setTableName(filter.getTable());
		}

		return entity;
	}
}
