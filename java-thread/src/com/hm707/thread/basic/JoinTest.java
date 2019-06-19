package com.hm707.thread.basic;

public class JoinTest {

	public static void main(String[] args) throws Exception {
		//test01();


	}

	// ---------- test01 -----------
	private static void test01() throws InterruptedException {
		Runnable task = () -> {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("[test01] [task run] -> " + Thread.currentThread().getName());
		};
		for (int i = 0; i < 5; i++) {
			Thread t = new Thread(task);
			t.start();
			System.out.println("[test01] [add thread] -> " + t.getName() + " [start] & [join]");
			t.join();
		}

		System.out.println("[test01] [main] --> main thread done!");
	}


}


