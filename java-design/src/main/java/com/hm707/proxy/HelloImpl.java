package com.hm707.proxy;

public class HelloImpl implements Hello {

  @Override
  public void say(String name) {
    System.out.println("Hello! " + name);
  }

  @Override
  public void sing(String content) {
    System.out.println("sing : la la la " + content);
  }
}
