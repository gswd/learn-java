package com.hm707.hash.applay;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
	public String[] findRestaurant(String[] list1, String[] list2) {
		String[] tempArr = new String[list1.length + list2.length];

		Map<String, Integer> map1 = new HashMap<>();
		for (int i = 0; i < list1.length; i++) {
			map1.put(list1[i], i);
		}

		int resultLength = 0;

		for (int i = 0; i < list2.length; i++) {
			Integer index = map1.get(list2[i]);
			if (index == null) {
				continue;
			}

			tempArr[i + index] = list2[i];
			resultLength++;
		}

		String[] result = new String[resultLength];
		int i = 0;
		for (String s : tempArr) {
			if (s != null)
				result[i++] = s;
		}

		return result;
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		String[] list1 = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
		String[] list2 = {"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"};
		String[] result = s.findRestaurant(list1, list2);
		Arrays.stream(result).forEach(System.out :: println);
	}
}