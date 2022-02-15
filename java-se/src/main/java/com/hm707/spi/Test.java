package com.hm707.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

import com.lh.spi.ISearch;

public class Test {
	public static void main(String[] args) {
		ServiceLoader<ISearch> loader = ServiceLoader.load(ISearch.class);
		Iterator<ISearch> iterator = loader.iterator();

		while (iterator.hasNext()) {
			iterator.next().searchDoc("哈哈");
		}
	}
}
