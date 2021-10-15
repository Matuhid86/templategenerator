package com.api.readdatabase.services;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.readdatabase.dtos.DataBaseDto;
import com.api.readdatabase.entities.DataBase;
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
}
