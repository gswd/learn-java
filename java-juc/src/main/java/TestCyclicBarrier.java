import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 循环栅栏, 积攒一定线程数后，推到栅栏，积攒满后可以指定一个动作.
 */
public class TestCyclicBarrier {
    public static void main(String[] args) {
//        CyclicBarrier barrier = new CyclicBarrier(5);
        CyclicBarrier barrier = new CyclicBarrier(5, ()->{
            System.out.println("人满发车.");
        });
        for (int i = 0; i < 11; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + ":start");
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":end");
            }).start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
