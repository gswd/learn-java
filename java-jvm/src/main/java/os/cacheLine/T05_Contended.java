package os.cacheLine;

import sun.misc.Contended;

/**
 *
 * 缓存行对齐：对于有些特别敏感的数字，会存在线程高竞争的访问，为了保证不发生伪共享，可以使用缓存航对齐的编程方式
 *
 * JDK7中，很多采用long padding提高效率
 *
 * JDK8，加入了@Contended注解（实验）需要加上：JVM -XX:-RestrictContended
 */
public class T05_Contended {

    @Contended
    volatile long x;
    @Contended
    volatile long y;

    public static void main(String[] args) throws InterruptedException {
        T05_Contended t = new T05_Contended();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1_0000_0000L; i++) {
                t.x = i;
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1_0000_0000L; i++) {
                t.y = i;
            }
        });

        final long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println((System.nanoTime() - start) / 100_0000);

    }

}
