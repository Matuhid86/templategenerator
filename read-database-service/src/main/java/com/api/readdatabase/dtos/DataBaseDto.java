package com.api.readdatabase.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DataBaseDto extends BaseDto {

	private String name;
	private List<TableDto> tables = new ArrayList<TableDto>();
}
