package com.api.readdatabase.mappers;

import org.mapstruct.Mapper;

import com.api.readdatabase.dtos.TableDto;
import com.api.readdatabase.entities.Table;

@Mapper
public interface TableMapper extends BaseMapper<Table, TableDto> {

}
