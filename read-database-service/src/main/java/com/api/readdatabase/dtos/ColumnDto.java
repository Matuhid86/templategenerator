package com.api.readdatabase.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ColumnDto extends BaseDto {

	private String dataBaseName;
	private String tableName;
	private String name;
	private String type;
	private Boolean isNullable;
	private String key;
	private String defaultValue;
	private String extra;
}
