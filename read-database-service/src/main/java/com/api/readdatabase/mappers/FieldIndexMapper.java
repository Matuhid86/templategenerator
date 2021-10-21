package com.api.readdatabase.mappers;

import org.mapstruct.Mapper;

import com.api.readdatabase.dtos.FieldIndexDto;
import com.api.readdatabase.entities.FieldIndex;

@Mapper
public interface FieldIndexMapper extends BaseMapper<FieldIndex, FieldIndexDto> {

}
