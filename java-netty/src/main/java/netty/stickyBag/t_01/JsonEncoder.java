package netty.stickyBag.t_01;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;

public class JsonEncoder extends MessageToByteEncoder<User> {
	@Override
	protected void encode(ChannelHandlerContext ctx, User user, ByteBuf out) throws Exception {
		String json = JSON.toJSONString(user);
		out.writeCharSequence(json, CharsetUtil.UTF_8);
//		ctx.writeAndFlush(json.getBytes(CharsetUtil.UTF_8));
	}
}
