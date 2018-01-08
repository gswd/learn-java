package com.hm707.pipeline;

public interface Handler {
	void channelRead(HandlerContext ctx, Object msg);
}