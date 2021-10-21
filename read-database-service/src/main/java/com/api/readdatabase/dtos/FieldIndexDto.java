package com.api.readdatabase.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FieldIndexDto extends BaseDto {

	private String indexName;
	private String fieldName;
	private Integer position;
}
