package consumerAndProducer;

import java.util.LinkedList;

/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 * 使用wait和notify/notifyAll来实现
 */
public class MyContainer1<T> {

    private final LinkedList<T> list = new LinkedList<T>();

    private static final int MAX = 5;
    private int count = 0;

    public synchronized void put(T element) {
        while (list.size() >= MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        list.add(element);
        count++;
        System.out.println("[Producer] - " + "[" + count + "] - " + element + list.toString());
        this.notifyAll();
    }

    public synchronized T get() {
        while (list.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        T t = list.removeFirst();
        count--;
        System.out.println("[Consumer] - " + "[" + count + "] - " + t + list.toString());
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {
        MyContainer1<String> container1 = new MyContainer1<>();
        Runnable producer = ()->{
            for (int i = 0; i < 5; i++) {
                container1.put(Thread.currentThread().getName());
                //System.out.println("[Producer] - " + Thread.currentThread().getName() + " - " + i);
            }
        };
        Runnable consumer = ()->{
            for(int i = 0; i < 2; i++) {
                String s = container1.get();
                //System.out.println("[Consumer] - " + s);
            }
        };

        for (int i = 0; i < 2; i++) {
            new Thread(producer, "P" + i).start();
        }

        for (int i = 0; i < 5; i++) {
            new Thread(consumer, "C" + i).start();
        }
    }

}
