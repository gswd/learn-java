package com.hm707.queue;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class OpenLock {
	Set<String> deadendSet;
	Set<String> visitedSet = new HashSet<>();
	public int open(String[] deadends, String target) {

		int unLockStep = 0;

		deadendSet = toSet(deadends);

		if (deadendSet.contains(target) || deadendSet.contains("0000")) {
			return -1;
		}


		Queue<String> queue = new LinkedList<>();
		queue.offer("0000");
		visitedSet.add("0000");


		while (!queue.isEmpty()) {
			unLockStep++;
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				String node = queue.poll();
				if(evolution(node, queue, target)){
					return unLockStep;
				}
			}

		}


		return -1;
	}

	private boolean evolution(String node, Queue<String> queue, String target) {

		char[] elementArray = node.toCharArray();
		for (int i = 0; i < elementArray.length; i++) {
			int currNum = toInt(elementArray[i]);
			//加一
			int afterNum = (currNum + 1) % 10;

			//char[] c1 = new char[elementArray.length];
			//System.arraycopy(elementArray, 0, c1, 0, elementArray.length);
			//c1[i] = (char)(afterNum + '0');


			StringBuilder sb = new StringBuilder(node);
			sb.setCharAt(i, (char)(afterNum + '0'));
			String n1 = sb.toString();

			if (!deadendSet.contains(n1) && !visitedSet.contains(n1)) {
				queue.offer(n1);
				visitedSet.add(n1);
				if (n1.equals(target)) {
					return true;
				}
			}


			//减一
			afterNum = ((currNum - 1) + 10) % 10;
			char[] c2 = new char[elementArray.length];
			System.arraycopy(elementArray, 0, c2, 0, elementArray.length);
			c2[i] = (char)(afterNum + '0');
			String n2 = new String(c2);

			if (!deadendSet.contains(n2) && !visitedSet.contains(n2)) {
				queue.offer(n2);
				visitedSet.add(n2);

				if (n2.equals(target)) {
					return true;
				}
			}

		}
		return false;
	}

	private Set<String> toSet(String[] deadends) {
		Set<String> set = new HashSet<>((int)(deadends.length / .75f + 1));
		for (String s : deadends) {
			set.add(s);
		}
		return set;
	}

	private int toInt(char c) {
		return (int)(c) - '0';
	}

	public static void main(String[] args) {
		OpenLock ol = new OpenLock();

		String[] dead = {"8887","8889","8878","8898","8788","8988","7888","9888"};
		int step = ol.open(dead, "8888");
		System.out.println(step);
	}
}
