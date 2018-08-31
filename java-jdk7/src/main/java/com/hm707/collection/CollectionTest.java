package com.hm707.collection;

import java.util.ArrayList;
import java.util.Iterator;

public class CollectionTest {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>(1);
		list.add(1);
		list.add(2);
		list.add(3);

		System.out.println(list);
		removeAll(list);
		System.out.println(list);
	}

	public static void removeAll(ArrayList<Integer> list){
		Iterator<Integer> it = list.iterator();
		while(it.hasNext()){
			System.out.println("===> " + it.next());
			it.remove();
		}
	}
}
