package toolClass;

import java.util.concurrent.Semaphore;

/**
 * Semaphore 信号灯, 作用是限流
 * 初始化一个 许可(permits)的数量，限制最多有多少线程同时执行
 */
public class TestSemaphore {
    public static void main(String[] args) {
        //Semaphore s = new Semaphore(2);
        Semaphore s = new Semaphore(1, true);//fair 指定是否公平， 公平可以保证先启动的线程可以先获得锁
        //允许一个线程同时执行
        //Semaphore s = new Semaphore(1);

        new Thread(()->{
            try {
                //获得一个 许可
                s.acquire();

                System.out.println("T1 running [start]...");
                Thread.sleep(200);
                System.out.println("T1 running [end]...");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //释放许可
                s.release();
            }
        }).start();

        new Thread(()->{
            try {
                s.acquire();

                System.out.println("T2 running [start]...");
                Thread.sleep(200);
                System.out.println("T2 running [end]...");

                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
