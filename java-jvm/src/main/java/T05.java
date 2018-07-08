/**
 * 线程栈大小  -Xss128k
 *
 * 值越小：线程并发数量比较多
 * 值越大：递归调用比较深
 */
public class T05 {
  static int count = 0;

  static void r() {
    count++;
    r();
  }

  public static void main(String[] args) {
    try {
      r();
    } catch (Throwable e) {
      System.out.println("===================="+count);
      //e.printStackTrace();//COM StackOverFlow
    }
  }
}
