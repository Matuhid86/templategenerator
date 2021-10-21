package com.api.readdatabase.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Index extends BaseEntity {

	private String dataBaseName;
	private String tableName;
	private String name;
	private Boolean isPrimaryKey;
	private Boolean isUnique;
	private List<FieldIndex> fields = new ArrayList<FieldIndex>();
}
