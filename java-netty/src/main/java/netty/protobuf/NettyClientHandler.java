package netty.protobuf;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.Random;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Random random = new Random();
        StudentPOJO.MyMessage message = null;
        if (random.nextBoolean()) {
            StudentPOJO.Student student = StudentPOJO.Student.newBuilder().setId(4).setName("TOM").build();

            StudentPOJO.MyMessage.DataType studentType = StudentPOJO.MyMessage.DataType.StudentType;

            message = StudentPOJO.MyMessage.newBuilder()
                    .setDataType(studentType)
                    .setStudent(student)
                    .build();
        } else {
            StudentPOJO.Worker worker = StudentPOJO.Worker.newBuilder().setAge(50).setName("Jerry-Worker").build();

            StudentPOJO.MyMessage.DataType workerType = StudentPOJO.MyMessage.DataType.WorkerType;

            message = StudentPOJO.MyMessage.newBuilder()
                    .setDataType(workerType)
                    .setWorker(worker)
                    .build();
        }

        ctx.writeAndFlush(message);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("Server replay message : " + buf.toString(CharsetUtil.UTF_8));
        System.out.println("Server Addr : " + ctx.channel().remoteAddress());

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
