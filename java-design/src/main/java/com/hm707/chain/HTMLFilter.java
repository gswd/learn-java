package com.hm707.chain;

/**
 * 过滤HTML中的脚本元素
 */
public class HTMLFilter implements Filter {

    @Override
	public void doFilter(Request request, Response response, FilterChain chain) {
        System.out.println("===> start HTMLFilter");
        request.requestStr = request.getRequestStr().replace("<", "[")
                .replace(">", "]");
        chain.doFilter(request, response, chain);
        System.out.println("===> end HTMLFilter");
        response.responseStr += " -> HTMLFilter";

    }

}