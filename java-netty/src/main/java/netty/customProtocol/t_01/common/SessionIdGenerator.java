package netty.customProtocol.t_01.common;

import java.util.UUID;

public class SessionIdGenerator {
	public static String generate() {
		return UUID.randomUUID().toString().substring(0, 8);
	}

}
