package dht;

import java.net.InetAddress;
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
import io.netty.util.internal.SocketUtils;

public class PingHandler extends SimpleChannelInboundHandler<DatagramPacket> {

//	private static final String host = "dht.transmissionbt.com";
	private static final String host = "127.0.0.1";
//	private static final String host = "10.34.135.96";
	private String a = "afdf1979e105b0d8989eee4bfc805a0ce76f65b0";
	private String b = "";

	private static final int port = 6881;
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
//		String msg = "d1:ad2:id20:xxxxxxxxxxxxxxxxxxxe1:q4:ping1:t4:tttt1:y1:qe";
//		String msg = "d1:ad2:id20:ad1bc97bb68c6e3d1a036:target20:ad1bc97bb68c6e3d1a03e1:q9:find_node1:t4:12341:y1:qe";
//		ByteBuf dataBuf = Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8);
//
//		System.out.println(InetAddress.getLocalHost());
//		DatagramPacket datagramPacket = new DatagramPacket(dataBuf, new InetSocketAddress(host, port));
//
//		ctx.writeAndFlush(datagramPacket).sync();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
		String response = msg.content().toString(CharsetUtil.UTF_8);
		System.out.println( "====" + response);

	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

	public static byte[] hexToBytes(String hex) {
		if (hex.length() < 1) {
			return null;
		} else {
			byte[] result = new byte[hex.length() / 2];
			int j = 0;
			for(int i = 0; i < hex.length(); i+=2) {
				result[j++] = (byte)Integer.parseInt(hex.substring(i,i+2), 16);
			}
			return result;
		}
	}
}
