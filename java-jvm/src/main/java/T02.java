/**
 * -XX:-DoEscapeAnalysis -XX:-EliminateAllocations -XX:-UseTLAB -XX:+PrintGCDetails
 * 关闭逃逸分析，关闭标量替换，关闭线程本地内存，打印GC信息
 */
public class T02 {

  public static void main(String[] args) {
    byte[] b = new byte[1024];
  }
}
