### run方法运行结束后线程退出,为什么还需要结束一个线程呢？

情况有很多种,比如

- 很多线程的运行模式是死循环，比如在生产者/消费者模式中，消费者主体就是一个死循环，它不停的从队列中接受任务，执行任务，在停止程序时，我们需要一种"优雅"的方法以关闭该线程。
- 在一些图形用户界面程序中，线程是用户启动的，完成一些任务，比如从远程服务器上下载一个文件，在下载过程中，用户可能会希望取消该任务。
- 在一些场景中，比如从第三方服务器查询一个结果，我们希望在限定的时间内得到结果，如果得不到，我们会希望取消该任务。
- 有时，我们会启动多个线程做同一件事，比如类似抢火车票，我们可能会让多个好友帮忙从多个渠道买火车票，只要有一个渠道买到了，我们会通知取消其他渠道。


### 中断机制

> 停止一个线程的主要机制是中断，中断并不是强迫终止一个线程，**它是一种协作机制**，是给线程传递一个取消信号，但是由线程来决定如何以及何时退出.

Thread 类定义了如下中断方法

```
public boolean isInterrupted();
public void interrupt();
public static boolean interrupted(); //实际会调用Thread.currentThread()操作当前线程
```

每个线程都有一个标志位，表示该线程是否被中断了。

- isInterrupted：返回该线程是否被中断
- interrupted: 返回该线程是否被中断, 但**同时会清除中断状态**, 即连续两次调用interrupted()，第一次为true，第二次一般是false.
- interrupt: 中断对应的线程，即设置中断标志,在线程不同状态下表现不同.

#### RUN/RUNNABLE 

线程运行中，或者线程具备运行条件,正在等待操作系统调度.

线程在没有执行IO操作时, `interrupt()`只是会设置线程的中断标志.线程可以在合适的位置检查中断标志位.


```java
public class InterruptRunnableDemo extends Thread {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            // ... 单次循环代码
        }
    }
}
```

#### WAITING/TIMED_WAITING

等待状态

`join()` `wait()` 方法被调用时会进入 WAITING 状态


等待超时状态

`wait(long timeout)` `sleep(long millis)` `join(long millis)` 被调用时进入 TIMED_WAITING 状态

调用 `interrupt()` 会使得该线程抛出 `InterruptedException` 

**注意，抛出异常后，中断标志位会被清空，而不是被设置。**

捕获到 `InterruptedException`，通常表示希望结束该线程, 捕获到异常后通常应该设置中断标志，让其它代码知道发生了中断.

```java
public class InterruptWaitingDemo extends Thread {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                // 模拟任务代码
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                //做一些清理操作
            	
                System.out.println(isInterrupted()); // -> false
                // 重设中断标志位
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(isInterrupted());
    }

    public static void main(String[] args) {
        InterruptWaitingDemo thread = new InterruptWaitingDemo();
        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
        thread.interrupt();
    }
}
```

#### BLOCKED

阻塞状态，即线程在等待锁.

象调用 `interrupt()` 只是会设置线程的中断标志, 线程依然处于 BLOCKED 状态.

`interrupt()` 并不能使一个在等待锁的线程真正"中断".

```java
public class InterruptSynchronizedDemo {
    private static Object lock = new Object();

    private static class A extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
            	System.out.println("haha");
                while (!Thread.currentThread().isInterrupted()) {
                }
            }
            System.out.println("exit");
        }
    }

    public static void test() throws InterruptedException {
        synchronized (lock) {
            A a = new A();
            a.start(); //线程a不会结束
            Thread.sleep(1000);

            a.interrupt();
            a.join();//主线程等待A线程结束 如果去掉这，线程就会结束,因为主线程结束，释放锁，A获得锁后发现中断，然后线程结束
        }
    }

    public static void main(String[] args) throws InterruptedException {
        test();
    }
}
```

#### NEW/TERMINATE

线程尚未启动或者结束状态

调用 `interrupt()` 对它没有任何效果，也不会设置中断标志。

```java
public class InterruptNotAliveDemo {
    private static class A extends Thread {
        @Override
        public void run() {
        }
    }

    public static void test() throws InterruptedException {
        A a = new A();
        a.interrupt();
        System.out.println(a.isInterrupted());// -> false

        a.start();
        Thread.sleep(100);
        a.interrupt();
        System.out.println(a.isInterrupted());// -> false
    }

    public static void main(String[] args) throws InterruptedException {
        test();
    }
}
```

### IO 操作

**`InputStream` 的 `read()` ，该操作是不可中断的，如果流中没有数据，`read` 会阻塞 (但线程状态依然是 `RUNNABLE` )，且不响应 `interrupt()`，**
与 `synchronized` 类似，调用 `interrupt()` 只会设置线程的中断标志，而不会真正"中断"它.

调用流的 `close` 方法,可以中断read()调用

```java
public class InterruptReadDemo {
    private static class A extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    System.out.println(System.in.read());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("exit");
        }

        public void cancel() {
            try {
                System.in.close();
            } catch (IOException e) {
            }
            interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        A t = new A();
        t.start();
        Thread.sleep(100);

        t.cancel();
    }
}
```

### 如何正确的关闭线程


interrupt方法不一定会真正"中断"线程，它只是一种协作机制

如果不明白线程在做什么，不应该贸然的调用线程的interrupt方法


对于以线程提供服务的程序模块而言，它应该封装取消/关闭操作，提供单独的取消/关闭方法给调用者，
类似于InterruptReadDemo中演示的cancel方法，外部调用者应该调用这些方法而不是直接调用interrupt。


