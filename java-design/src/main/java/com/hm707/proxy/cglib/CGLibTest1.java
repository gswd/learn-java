package com.hm707.proxy.cglib;

import com.hm707.proxy.HelloImpl;

public class CGLibTest1 {
  public static void main(String[] args) {

    //CGLibProxy1 proxy1 = new CGLibProxy1();
    //HelloImpl hello = proxy1.getProxy(HelloImpl.class);
    //hello.say("cglib ... ");

    HelloImpl hello2 = CGLibProxy2.getInstance().getProxy(HelloImpl.class);
    hello2.say("cglib2 **@#&%^");
    hello2.sing("ba ba ba ~~~");
  }
}
