package com.hm707.proxy.jdk;

import java.lang.reflect.Proxy;

import com.hm707.proxy.Hello;
import com.hm707.proxy.HelloImpl;

public class Test1 {
  public static void main(String[] args) {
    Hello hello = new HelloImpl();
    Proxy1 proxy1 = new Proxy1(hello);

    Hello helloProxy = (Hello)Proxy.newProxyInstance(hello.getClass().getClassLoader(), hello.getClass().getInterfaces(), proxy1);
    helloProxy.say("Jack");
  }
}
