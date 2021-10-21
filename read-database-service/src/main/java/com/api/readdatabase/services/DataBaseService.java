package com.api.readdatabase.services;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.readdatabase.dtos.DataBaseDto;
import com.api.readdatabase.entities.DataBase;
import com.api.readdatabase.filters.FilterColumn;
import com.api.readdatabase.filters.FilterDataBase;
import com.api.readdatabase.filters.FilterIndex;
import com.api.readdatabase.filters.FilterTable;
import com.api.readdatabase.mappers.BaseMapper;
import com.api.readdatabase.mappers.DataBaseMapper;
import com.api.readdatabase.repositories.BaseRepository;
import com.api.readdatabase.repositories.DataBaseRepository;

@Service
public class DataBaseService extends BaseService<DataBaseDto, DataBase> {

	@Autowired
	private DataBaseRepository dataBaseRepository;

	@Override
	protected BaseRepository<DataBase> getRepository() {
		return dataBaseRepository;
	}

	@Override
	protected BaseMapper<DataBase, DataBaseDto> getMapper() {
		return Mappers.getMapper(DataBaseMapper.class);
	}

	public List<DataBaseDto> getDataBasesInitializer() throws Exception {
		FilterTable filterTable = new FilterTable();
		filterTable.getInitializers().put("columns", new FilterColumn());
		filterTable.getInitializers().put("indexs", new FilterIndex());

		FilterDataBase filter = new FilterDataBase();
		filter.getInitializers().put("tables", filterTable);

		return this.find(filter);
	}

	public List<DataBaseDto> getDataBasesWithoutColumnsInitializer() throws Exception {
		FilterDataBase filter = new FilterDataBase();
		filter.getInitializers().put("tables", new FilterTable());

		return this.find(filter);
	}
}
