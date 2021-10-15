package com.api.readdatabase.services;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.readdatabase.dtos.ColumnDto;
import com.api.readdatabase.entities.Column;
import com.api.readdatabase.filters.FilterColumn;
import com.api.readdatabase.mappers.BaseMapper;
import com.api.readdatabase.mappers.ColumnMapper;
import com.api.readdatabase.repositories.BaseRepository;
import com.api.readdatabase.repositories.ColumnRepository;

@Service
public class ColumnService extends BaseService<ColumnDto, Column> {

	@Autowired
	private ColumnRepository columnRepository;

	@Override
	protected BaseRepository<Column> getRepository() {
		return columnRepository;
	}

	@Override
	protected BaseMapper<Column, ColumnDto> getMapper() {
		return Mappers.getMapper(ColumnMapper.class);
	}

	public List<ColumnDto> getByTable(String databaseName, String tableName) throws Exception {
		FilterColumn filter = new FilterColumn();
		filter.setDataBase(databaseName);
		filter.setTable(tableName);

		return this.find(filter);
	}
}
