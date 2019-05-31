package com.hm707.io.socket.nio.channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class ChannelCopy {

	public static void main(String[] args) throws IOException {
		ReadableByteChannel source = Channels.newChannel(System.in);
		WritableByteChannel dest = Channels.newChannel(System.out);

		//channelCopy1(source, dest);
		channelCopy2(source, dest);

		source.close();
		dest.close();
	}

	/**
	 * read()后将数据写入channel 但不一定会将数据全部写入，
	 * 执行compact()后继续读取，这样会产生buffer数据复制问题，但会减少系统调用.
	 */
	private static void channelCopy1(ReadableByteChannel source, WritableByteChannel dest) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);

		while (source.read(buffer) != -1) {
			buffer.flip();
			dest.write(buffer);

			buffer.compact();
		}

		//接收到EOF后缓冲区也会有数据
		buffer.flip( );
		// Make sure that the buffer is fully drained
		while (buffer.hasRemaining( )) {
			dest.write (buffer);
		}
	}

	/**
	 * read()后，将数据全部写入writableByteChannel,确保下一次read()时buffer为空.
	 * 这样做不需要复制buffer中的数据，但是可能会增加系统调用.
	 *
	 */
	private static void channelCopy2(ReadableByteChannel source, WritableByteChannel dest) throws IOException {

		ByteBuffer buffer = ByteBuffer.allocateDirect(16 * 1024);

		while (source.read(buffer) != -1) {
			buffer.flip();

			while (buffer.hasRemaining()) {
				dest.write(buffer);
			}
			buffer.clear();
		}
	}

}
