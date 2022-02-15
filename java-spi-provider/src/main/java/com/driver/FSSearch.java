package com.driver;

import java.util.List;

public class FSSearch implements com.lh.spi.ISearch {

	@Override
	public List<String> searchDoc(String keyword) {
		System.out.println("文件搜索 "+keyword);

		return null;
	}


}
