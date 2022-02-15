package com.driver;

import java.util.List;

public class DBSearch implements com.lh.spi.ISearch {

	@Override
	public List<String> searchDoc(String keyword) {
		System.out.println("DB搜索 "+keyword);

		return null;
	}


}
