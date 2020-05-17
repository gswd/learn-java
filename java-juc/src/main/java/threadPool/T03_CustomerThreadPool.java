package threadPool;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class T03_CustomerThreadPool {
    static class Task implements Runnable {

        private int i;

        public Task(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Task" + i);
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Task{" +
                    "i=" + i +
                    '}';
        }
    }
    public static void main(String[] args) {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(
                2,//核心线程，开始时ThreadPool中的线程数是0，当有新线程来时才创建线程，不会超时被回收
                4,//最大线程数, 当任务队列满后新建线程去执行(Task6 Task7 直接被执行)，但最多不能超过最大线程数, 超时后会被回收
                60, TimeUnit.SECONDS,//超时时间
                new ArrayBlockingQueue<>(4), //任务队列
                Executors.defaultThreadFactory(),// 线程工厂
                //任务队列满，并达到最大线程数后执行拒绝策略，默认提供4种拒绝策略，但通常需要自定义
                //AbortPolicy : 抛出异常
                //DiscardPolicy : 直接抛弃任务
                //DiscardOldestPolicy : 抛弃最老的线程
                //CallerRunsPolicy : 使用调用线程来执行
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );

        for (int i = 0; i < 8; i++) {
            tpe.execute(new Task(i));
        }
        System.out.println(tpe.getQueue());
        tpe.execute(new Task(100));
        System.out.println(tpe.getQueue());

    }
}
