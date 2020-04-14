package consumerAndProducer;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * <p>
 * 使用wait和notify/notifyAll来实现
 * <p>
 * 使用Lock和Condition来实现
 * 对比两种方式，Condition的方式可以更加精确的指定哪些线程被唤醒
 */
public class MyContainer2<T> {

    private final LinkedList<T> list = new LinkedList<T>();
    Lock lock = new ReentrantLock();
    private Condition producerCondition = lock.newCondition();
    private Condition consumerCondition = lock.newCondition();
    private static final int MAX = 5;
    private int count = 0;

    public void put(T element) {
        try {
            lock.lock();
            while (list.size() == MAX) {
                producerCondition.await();
            }
            list.add(element);
            count++;
            System.out.println("[P:" + Thread.currentThread().getName() + "] - " + "[" + count + "] - " + element + list.toString());
            consumerCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    public T get() {
        try {
            lock.lock();
            while (list.size() == 0) {
                consumerCondition.await();
            }
            T t = list.removeFirst();
            count--;
            System.out.println("[C:" + Thread.currentThread().getName() + "] - " + "[" + count + "] - " + t + list.toString());
            producerCondition.signal();
            return t;
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MyContainer2<String> container = new MyContainer2<>();
        Runnable producer = () -> {
            for (int i = 0; i < 50; i++) {
                container.put(Thread.currentThread().getName());
                //System.out.println("[Producer] - " + Thread.currentThread().getName() + " - " + i);
            }
        };
        Runnable consumer = () -> {
            for (int i = 0; i < 20; i++) {
                String s = container.get();
                //System.out.println("[Consumer] - " + s);
            }
        };

        for (int i = 0; i < 20; i++) {
            new Thread(producer, "P" + i).start();
        }

        for (int i = 0; i < 50; i++) {
            new Thread(consumer, "C" + i).start();
        }
    }

}
