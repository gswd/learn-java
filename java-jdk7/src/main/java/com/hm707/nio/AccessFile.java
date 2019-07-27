package com.hm707.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

public class AccessFile {
	public static void main(String[] args) throws IOException {
		sharePosition();
	}

	/**
	 * FileChannel position 是从底层的文件描述符获得的,
	 * 一个对象对该 position 的更新可以被另一个对象看到
	 */
	public static void sharePosition() throws IOException {
		File temp = File.createTempFile("temp", "");
		RandomAccessFile rFile = new RandomAccessFile(temp, "r");
		FileChannel channel = rFile.getChannel();

		rFile.seek(100);
		System.out.println("random file position : " + rFile.getFilePointer());
		System.out.println("channel position : " + channel.position());

		System.out.println("============");

		channel.position(30);
		System.out.println("random file position : " + rFile.getFilePointer());
		System.out.println("channel position : " + channel.position());

		rFile.close();
		Files.delete(temp.toPath());

	}

}
