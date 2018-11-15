package com.hm707.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGLibProxy1 implements MethodInterceptor {

  @SuppressWarnings("unchecked")
  public <T> T getProxy(Class<T> cls) {
    return (T)Enhancer.create(cls, this);
  }

  @Override
  public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
    System.out.println("proxy1 before()");
    Object result = methodProxy.invokeSuper(o, objects);
    System.out.println("proxy1 after()");
    return result;
  }
}
