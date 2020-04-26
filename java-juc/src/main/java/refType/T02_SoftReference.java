package refType;

import java.lang.ref.SoftReference;

/**
 * 空间不够的时候才会回收 软引用
 */
public class T02_SoftReference {

    /**
     * 设置堆内存最大和最小：-Xms20M -Xmx20M
     */
    public static void main(String[] args) throws InterruptedException {

        SoftReference<byte[]> m = new SoftReference<>(new byte[1024 * 1024 * 15]);// 15M

        System.out.println(m.get());
        System.gc();

        Thread.sleep(500);

        System.out.println(m.get());

        //再分配一个数组， heap 将装不下，这时系统会垃圾回收一次，如果不够，会把若引用回收.
        byte[] b = new byte[1024 * 1024 * 18];//18M
        System.out.println(m.get());

    }

}
