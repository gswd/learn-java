package com.hm707.chain;

public class SesitiveFilter implements Filter {

    @Override
	public void doFilter(Request request, Response response, FilterChain chain) {
        System.out.println("===> start SesitiveFilter");
        request.requestStr = request.getRequestStr().replace("敏感", "**");
        chain.doFilter(request, response, chain);
        System.out.println("===> end SesitiveFilter");
        response.responseStr += " -> SesitiveFilter";

    }

}