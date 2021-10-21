package com.api.readdatabase.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FieldIndex extends BaseEntity {

	private String indexName;
	private String fieldName;
	private Integer position;
}
