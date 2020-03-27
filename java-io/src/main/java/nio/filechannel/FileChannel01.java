package nio.filechannel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileChannel01 {
	public static void main(String[] args) throws IOException, URISyntaxException {

		String content = "测试内容";

		File file = new File(Thread.currentThread().getContextClassLoader().getResource("fileChannelTest.txt").toURI());
		System.out.println(file.toPath());
		FileOutputStream fos = new FileOutputStream(file);
		FileChannel channel = fos.getChannel();

		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

		byteBuffer.put(content.getBytes(StandardCharsets.UTF_8));

		byteBuffer.flip();
		channel.write(byteBuffer);

		fos.close();


	}
}
