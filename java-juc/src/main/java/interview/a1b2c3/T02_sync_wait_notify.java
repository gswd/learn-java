package interview.a1b2c3;

public class T02_sync_wait_notify {
    static Thread t1, t2;

    private static volatile boolean t1Started = false;

    public static void main(String[] args) {
        char[] aC = "ABCDEFG".toCharArray();
        char[] aI = "1234567".toCharArray();
        Object lock = new Object();

        t1 = new Thread(() -> {

            synchronized (lock) {
                t1Started = true;
                for (char c : aC) {
                    try {
                        System.out.print(c);
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();

            }
        });

        t2 = new Thread(() -> {
            synchronized (lock) {

                while (!t1Started) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (char c : aI) {
                    try {
                        System.out.print(c);
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();

            }
        });

        t1.start();
        t2.start();

    }
}
