package com.api.readdatabase.filters;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FilterTable extends FilterBase {

	private String name;
	private String dataBase;
}
