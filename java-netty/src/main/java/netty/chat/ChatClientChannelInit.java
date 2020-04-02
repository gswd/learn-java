package netty.chat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class ChatClientChannelInit extends ChannelInitializer<SocketChannel> {
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ch.pipeline()
			.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8))
			.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8))
			.addLast("ChatClientHandler", new ChatClientHandler());
	}
}
