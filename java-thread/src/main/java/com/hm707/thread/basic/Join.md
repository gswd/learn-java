
## 使用

`join` 是将指定线程加入执行，并且该执行成功后调用join方法的线程才会继续执行.

```java
public class JoinTest {

	private static void test01() throws InterruptedException {
		Runnable task = () -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("[test01] [task run] -> " + Thread.currentThread().getName());
		};
		
		Thread t = new Thread(task);
        t.start();
        System.out.println("[test01] [add thread] -> " + t.getName() + " [start] & [join]");
        t.join();

		System.out.println("[test01] [main] --> main thread done!");
	}

}
```

上面的例子中 `t线程` 执行完成后才会继续主线程的执行.

## 原理

`join` 实际是使用 `wait` 方法实现的，所以`join`是可以被打断的.

核心代码

```java
class Thread{
    public final synchronized void join(){
    	//...
    	
		while (isAlive()) {
            wait(0);
        }
        
        //...
	}
}
```

`isAlive()` 判断当前线程是否还活着,即 `t线程`是否执行完成.

如果 `t线程` 正在执行, 则调用 `wait(0)` 方法， 注意 `wait`的调用是 main 线程调用的，而非`this.wait()`，所以 main 线程进入 `waiting` 状态. 
待该线程结束后由Java系统调用 `notifyAll` 来通知。

