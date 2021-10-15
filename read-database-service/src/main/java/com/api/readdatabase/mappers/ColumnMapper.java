package com.api.readdatabase.mappers;

import org.mapstruct.Mapper;

import com.api.readdatabase.dtos.ColumnDto;
import com.api.readdatabase.entities.Column;

@Mapper
public interface ColumnMapper extends BaseMapper<Column, ColumnDto> {

}
