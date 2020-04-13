package netty.stickyBag.t_01;

import java.util.List;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;

public class JsonDecoder extends MessageToMessageDecoder<ByteBuf> {
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {

		User user = JSON.parseObject(msg.toString(CharsetUtil.UTF_8), User.class);
		out.add(user);
	}
}
