package com.hm707.chain;

public class SensitiveFilter implements Filter {

    @Override
	public void doFilter(Request request, Response response, FilterChain chain) {
        System.out.println("===> start SensitiveFilter");
        request.requestStr = request.getRequestStr().replace("敏感", "**");
        chain.doFilter(request, response, chain);
        System.out.println("===> end SensitiveFilter");
        response.responseStr += " -> SensitiveFilter";

    }

}