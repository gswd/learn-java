package netty.s01;

import java.util.concurrent.TimeUnit;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("服务器读取线程 : " + Thread.currentThread().getName());
//        System.out.println("server ctx = " + ctx);

        Channel channel = ctx.channel();
        ChannelPipeline pipeline = ctx.pipeline();//双向链表

        ByteBuf buf = (ByteBuf) msg;
        System.out.println("client send message : " + buf.toString(CharsetUtil.UTF_8));

        ctx.channel().eventLoop().execute(()->{
            System.out.println(System.currentTimeMillis() + " : " + Thread.currentThread().getName() + " - 业务处理开始1");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + " : " + Thread.currentThread().getName() + " - 业务处理完成1.");

        });
//        ctx.channel().eventLoop().schedule(
//            ()->{
//                System.out.println("业务处理开始2" + Thread.currentThread().getName());
//                try {
//                    TimeUnit.SECONDS.sleep(2);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//            }
//            System.out.println("业务处理完成2.");
//
//        }, 10, TimeUnit.SECONDS);
//        System.out.println("111");

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, Client ...", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
