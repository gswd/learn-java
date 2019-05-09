package com.hm707.singleton;

/**
 * 创建枚举默认就是线程安全的，所以不需要担心double checked locking，而且还能防止反序列化导致重新创建新的对象。
 */
public enum Mgr03 {
  INSTANCE("haha");

  String x;

  Mgr03(String x) {
    this.x = x;
  }

  //变量
  //方法
}
