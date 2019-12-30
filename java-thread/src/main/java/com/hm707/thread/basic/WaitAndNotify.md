### Wait & notify 协作

`wait()` `notify()` `notifyAll()` 在`Object` 中定义

`wait` `notify` 方法只能在 `synchronized` 代码块内被调用, 当前线程没有持有对象锁，会抛出异常`java.lang.IllegalMonitorStateException`.

**锁的等待队列**: 对于锁机制，存在一个等待队列，当需要获取锁但锁没被释放时，需要进入等待队列，等待锁的释放.

**条件等待队列**: 条件队列，该队列用于线程间的协作, 一旦调用 `wait` 马上释放锁，即使当前正处于 `synchronized` 代码块中, 
该线程会加入条件队列, 等待其他线程`notify`或者发生超时.


#### wait的具体过程 :

1. 把当前线程放入条件等待队列，释放对象锁，阻塞等待，线程状态变为 `WAITING` 或 `TIMED_WAITING`
2. 等待时间到或被其他线程调用 `notify/notifyAll` 从条件队列中移除，这时，**要重新竞争对象锁**
    - 如果能够获得锁，线程状态变为RUNNABLE，并从wait调用中返回
    - 否则，该线程加入对象锁等待队列，线程状态变为BLOCKED，只有在获得锁后才会从wait调用中返回
    
    
调用 `notify` 会把在条件队列中等待的线程唤醒并从队列中移除，但**它不会释放对象锁**，
即，只有在包含 `notify` 的 `synchronzied` 代码块执行完后，等待的线程才会从 `wait` 调用中返回。


#### 局限性

只能有一个条件等待队列，这是Java wait/notify机制的局限性，这使得对于等待条件的分析变得复杂.


#### 生产者 & 消费者


#### 同时开始

