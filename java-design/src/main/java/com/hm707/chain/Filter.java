package com.hm707.chain;

public interface Filter {
	void doFilter(Request request, Response response,FilterChain chain);

}