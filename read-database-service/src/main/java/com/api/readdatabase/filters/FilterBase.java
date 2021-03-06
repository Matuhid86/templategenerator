package com.api.readdatabase.filters;

import java.util.HashMap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FilterBase {

	private HashMap<String, FilterBase> initializers = new HashMap<String, FilterBase>();

}
