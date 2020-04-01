package netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

public class ByteBuf01 {
    public static void main(String[] args) {
        //创建ByteBuf，该对象包含一个数组.
        ByteBuf buf = Unpooled.buffer(3);
        buf.writeByte(2);

//        for (int i = 0; i < buf.capacity(); i++) {
//            System.out.println(buf.getByte(i));
//        }
//        while (buf.readableBytes() > 0) {
//            System.out.println(buf.readByte());
//        }

        ByteBuf buf2 = Unpooled.copiedBuffer("hello world!", CharsetUtil.UTF_8);
        System.out.println("hello world!字节数：" + "hello world!".getBytes().length);
        System.out.println("你好 字节数：" + "你好".getBytes().length);

        //判断是否有支撑数组(backing byte array)
        if (buf2.hasArray()) {
            byte[] content = buf2.array();
            System.out.println(new String(content, CharsetUtil.UTF_8));
            System.out.println(buf2);//UnpooledByteBufAllocator$InstrumentedUnpooledUnsafeHeapByteBuf(ridx: 0, widx: 12, cap: 36)
            System.out.println("--- 属性 ---");
            System.out.println("offset : " + buf2.arrayOffset());//0
            System.out.println("readerIndex : " + buf2.readerIndex());//0
            System.out.println("writeIndex : " + buf2.writerIndex());//12
            System.out.println("capacity : " + buf2.capacity());//36 --> 12(字符数) * 3(UTF8占的字节数)

            System.out.println("--- test ByteBuf3 ---");
            ByteBuf buf3 = Unpooled.wrappedBuffer(content);//将指定数组作为支撑数组
            System.out.println("buf3.array() == content ? : " + (buf3.array() == content));//true
            System.out.println("buf3.capacity : " + buf3.capacity());//36
            System.out.println("输出world : " + buf3.getCharSequence(6, 6, CharsetUtil.UTF_8));
        }

    }
}
