package netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

public class TextWebsocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("server 收到消息 : " + msg.text());

        TextWebSocketFrame frame = new TextWebSocketFrame("服务器时间 : " + LocalDateTime.now());
        ctx.channel().writeAndFlush(frame);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //id是唯一的, LongText是唯一的, ShortText不是唯一的
        System.out.println("==> handler [Added] - " + ctx.channel().id().asLongText());
        System.out.println("==> handler [Added] - " + ctx.channel().id().asShortText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("==> handler [Removed] - " + ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("发生异常 : " + cause.getMessage());
        ctx.close();
    }
}
