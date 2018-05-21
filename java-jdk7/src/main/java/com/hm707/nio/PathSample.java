package com.hm707.nio;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

/**
 * Created by LH on 2018/5/15.
 */
public class PathSample {
	public static void main(String[] args) {
		FileSystem fs = FileSystems.getDefault();
		System.out.println(fs.toString());
		System.out.println(fs.getSeparator());
		fs.getFileStores().forEach(System.out::println);
		System.out.println(fs.isOpen());
		System.out.println(fs.isReadOnly());

		System.out.println("=====================");

		Path bookPath = fs.getPath("d:", "book", "..", "book");
		System.out.println("toAbsolutePath -> " + bookPath.toAbsolutePath().toString());

		try {
			System.out.println("toRealPath -> " + bookPath.toRealPath(LinkOption.NOFOLLOW_LINKS).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("getFileName -> " + bookPath.getFileName());
		System.out.println("getNameCount -> " + bookPath.getNameCount());
		System.out.println("getRoot -> " + bookPath.getRoot());
		System.out.println("isDirectory -> " + Files.isDirectory(bookPath));


	}


}
