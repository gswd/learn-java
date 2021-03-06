package com.hm707.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.EnumSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by LH on 2018/5/8.
 * jdk7之后使用java.nio.file.Path 和 java.nio.file.Files  来代替java.io.File对文件的操作
 */
public class FileSample {

	public static void main(String[] args) throws IOException{
		//deleteFile();

		//rename1();
		//rename2();

		//printAttributes();

		//testCopy();

		//testWalkFileTree();

		//testWalkFileTree2();

		//testVisitFile();

		testCreateSameAttribute();
	}

	private static void printAttributes() throws IOException {
		Path path = Paths.get( "d:","abc.txt");
		createFile(path);

		readAttributes(path, "*", LinkOption.NOFOLLOW_LINKS);

		System.out.println("**************");

		System.out.println("the file owner is -- [" + Files.getOwner(path, LinkOption.NOFOLLOW_LINKS).getName() + "]");

		System.out.println("**************\n\r");

		BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class,
				LinkOption.NOFOLLOW_LINKS);
		System.out.println(attributes.creationTime());
		System.out.println(attributes.fileKey());
		System.out.println(attributes.isOther());
	}

	public static void readAttributes(Path path, String attributes, LinkOption... linkOption) throws IOException {
		Map<String, Object> map = Files.readAttributes(path, attributes, linkOption);
		for (String s : map.keySet()) {
			System.out.println(s + ":" + map.get(s));
		}
	}

	/**
	 * 这个方法会抛出：NoSuchFileException，DirectoryNotEmptyException，IOException，SecurityException
	 * 这样当删除一个文件失败时可以根据异常来查找失败原因。
	 */
	private static void deleteFile() {
		Path path = Paths.get("I://aaa");//This path does not exsit in file system.
		try {
			Files.delete(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 使用File类移动文件时，如果目标文件已经存在，则不能移动成功，不能指定调用renameTo方法时的行为.
	 */
	public static void rename1() {

		File file = new File("D:\\abc.txt");

		try {
			boolean createFileResult = file.createNewFile();
			System.out.println("create [abc.txt] file -- [" + createFileResult + "]");
		} catch (IOException e) {
			e.printStackTrace();
		}

		File destinationFile1 = new File("D:\\abc_rename.txt");

		boolean renameResult = file.renameTo(destinationFile1);
		System.out.println("abc.txt ==> abc_rename.txt -- [" + renameResult + "]");


	}


	/**
	 * 使用Files类可以指定文件移动时的行为
	 */
	public static void rename2() throws IOException {

		Path path = Paths.get("D:\\abc.txt");
		createFile(path);

		Path pathRename = Paths.get("D:\\abc_rename.txt");


		Files.move(path, pathRename, StandardCopyOption.REPLACE_EXISTING);

	}

	private static void createFile(Path path) {
		//!exists(path) 不等于 notExists(path)（因为 !exists() 不一定是原子的，而 notExists() 是原子的）
		if (Files.notExists(path)) {
			try {
				Files.createFile(path);
				System.out.println("create [abc.txt] file success!");
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("create [abc.txt] file fail!");
			}
		}
	}

	/**
	 * 文件的复制，目标文件已存在情况下抛 FileAlreadyExistsException 除非指定StandardCopyOption.REPLACE_EXISTING，其他情况下会抛IOException
	 */
	public static void testCopy() {

		Path sourcePath = Paths.get( "d:","abc.txt");

		createFile(sourcePath);

		Path destinationPath = Paths.get( "d:","abc-copy.txt");
		try {
			Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
		} catch(FileAlreadyExistsException e) {
			//destination file already exists
			e.printStackTrace();
		} catch (IOException e) {
			//something else went wrong
			e.printStackTrace();
		}
	}

	/**
	 * 递归访问path下的所有文件
	 */
	public static void testWalkFileTree() throws IOException {
		Path path = Paths.get("d:", "Book");
		Files.walkFileTree(path, new FileVisitor<Path>() {
			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				System.out.println("pre visit dir:" + dir);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				System.out.println("visit file: " + file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
				System.out.println("visit file failed: " + file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				System.out.println("post visit directory: " + dir);
				return FileVisitResult.CONTINUE;
			}
		});
	}
	public static void testWalkFileTree2() throws IOException {
		Path path = Paths.get("d:", "Book");
		Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
			private String level = "";
			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				if (!dir.endsWith("java") && !dir.equals(path)) {
					return FileVisitResult.SKIP_SUBTREE;
				}
				System.out.println(level + "├─ " + dir.getFileName());
				level = upLevel(level);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				System.out.println(level + "*" + file.getFileName());
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				level = downLevel(level);
				return FileVisitResult.CONTINUE;
			}

			private String upLevel(String level) {

				return (level + "    ");
			}

			private String downLevel(String level) {
				if (Objects.isNull(level) || "".equals(level)) {
					return level;
				}

				int index = level.lastIndexOf("    ");
				if (index < 0) {
					return level;
				}

				return level.substring(0, index);
			}
		});
	}


	public static void testVisitFile() throws IOException {
		Path path = Paths.get("d:", "BooK");
		// 得到当前目录下所有文件路径。
		try(DirectoryStream<Path> dir = Files.newDirectoryStream(path, "*.pdf")){
			for (Path entry : dir)
				System.out.println(entry);
		}

		System.out.println("=======================");

		try (DirectoryStream<Path> dir = Files.newDirectoryStream(path, p -> Files.isDirectory(p, LinkOption.NOFOLLOW_LINKS))) {
			dir.forEach(System.out::println);
		}

	}

	private static void testCreateSameAttribute() throws IOException {
		// 初始化一个Path对象
		Path path = Paths.get("D:","testDir","TEXTa.txt");

		//创建新目录
		//文件属性集合
		Set<PosixFilePermission> set = EnumSet.allOf(PosixFilePermission.class);
		// 这里会抛错，貌似windows不支持posix
		Files.createDirectories(path, PosixFilePermissions.asFileAttribute(set));

		//创建一个文件,同上面一样执行无法通过
		Files.createFile(path, PosixFilePermissions.asFileAttribute(set));
	}
}
