package netty.stickyBag.t_01;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class EchoServerHandler extends SimpleChannelInboundHandler<User> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, User msg) throws Exception {
		System.out.println("receive from client : " + msg);
		ctx.writeAndFlush(msg);
	}
}
