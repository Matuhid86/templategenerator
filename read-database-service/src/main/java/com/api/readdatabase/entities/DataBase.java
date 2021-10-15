package com.api.readdatabase.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DataBase extends BaseEntity {

	private String name;
	private List<Table> tables = new ArrayList<Table>();
}
