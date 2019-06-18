package com.hm707.thread.basic;

import java.util.ArrayDeque;
import java.util.Queue;

public class WaitAndNotifyTest {

	public static void main(String[] args) throws InterruptedException {
		//test01();

		//TestProducerAndConsumer();

		atSameTimeTest();
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

	// ---------- producer & Consumer ---------

	static class MyBlockingQueue<E> {
		private Queue<E> queue = null;
		private int limit;

		public MyBlockingQueue(int limit) {
			this.limit = limit;
			queue = new ArrayDeque<>(limit);
		}

		public synchronized void put(E e) throws InterruptedException {
			while (queue.size() == limit) {
				wait();
			}
			queue.add(e);
			notifyAll();
		}

		public synchronized E take() throws InterruptedException {
			while (queue.isEmpty()) {
				wait();
			}
			E e = queue.poll();
			notifyAll();
			return e;
		}
	}

	static class Producer extends Thread {
		MyBlockingQueue<String> queue;

		public Producer(MyBlockingQueue<String> queue) {
			this.queue = queue;
		}

		@Override
		public void run() {
			int num = 0;
			try {
				while (true) {
					String task = String.valueOf(num);
					queue.put(task);
					System.out.println("produce task " + task);
					num++;
					Thread.sleep((int)(Math.random() * 100));
				}
			} catch (InterruptedException e) {
			}
		}
	}

	static class Consumer extends Thread {
		MyBlockingQueue<String> queue;

		public Consumer(MyBlockingQueue<String> queue) {
			this.queue = queue;
		}

		@Override
		public void run() {
			try {
				while (true) {
					String task = queue.take();
					System.out.println("handle task " + task);
					Thread.sleep((int)(Math.random() * 100));
				}
			} catch (InterruptedException e) {
			}
		}
	}

	public static void TestProducerAndConsumer() {
		MyBlockingQueue<String> queue = new MyBlockingQueue<>(10);
		new Producer(queue).start();
		new Consumer(queue).start();
	}


	// ---------- 同时开始 -----------
	static class FireFlag {
		private volatile boolean fired = false;

		public synchronized void waitForFire() throws InterruptedException {
			while (!fired) {
				wait();
			}
		}

		public synchronized void fire() {
			this.fired = true;
			notifyAll();
		}
	}

	static class Racer extends Thread {
		FireFlag fireFlag;

		public Racer(FireFlag fireFlag) {
			this.fireFlag = fireFlag;
		}

		@Override
		public void run() {
			try {
				this.fireFlag.waitForFire();
				System.out.println("start run "
						+ Thread.currentThread().getName());
			} catch (InterruptedException e) {
			}
		}
	}

	/**
	 * 这里，启动了10个子线程，每个子线程启动后等待fire信号，主线程调用fire()后各个子线程才开始执行后续操作。
	 */
	static void atSameTimeTest() throws InterruptedException {
		int num = 10;
		FireFlag fireFlag = new FireFlag();
		Thread[] racers = new Thread[num];
		for (int i = 0; i < num; i++) {
			racers[i] = new Racer(fireFlag);
			racers[i].start();
		}
		System.out.println("[atSameTimeTest] -> ready...");
		Thread.sleep(3000);
		System.out.println("[atSameTimeTest] -> go!");
		fireFlag.fire();
	}

}
