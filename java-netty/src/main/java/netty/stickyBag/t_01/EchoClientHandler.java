package netty.stickyBag.t_01;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class EchoClientHandler extends SimpleChannelInboundHandler<User> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, User msg) throws Exception {
		System.out.println("receive from Server : " + msg);

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		User user = new User();
		user.setAge(22);
		user.setName("tom");

		ctx.writeAndFlush(user);
	}
}
