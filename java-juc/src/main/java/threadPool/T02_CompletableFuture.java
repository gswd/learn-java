package threadPool;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class T02_CompletableFuture {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        CompletableFuture<Void> futureTM = CompletableFuture.supplyAsync(T02_CompletableFuture::priceOfTM)
                .thenApply(String::valueOf).thenApply(str-> "[price] " + str).thenAccept(System.out::println);
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(T02_CompletableFuture::priceOfTB);
        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(T02_CompletableFuture::priceOfJD);
        CompletableFuture.allOf(futureJD, futureTB, futureTM).join();

        long end = System.currentTimeMillis();

        System.out.println("use completable future! " + (end - start));

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double priceOfTM() {
        delay();
        return 1.00;
    }

    private static double priceOfTB() {
        delay();
        return 2.00;
    }

    private static double priceOfJD() {
        delay();
        return 3.00;
    }

    private static void delay() {
        int time = new Random().nextInt(500);
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("After %s sleep!\n", time);
    }
}
