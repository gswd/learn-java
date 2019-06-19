package com.hm707.thread.basic;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WaitAndNotifyTest {

	public static void main(String[] args) throws InterruptedException {
		//test01();

		//test02();

		//TestProducerAndConsumer();

		//线程协作 ： 同时开始
		//atSameTimeTest();

		//线程结束 ： 使用协作对象
		//testLatch(); // 模拟 CountDownLatch

		//线程结束 ： 集合点
		assemblePointTest(); // 模拟 CyclicBarrier

	}

	// ---------- test01 ---------
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

	// ---------- test02 ---------
	static class T02Thread extends Thread {
		Object lock;

		public T02Thread(Object lock) {
			this.lock = lock;

		}

		@Override
		public void run() {
			System.out.println("[" + Thread.currentThread().getName() + "] [task] [begin] ");

			try {
				synchronized (lock) {
					System.out.println("[" + Thread.currentThread().getName() + "] lock object is : " + lock);
					lock.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("[" + Thread.currentThread().getName() + "] [task] [end]");
		}
	}

	public static void test02() throws InterruptedException {
		Object lock = new Object();
		T02Thread t1 = new T02Thread(lock);
		T02Thread t2 = new T02Thread(lock);
		//第三个线程不能被唤醒，因为使用的不是同一个锁对象
		T02Thread t3 = new T02Thread(new Object());
		t1.start();
		t2.start();
		t3.start();

		TimeUnit.SECONDS.sleep(1);

		synchronized (lock) {
			lock.notifyAll();
		}
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

	// ---------- test latch -----------
	static class MyLatch{
		private int count;

		public MyLatch(int count) {
			this.count = count;
		}

		public synchronized void await() throws InterruptedException {
			while (count > 0) {
				wait();
			}
		}

		public synchronized void countDown() {
			count--;

			if (count <= 0) {
				notifyAll();
			}
		}
	}

	static class worker implements Runnable {
		MyLatch latch;

		public worker(MyLatch latch) {
			this.latch = latch;
		}

		@Override
		public void run() {
			try {
				Thread.sleep((int)(Math.random() * 1000));
				System.out.println("[worker] ["+Thread.currentThread().getName()+"] -> work done~");
				this.latch.countDown();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static void testLatch() throws InterruptedException {
		int workerNum = 10;
		MyLatch latch = new MyLatch(workerNum);

		for (int i = 0; i < workerNum; i++) {
			Thread t = new Thread(new worker(latch));
			t.start();
		}

		latch.await();

		System.out.println("[latch] -> collect worker results");
	}


	// ---------- Assemble Point -----------

	/**
	 * 各个线程先是分头行动，然后各自到达一个集合点，在集合点需要集齐所有线程，交换数据，然后再进行下一步动作.
	 */
	static class AssemblePoint {
		/** 未到集合点的线程个数 初始值为子线程个数*/
		private int n;

		public AssemblePoint(int n) {
			this.n = n;
		}

		/**
		 * 每个线程到达集合点后将 n--
		 *   如果不为0，表示还有别的线程未到，进行等待，
		 *   如果变为0，表示自己是最后一个到的，调用notifyAll唤醒所有线程。
		 */
		public synchronized void await() throws InterruptedException {
			if (n > 0) {
				n--;
				if (n == 0) {
					notifyAll();
				} else {
					while (n != 0) {
						wait();
					}
				}
			}
		}
	}

	static class Tourist extends Thread {
		AssemblePoint ap;

		public Tourist(AssemblePoint ap) {
			this.ap = ap;
		}

		@Override
		public void run() {
			try {
				// 模拟先各自独立运行
				System.out.println("[" + Thread.currentThread().getName() + "] [start]");
				Thread.sleep((int) (Math.random() * 1000));

				// 集合
				ap.await();
				System.out.println("[" + Thread.currentThread().getName() + "] [arrived]");
				// ... 集合后执行其他操作
			} catch (InterruptedException e) {
			}
		}
	}

	private static void assemblePointTest() {
		int num = 3;
		Tourist[] threads = new Tourist[num];
		AssemblePoint ap = new AssemblePoint(num);
		for (int i = 0; i < num; i++) {
			threads[i] = new Tourist(ap);
			threads[i].start();
		}
	}
}
