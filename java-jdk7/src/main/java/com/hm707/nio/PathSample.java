package com.hm707.nio;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

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

		Path bookPath = fs.getPath("d:", "book", "test_link");
		System.out.println("toAbsolutePath -> " + bookPath.toAbsolutePath().toString());

		try {
			//toRealPath会返回一个真实代表一个文件的路径，，如果文件不存在，会抛出NoSuchFileException
			//windows下使用 mklink /D d:\Book\test_link  C:\home1\irteam\logs  来创建符号链接
			//bookPath.toRealPath(LinkOption.NOFOLLOW_LINKS) 使用这个选项表示不跟踪符号链接，即会展示为 d:\book\test_link
			System.out.println("toRealPath -> " + bookPath.toRealPath().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("getFileName -> " + bookPath.getFileName());
		System.out.println("normalize -> " + bookPath.normalize());
		System.out.println("getRoot -> " + bookPath.getRoot());
		System.out.println("isDirectory -> " + Files.isDirectory(bookPath));
		System.out.println("subpath(1,3) -> " + bookPath.subpath(1,2));
		System.out.println("getParent -> " + bookPath.getParent().getParent());

		//如果使用LinkOption.NOFOLLOW_LINKS参数，则不会检查符号链接所指向的真实位置
		System.out.println(bookPath + " is exists -> " + Files.exists(bookPath, LinkOption.NOFOLLOW_LINKS));

		System.out.println("=====================");

		System.out.println("getNameCount -> " + bookPath.getNameCount());
		for (int i = 0; i < bookPath.getNameCount(); i++) {
			System.out.printf("getName(%d): %s\n", i, bookPath.getName(i));
		}


		System.out.println("==========Paths.get()===========");

		Path path = Paths.get("d:\\Book");
		System.out.println(path.toAbsolutePath());

		System.out.println("=========file2Path============");
		file2Path();
	}

	private static void file2Path() {
		try {
			Path path = Paths.get(new URI("file:///C:/home/docs/users.txt"));
			File file = new File("C:\\home\\docs\\users.txt");
			Path toPath = file.toPath();
			System.out.println(toPath.equals(path));

		} catch (URISyntaxException e) {
			System.out.println("Bad URI");
		}
	}


}
