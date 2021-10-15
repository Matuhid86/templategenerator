package com.api.readdatabase.mappers;

import org.mapstruct.Mapper;

import com.api.readdatabase.dtos.DataBaseDto;
import com.api.readdatabase.entities.DataBase;

@Mapper
public interface DataBaseMapper extends BaseMapper<DataBase, DataBaseDto> {

}
