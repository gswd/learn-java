package com.hm707.chain;

public class FaceFilter implements Filter {

    @Override
	public void doFilter(Request request, Response response, FilterChain chain) {
        System.out.println("===> start FaceFilter");
        request.requestStr = request.getRequestStr().replace(":)",
                "^V^");
        chain.doFilter(request, response, chain);

        System.out.println("===> end FaceFilter");
        response.responseStr += " -> FaceFilter";

    }

} 