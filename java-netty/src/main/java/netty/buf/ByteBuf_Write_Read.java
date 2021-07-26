package netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

public class ByteBuf_Write_Read {
	public static void main(String[] args) {
		ByteBuf byteBuf = Unpooled.buffer(2);
		String t = "1:";
		byteBuf.writeCharSequence(t, CharsetUtil.UTF_8);
		byteBuf.writeChar(':');


		for (; byteBuf.readableBytes() > 0; ) {
			byte r = byteBuf.readByte();

			System.out.print(Integer.toHexString(r) + " - ");
		}
		System.out.println("----");
		String tt = "我喜欢?这个字符";
	}
}
