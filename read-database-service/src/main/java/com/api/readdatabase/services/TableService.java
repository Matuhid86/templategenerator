package com.api.readdatabase.services;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.readdatabase.dtos.TableDto;
import com.api.readdatabase.entities.Table;
import com.api.readdatabase.filters.FilterColumn;
import com.api.readdatabase.filters.FilterIndex;
import com.api.readdatabase.filters.FilterTable;
import com.api.readdatabase.mappers.BaseMapper;
import com.api.readdatabase.mappers.TableMapper;
import com.api.readdatabase.repositories.BaseRepository;
import com.api.readdatabase.repositories.TableRepository;

@Service
public class TableService extends BaseService<TableDto, Table> {

	@Autowired
	private TableRepository tableRepository;

	@Override
	protected BaseRepository<Table> getRepository() {
		return tableRepository;
	}

	@Override
	protected BaseMapper<Table, TableDto> getMapper() {
		return Mappers.getMapper(TableMapper.class);
	}

	public List<TableDto> getByDataBase(String databaseName) throws Exception {
		FilterTable filter = new FilterTable();
		filter.getInitializers().put("columns", new FilterColumn());
		filter.getInitializers().put("indexs", new FilterIndex());
		filter.setDataBase(databaseName);

		return this.find(filter);
	}
}
