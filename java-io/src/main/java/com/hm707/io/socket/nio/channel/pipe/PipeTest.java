package com.hm707.io.socket.nio.channel.pipe;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.Pipe;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Random;

/**
 * Pipe是一个进程内部单向的管道, 向Pipe.SinkChannel写入数据，可以通过Pipe.SourceChannel读取到对应数据。
 * 虽然有更加有效率的方式来在线程之间传输数据，但是使用管道的好处在于封装性.
 *
 * 优点：
 * 1. 根据给定的通道类型，相同的代码可以被用来写数据到一个File、 socket 或 其他pipe.
 * 2. 配合Selector从多个channel搜集数据
 * 3. 可以用来辅助测试，通过向一端写入数据然后检查读取逻辑，也可以从pipe中读取数据并检查写入逻辑.
 */
public class PipeTest {
	public static void main(String[] args) throws IOException {
		WritableByteChannel out = Channels.newChannel(System.out);

		ReadableByteChannel workerChannel = startWorker(10);
		ByteBuffer buffer = ByteBuffer.allocate(100);

		while (workerChannel.read(buffer) != -1) {
			buffer.flip();
			out.write(buffer);
			buffer.compact();
		}

	}


	private static ReadableByteChannel startWorker(int reps) throws IOException {

		Pipe pipe = Pipe.open();
		Worker worker = new Worker(pipe.sink(), reps);
		worker.start();
		return  pipe.source();
	}

	private static class Worker extends Thread{
		WritableByteChannel channel;
		private int reps;

		Worker(WritableByteChannel channel, int reps) {
			this.channel = channel;
			this.reps = reps;
		}

		@Override
		public void run() {
			ByteBuffer buffer = ByteBuffer.allocate(100);

			try {
				for (int i = 0; i < this.reps; i++) {
					doSomeWork(buffer);

					while (channel.write(buffer) > 0) {

					}
				}
				this.channel.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private String [] products = {
				"No good deed goes unpunished",
				"To be, or what?",
				"No matter where you go, there you are",
				"Just say \"Yo\"",
				"My karma ran over my dogma"
		};

		private Random rand = new Random( );

		private void doSomeWork(ByteBuffer buffer) {
			int product = rand.nextInt (products.length);
			buffer.clear( );
			buffer.put (products[product].getBytes( ));
			buffer.put ("\r\n".getBytes( ));
			buffer.flip();
		}
	}
}
