import java.util.ArrayList;
import java.util.List;

/**
 * 内存溢出
 * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=c:\tmp\jvm.dump -XX:+PrintGCDetails
 *
 * -Xms10M -Xmx10M
 *
 * 内存查看工具VisualVM
 */
public class T04 {
  public static void main(String[] args) {
    List<Object> lists = new ArrayList<>();

    for (int i = 0; i < 100000000; i++) {
      lists.add(new byte[1024 * 1024]);
    }
  }

}
