package netty.customProtocol.t_01.message;

import netty.customProtocol.t_01.message.Message;

public interface Resolver {
	boolean support(Message message);

	Message resolve(Message message);
}
