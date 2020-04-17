package netty.src;

import io.netty.util.concurrent.DefaultEventExecutor;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.Promise;

public class PromiseTest01 {


	public static void main(String[] args) {
		EventExecutor executor = new DefaultEventExecutor();
		Promise<Integer> promise = new DefaultPromise<>(executor);

		promise.addListener(future -> {
			if (future.isSuccess()) {
				System.out.println("任务结束，结果：" + future.get());
			} else {
				System.out.println("任务失败，异常：" + future.cause());
			}

		}).addListener(new GenericFutureListener<Future<? super Integer>>() {
			@Override
			public void operationComplete(Future future) throws Exception {
				System.out.println("任务结束，balabala...");
				executor.shutdownGracefully();
			}
		});

		executor.submit(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			promise.setFailure(new RuntimeException());
			promise.setSuccess(666);

		});

		try {
			System.out.println("准备阻塞...");
			promise.sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("main 线程结束.");
	}
}
