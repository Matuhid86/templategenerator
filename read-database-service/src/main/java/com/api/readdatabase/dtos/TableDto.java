package com.api.readdatabase.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TableDto extends BaseDto {

	private String dataBaseName;
	private String name;
	private List<ColumnDto> columns = new ArrayList<ColumnDto>();

}
