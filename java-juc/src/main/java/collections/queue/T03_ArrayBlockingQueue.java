package collections.queue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class T03_ArrayBlockingQueue {
    //容量是10
    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);
    static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            strs.put("a" + i);
        }

        //满了就会等待，程序阻塞
        strs.put("aaa");

        //不能添加时报错
        //strs.add("aaa");

        //boolean res = strs.offer("aaa");//false
        //strs.offer("aaa", 1, TimeUnit.SECONDS);

        System.out.println(strs);

    }
}
