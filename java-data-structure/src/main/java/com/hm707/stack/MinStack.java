package com.hm707.stack;

import java.util.Stack;

public class MinStack {

	public static void main(String[] args) {
		MinStack s = new MinStack();
		s.push(-2);
		s.push(0);
		s.push(-3);

		s.pop();
		s.top();
		System.out.println(s.getMin());
	}

	private int min = Integer.MAX_VALUE;
	Stack<Integer> stack;

	public MinStack() {
		stack = new Stack<>();
	}

	public void push(int x) {
		if (min >= x) {
			stack.push(min);
			min = x;
		}

		stack.push(x);

	}

	public void pop() {
		if (stack.pop() == min) {
			// 如果恰好把最小数 pop 了,则取出之前"寄存"的次小数,也就成了现在的最小数
			min = stack.pop();
		}
	}

	public int top() {
		return stack.peek();
	}

	public int getMin() {
		return min;
	}
}
