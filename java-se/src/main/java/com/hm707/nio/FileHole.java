package com.hm707.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件空洞
 * 当磁盘上一个文件的分配空间小于它的文件大小时会出现“文件空洞”。对于内容稀疏的文
 * 件，空洞是否占用磁盘由文件系统决定, 大多数现代文件系统只为实际写入的数据分配磁盘空间（更准确地说，只为那些写入数
 * 据的文件系统页分配空间）
 */
public class FileHole {
	public static void main(String[] args) throws IOException {
		File temp = File.createTempFile("hold", null);
		RandomAccessFile file = new RandomAccessFile(temp, "rw");
		FileChannel channel = file.getChannel();

		ByteBuffer byteBuffer = ByteBuffer.allocateDirect (100);

		putData(0, byteBuffer, channel);
		putData(5000000, byteBuffer, channel);
		putData(5000, byteBuffer, channel);
		System.out.println ("Wrote temp file '" + temp.getPath( )
				+ "', size=" + channel.size( ));

		channel.close();
		file.close();

	}

	private static void putData(int position, ByteBuffer byteBuffer, FileChannel channel) throws
			IOException {
		String string = "*<-- location " + position;
		byteBuffer.clear();
		byteBuffer.put(string.getBytes("US-ASCII"));
		byteBuffer.flip();

		channel.position(position);
		channel.write(byteBuffer);
	}

}
