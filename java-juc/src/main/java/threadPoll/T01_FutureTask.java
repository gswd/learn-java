package threadPoll;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class T01_FutureTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //FutureTask既是一个Future又是一个Task
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(2);
            return "success";
        });

        new Thread(futureTask).start();
        System.out.println("---");
        System.out.println(futureTask.get());

    }
}
