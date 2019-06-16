package com.hm707.netty.s01;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

public class Client {
  public static void main(String[] args) {
    EventLoopGroup group = new NioEventLoopGroup();

    Bootstrap boot = new Bootstrap();

    try {
      ChannelFuture f = boot.group(group)
        .channel(NioSocketChannel.class)
        .handler(new ClineChannelInitializer())
        .connect("localhost", 8899);

      f.addListener(new ChannelFutureListener() {
        @Override
        public void operationComplete(ChannelFuture channelFuture) throws Exception {
          if (!channelFuture.isSuccess()) {
            System.out.println("not connected!");
          } else {
            System.out.println("connect!");
          }

        }
      });

      f.sync();

      System.out.println("...");

      f.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      group.shutdownGracefully();
    }

  }
}

class ClineChannelInitializer extends ChannelInitializer<SocketChannel>{

  @Override
  protected void initChannel(SocketChannel socketChannel) throws Exception {
    socketChannel.pipeline().addLast(new ClientHandler());
  }
}

class ClientHandler extends ChannelInboundHandlerAdapter {
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    ByteBuf buf = null;
    try {
      buf = (ByteBuf)msg;
      byte[] bytes = new byte[buf.readableBytes()];
      buf.getBytes(buf.readerIndex(), bytes);

      System.out.println(new String(bytes));

    } finally {
      if (buf != null) {
        ReferenceCountUtil.release(buf);
        System.out.println(buf.refCnt());
      }

    }
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    //第一次连上，写出字符串
    ByteBuf buf = Unpooled.copiedBuffer("hello".getBytes());
    ctx.writeAndFlush(buf);
  }
}
