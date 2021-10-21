package com.api.readdatabase.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IndexDto extends BaseDto {

	private String dataBaseName;
	private String tableName;
	private String name;
	private Boolean isPrimaryKey;
	private Boolean isUnique;
	private List<FieldIndexDto> fields = new ArrayList<FieldIndexDto>();
}
