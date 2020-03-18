import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch  倒数门闩
 * 用来批量控制线程结束
 */
public class TestCountDownLatch {
    public static void main(String[] args) {
        usingCountDownLatch();
        usingJoin();
    }

    private static void usingCountDownLatch() {
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int result = 0;
                for (int j = 0; j < 10000; j++) result += j;
                System.out.println(Thread.currentThread().getName() + " : " + result);
                latch.countDown();
            });
        }

        for (Thread t : threads) {
            t.start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end latch");
    }

    private static void usingJoin() {
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                int result = 0;
                for (int j = 0; j < 10000; j++) result += j;
                System.out.println(Thread.currentThread().getName() + " : " + result);
            });
        }

        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        System.out.println("end join");
    }
}
