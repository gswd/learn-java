package dht;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class PingHandler extends SimpleChannelInboundHandler<DatagramPacket> {

	private static final String host = "dht.transmissionbt.com";

	private static final int port = 6881;
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		String msg = "d1:ad2:id20:xxxxxxxxxxxxxxxxxxxe1:q4:ping1:t4:tttt1:y1:qe";
		ByteBuf dataBuf = Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8);

		DatagramPacket datagramPacket = new DatagramPacket(dataBuf, new InetSocketAddress(host, port));
		ctx.writeAndFlush(datagramPacket).addListener(new GenericFutureListener<Future<? super Void>>() {
			@Override
			public void operationComplete(Future<? super Void> future) throws Exception {
				System.out.println("future.isSuccess()  -  " + future.isSuccess());
			}
		});
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
		System.out.println(msg.toString());

	}
}
