package com.api.readdatabase.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Table extends BaseEntity {

	private String dataBaseName;
	private String name;
	private List<Column> columns = new ArrayList<Column>();
}
