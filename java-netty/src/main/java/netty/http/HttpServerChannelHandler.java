package netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

@Sharable
public class HttpServerChannelHandler extends SimpleChannelInboundHandler<HttpObject> {
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

		if (msg instanceof HttpRequest) {

			HttpRequest request = (HttpRequest)msg;

			String uri = request.uri();
			String method = request.method().toString();
			String protocol = request.protocolVersion().toString();

			if ("/favicon.ico".equals(uri)) {
				//System.out.println("忽略请求");
				return;
			}
			System.out.println();
			System.out.println(protocol + " - " + method + " - " + uri);

			String connectionInHeader = request.headers().get(HttpHeaderNames.CONNECTION);

			System.out.println(HttpHeaderNames.CONNECTION + " : " + connectionInHeader);

			System.out.println("ctx : " + ctx.hashCode());
			System.out.println("ctx.pipeline : " + ctx.pipeline().hashCode());
			System.out.println("ctx.handler : " + this.hashCode());
			System.out.println("ctx.channel : " + ctx.channel().hashCode());


			ByteBuf byteBuf = Unpooled.copiedBuffer("Hello Client. ".getBytes());
			HttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
			response.headers()
				.add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.TEXT_PLAIN)
				.add(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());

			ctx.writeAndFlush(response);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
		cause.printStackTrace();

	}
}
