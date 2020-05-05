package interview.a1b2c3;

import java.util.concurrent.locks.LockSupport;

public class T01_LockSupport {
    static Thread t1, t2;

    public static void main(String[] args) {
        char[] aC = "ABCDEFG".toCharArray();
        char[] aI = "1234567".toCharArray();

        t1 = new Thread(() -> {
            for (char c : aC) {
                System.out.print(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });

        t2 = new Thread(() -> {
            for (char c : aI) {
                LockSupport.park();
                System.out.print(c);
                LockSupport.unpark(t1);
            }
        });

        t1.start();
        t2.start();

    }
}
