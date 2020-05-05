package collections.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 1个线程给另一个线程传递数据时使用.
 * 由于Queue的容量是0，实际上相当于线程之间直接传递数据.
 * 类似Exchanger 但是更简单一些.
 */
public class T06_SynchronousQueue {//容量为0

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strs = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " - [take] : " + strs.take());//Thread-0 - [take] : aaa
                strs.put("other Thread");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.put("aaa");//阻塞等待消费者消费, 也就是消费者必须是另外一个线程

        System.out.println(strs.size());//0 : 永远是0

        System.out.println(Thread.currentThread().getName() + " - [take] : " + strs.take());//main - [take] : other Thread
        //System.out.println(strs.add("aaa"));// exception : Queue full
    }

}
