package netty.chat;

import java.time.LocalDateTime;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * ==> handler [Added]
 * --> channel [Registered]
 * --> channel [Active]
 * --> channel [Read]
 * --> channel [ReadComplete]
 */
public class ChatServerHandler extends ChannelInboundHandlerAdapter {

	private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		//System.out.println("==> handler [Added]");
		String msg = LocalDateTime.now().toString() + " : [Client > 加入] - [" + ctx.channel().remoteAddress() + "]";
		channels.writeAndFlush(msg);
		channels.add(ctx.channel());
		System.out.println("==> handler [Added] Client Channels Size : " + channels.size());

	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		//System.out.println("==> handler [Removed]");
		String msg = LocalDateTime.now().toString() + " : [Client > 离开] - [" + ctx.channel().remoteAddress() + "]";
		System.out.println(msg);
		channels.writeAndFlush(msg);
		//channels 不需要手动移除
		System.out.println("==> handler [Removed] Client Channels Size : " + channels.size());
	}


	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		//System.out.println("--> channel [Registered]");
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		//System.out.println("--> channel [Unregistered]");
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//System.out.println("--> channel [Active]");
		String msg = LocalDateTime.now().toString() + " : [Client > 上线] - [" + ctx.channel().remoteAddress() + "]";
		System.out.println(msg);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		//System.out.println("--> channel [Inactive]");
		String msg = LocalDateTime.now().toString() + " : [Client > 下线] - [" + ctx.channel().remoteAddress() + "]";
		System.out.println(msg);
	}


	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		//System.out.println("--> channel [ReadComplete]");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		//System.out.println("--> channel [Read]");
		channels.stream().filter(c -> c != ctx.channel()).forEach(c -> {
			c.writeAndFlush(msg);
		});
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
}
