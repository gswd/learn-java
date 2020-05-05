package collections.queue;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

/**
 * 有transfer() 方法, 必须等待有消费者消费掉这个元素才能返回.
 * transfer 和 put 的区别：
 *  transfer是必须有消费者将其取走后(transferQueue.take())才会返回(即解除阻塞).
 *  put 当队列满后才会阻塞，直到队列有空余地方.
 *
 */
public class T07_TransferQueue {
    public static void main(String[] args) throws InterruptedException {
        TransferQueue<String> transferQueue = new LinkedTransferQueue<>();

        new Thread(() -> {
            try {
                while (true) {

                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("-- [C] 处理数据 : " + transferQueue.take());
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("消费者任务完成");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        for (int i = 0; i < 3; i++) {
            String data = "A-" + i;
            new Thread(() -> {
                try {
                    System.out.println("[P] [Start] - " + Thread.currentThread().getName());

                    System.out.println("[P] 发送数据 - " + data);
                    transferQueue.transfer(data);
                    System.out.println("[P] 传输完成 - " + data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }



    }
}
