package com.hm707.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Proxy1 implements InvocationHandler {

  private Object target;

  public Proxy1(Object target) {
    this.target = target;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("proxy1 before()");
    Object result = method.invoke(target, args);
    System.out.println("proxy1 after()");
    return result;
  }
}
