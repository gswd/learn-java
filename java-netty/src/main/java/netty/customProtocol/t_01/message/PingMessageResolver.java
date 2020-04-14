package netty.customProtocol.t_01.message;

import java.util.concurrent.atomic.AtomicInteger;

public class PingMessageResolver implements Resolver {

	private static final AtomicInteger counter = new AtomicInteger();

	@Override
	public boolean support(Message message) {
		return message.getMessageType() == MessageTypeEnum.PING;
	}

	@Override
	public Message resolve(Message message) {
		// 接收到ping消息后，返回一个pong消息返回
		System.out.println("receive ping message: " + System.currentTimeMillis());
		Message pong = new Message();
		pong.setMessageType(MessageTypeEnum.PONG);
		return pong;
	}
}
