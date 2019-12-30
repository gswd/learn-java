package com.hm707.thread.basic;

import java.util.concurrent.Callable;

/**
 * 线程协作 : 异步结果
 */
public class FutureTest {
	public static void main(String[] args) {
		MyExecutor.testMyFuture();
	}
}

/**
 * get方法返回真正的结果，如果结果还没有计算完成，get会阻塞直到计算完成，
 * 如果调用过程发生异常，则get方法抛出调用过程中的异常。
 */
interface MyFuture<V> {
	V get() throws Exception;
}

class MyExecutor {

	static class ExecuteThread<V> extends Thread {
		private V result = null;
		private Exception exception = null;
		private boolean done = false;
		private Callable<V> task;
		private Object lock;

		public ExecuteThread(Callable<V> task, Object lock) {
			this.task = task;
			this.lock = lock;
		}

		@Override
		public void run() {
			try {
				result = task.call();
			} catch (Exception e) {
				exception = e;
			} finally {
				synchronized (lock) {
					done = true;
					lock.notifyAll();
				}
			}
		}

		public V getResult() {
			return result;
		}

		public boolean isDone() {
			return done;
		}

		public Exception getException() {
			return exception;
		}

	}

	public <V> MyFuture<V> execute(final Callable<V> task) {
		final Object lock = new Object();
		final ExecuteThread<V> thread = new ExecuteThread<>(task, lock);
		thread.start();

		MyFuture<V> future = () -> {
			synchronized (lock) {
				while (!thread.isDone()) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
					}
				}

				if (thread.getException() != null) {
					throw thread.getException();
				}

				return thread.getResult();
			}
		};

		return future;
	}

	static void testMyFuture() {
		MyExecutor executor = new MyExecutor();

		// 子任务
		Callable<Integer> subTask = new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				// ... 执行异步任务
				int millis = (int)(Math.random() * 1000);
				Thread.sleep(millis);
				return millis;
			}
		};

		MyFuture<Integer> future = executor.execute(subTask);

		try {
			// 获取异步调用的结果
			Integer result = future.get();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
