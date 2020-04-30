package collections.queue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class T01_ConcurrentQueue {
    public static void main(String[] args) {
        Queue<String> strs = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < 10; i++) {
            //添加元素，成功返回true
            strs.offer("a" + i);
        }

        System.out.println(strs);
        System.out.println(strs.size());
        System.out.println(strs.poll());
        System.out.println(strs.size());
        //如果Queue是空，则返回null
        System.out.println(strs.peek());
        System.out.println(strs.size());

        //和peek类似，但如果Queue是空则抛异常
        System.out.println(strs.element());
    }
}
