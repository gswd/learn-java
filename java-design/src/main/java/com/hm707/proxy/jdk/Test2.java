package com.hm707.proxy.jdk;

import com.hm707.proxy.Hello;
import com.hm707.proxy.HelloImpl;

public class Test2 {
  public static void main(String[] args) {
    Hello hello = new HelloImpl();
    Proxy2 proxy2 = new Proxy2(hello);

    Hello helloProxy = proxy2.getProxy();
    helloProxy.say("Jerry");
    helloProxy.sing("ba ba ba ba");
  }
}
