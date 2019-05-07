package com.hm707.singleton;

public class Mgr02 {
  private Mgr02(){

  }

  private static class Mgr02Holder{
    private final static Mgr02 INSTANCE = new Mgr02();
  }

  public static Mgr02 getInstance() {
    return Mgr02Holder.INSTANCE;
  }
}
