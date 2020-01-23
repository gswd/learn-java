package com.hm707.thread.basic;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 停止一个线程的主要机制是中断，中断并不是强迫终止一个线程，它是一种协作机制，是给线程传递一个取消信号，但是由线程来决定如何以及何时退出
 *
 * 线程在不同状态下，调用 interrupt() 时的情况有所不同.
 *
 */
public class InterruptTest {


	public static void main(String[] args) throws InterruptedException {

		//test01();//WAITING / TIMED_WAITING 状态
		//test02();//RUN状态
		//test03(); //BLOCKED状态

		testIO();

	}

	//----------- T01 -------------
	private static class T01 extends Thread {
		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				//do something
				System.out.println("[T01] --> -_-!");
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();//收到异常后不会设置标志位 需要手动设置一次
				}
			}
		}
	}
	public static void test01() throws InterruptedException {
		T01 t01 = new T01();
		t01.start();
		TimeUnit.SECONDS.sleep(3);
		t01.interrupt();

	}

	//----------- T02 -------------
	private static class T02 extends Thread {
		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				//do something
				System.out.println("[T02] --> :)");

			}

			System.out.println("[T02] -> thread done ~~");
		}
	}
	public static void test02() throws InterruptedException {
		T02 t02 = new T02();
		t02.start();
		TimeUnit.SECONDS.sleep(1);
		t02.interrupt();

	}


	//----------- T03 -------------
	private static Object lock = new Object();

	private static class T03 extends Thread {
		@Override
		public void run() {
			synchronized (lock) {
				System.out.println("[T03] -> :(");
				while (!Thread.currentThread().isInterrupted()) {
				}
			}
			System.out.println("exit");
		}
	}
	public static void test03() throws InterruptedException {
		synchronized (lock) {
			T03 t03 = new T03();
			t03.start(); //线程t03不会结束
			Thread.sleep(1000);

			t03.interrupt();
			//t03.join();//主线程等待t03线程结束 如果去掉这，线程就会结束,因为主线程结束，释放锁，A获得锁后发现中断，然后线程结束
		}
	}


	//
	private static class T_IO extends Thread{
		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				try {
					System.out.println("[T_IO] --> test IO.");
					System.out.println(System.in.read());//该操作是不可中断的, 调用interrupt()只会设置标志位
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("[T_IO] isInterrupted() --> " + Thread.currentThread().isInterrupted());
				}
			}
			System.out.println("exit");
		}

		public void cancel() {
			try {
				System.in.close();
				System.out.println("[T_IO] --> close System.in");
			} catch (IOException e) {
				e.printStackTrace();
			}
			interrupt();
		}
	}

	public static void testIO() throws InterruptedException {
		T_IO t = new T_IO();
		t.start();
		Thread.sleep(100);

		t.cancel();
	}

}
