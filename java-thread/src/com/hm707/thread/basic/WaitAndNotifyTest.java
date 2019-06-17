package com.hm707.thread.basic;

public class WaitAndNotifyTest {

	public static void main(String[] args) throws InterruptedException {
		test01();
	}

	private static class T01 extends Thread {
		private volatile boolean fire = false;

		@Override
		public void run() {
			try {
				synchronized (this) {
					while (!fire) {
						wait();
					}
				}
				System.out.println("fired");
			} catch (InterruptedException e) {
			}
		}

		public synchronized void fire() {
			this.fire = true;
			notify();
		}
	}

	public static void test01() throws InterruptedException {
		T01 t01 = new T01();
		t01.start();
		Thread.sleep(1000);
		System.out.println("fire");
		t01.fire();
	}

}
