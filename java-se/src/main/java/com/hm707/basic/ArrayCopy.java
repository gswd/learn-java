package com.hm707.basic;

/**
 * 使用数组的clone()方法复制数组比较方便
 * 但数组必须是基础数据类型
 *
 * clone方法唯一值得使用的地方就是数组的复制.
 */
public class ArrayCopy {
  public static void main(String[] args) {
    int[] arr1 = new int[3];
    arr1[0] = 0;
    arr1[1] = 1;
    arr1[2] = 2;

    int[] arr2 = arr1.clone();

    System.out.println(arr1 == arr2);
    arr1[0] = 100;
    for (int e : arr2) {
      System.out.println(e);
    }

    System.out.println("---------------");

    Person[] pArr = new Person[3];

    pArr[0] = new Person("p0");
    pArr[1] = new Person("p1");
    pArr[2] = new Person("p2");

    Person[] pArr2 = pArr.clone();

    System.out.println(pArr[0] == pArr2[0]);
    pArr[0].name = "haha";
    for (Person p : pArr2) {
      System.out.println(p);
    }
  }

}
class Person{
  String name;

  public Person(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Person{");
    sb.append("name='").append(name).append('\'');
    sb.append('}');
    return sb.toString();
  }
}