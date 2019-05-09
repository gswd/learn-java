package com.hm707.hash.applay;

import java.util.HashMap;

class Isomorphic {
	public boolean isIsomorphic(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}

		HashMap<Character, Character> hashMapS = new HashMap<>();
		HashMap<Character, Character> hashMapT = new HashMap<>();

		for (int i = 0; i < s.length(); i++) {
			if (hashMapS.containsKey(s.charAt(i))) {
				if (hashMapS.get(s.charAt(i)) != t.charAt(i)) {
					return false;
				}
			} else {
				if (hashMapT.containsKey(t.charAt(i))) {
					return false;
				}
				hashMapS.put(s.charAt(i), t.charAt(i));
				hashMapT.put(t.charAt(i), s.charAt(i));
			}
		}

		return true;
	}

	public static void main(String[] args) {
		Isomorphic s = new Isomorphic();
		System.out.println(s.isIsomorphic("aabe", "ccdf"));
	}
}