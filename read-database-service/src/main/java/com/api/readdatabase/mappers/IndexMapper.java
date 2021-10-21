package com.api.readdatabase.mappers;

import org.mapstruct.Mapper;

import com.api.readdatabase.dtos.IndexDto;
import com.api.readdatabase.entities.Index;

@Mapper
public interface IndexMapper extends BaseMapper<Index, IndexDto> {

}
