package netty.protobuf;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        StudentPOJO.MyMessage message = (StudentPOJO.MyMessage) msg;

        if (message.getDataType() == StudentPOJO.MyMessage.DataType.StudentType) {
            StudentPOJO.Student student =  message.getStudent();
            System.out.println("student : ");
            System.out.println(student.getId());
            System.out.println(student.getName());
        }
        if (message.getDataType() == StudentPOJO.MyMessage.DataType.WorkerType) {
            StudentPOJO.Worker worker =  message.getWorker();
            System.out.println("worker : ");
            System.out.println(worker.getAge());
            System.out.println(worker.getName());
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, Client ...", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
