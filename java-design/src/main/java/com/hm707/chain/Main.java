package com.hm707.chain;

public class Main {
	public static void main(String[] args) {
        String message = "敏感词，<script> 哈哈哈 :)";
        Request request = new Request();
        request.setRequestStr(message);

        Response response = new Response();
        response.setResponseStr("response");

        FilterChain fc = new FilterChain();
        fc.addFilter(new HTMLFilter());

        FilterChain fc2 = new FilterChain();
        fc2.addFilter(new FaceFilter()).addFilter(new SensitiveFilter());

        fc.addFilter(fc2);
        fc.doFilter(request, response, fc);

        System.out.println("request = " + request.getRequestStr());
        System.out.println("response = " + response.getResponseStr());
    }
}