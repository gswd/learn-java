package collections.queue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列提供了put、tack 方法，它们达到某个条件后进入阻塞.
 * 天生实现了生产者和消费者队列.
 * LinkedBlockingQueue 是无边界的队列.
 * ArrayBlockingQueue 有边界
 */
public class T02_LinkedBlockingQueue {
    static BlockingQueue<String> strs = new LinkedBlockingQueue<>();
    static Random random = new Random();
    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    //如果满了会等待
                    strs.put("a" + i);
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "p").start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (; ; ) {
                    try {
                        //如果空了，就会等待
                        String str = strs.take();
                        System.out.println(Thread.currentThread().getName() + " take -" + str);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "c" + i).start();
        }

    }

}
