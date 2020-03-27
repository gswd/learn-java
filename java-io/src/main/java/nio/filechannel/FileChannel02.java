package nio.filechannel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class FileChannel02 {
	public static void main(String[] args) throws IOException, URISyntaxException {


		File file = new File(Thread.currentThread().getContextClassLoader().getResource("fileChannelTest.txt").toURI());
		FileInputStream fos = new FileInputStream(file);
		FileChannel channel = fos.getChannel();

		File destFile = new File("2.txt");
		System.out.println(destFile.getAbsolutePath());
		FileChannel destChannel = new FileOutputStream(destFile).getChannel();

		channel.transferTo(0, file.length(), destChannel);
		fos.close();
		destChannel.close();


	}
}
