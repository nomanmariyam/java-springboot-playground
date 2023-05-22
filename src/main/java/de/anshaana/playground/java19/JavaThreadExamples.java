package de.anshaana.playground.java19;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JavaThreadExamples {

    public static void asyncExampleUsingThread() {
        for(int i = 0; i<10; i++) {
            System.out.println("Main Thread1 " + i);
        }

        //using thread pool
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        CompletableFuture<String> asyncCallFuture1 = CompletableFuture.supplyAsync(() -> asyncCall1(), threadPool);
        CompletableFuture<String> asyncCallFuture2 = CompletableFuture.supplyAsync(() -> asyncCall2(), threadPool);
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(asyncCallFuture1, asyncCallFuture2);
        combinedFuture.join();

        String functionValue1 = asyncCallFuture1.join();
        String functionValue2 = asyncCallFuture2.join();
        System.out.println(functionValue1);
        System.out.println(functionValue2);
    }

    private static String asyncCall1() {
        return "I am async function1";
    }

    private static String asyncCall2() {
        return "I am async function2";
    }




    public static void main(String[] args) {
        asyncExampleUsingThread();
    }



    public class MyRunnable implements Runnable {
        public void run() {
            System.out.println("Thread is running");
        }
    }
}
