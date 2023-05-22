package de.anshaana.playground.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicBooleanExample {
    private static AtomicBoolean flag = new AtomicBoolean(false);

    public static void main(String[] args) {
        Runnable task = () -> {
            boolean value = flag.compareAndSet(false, true);
            System.out.println("Thread " + Thread.currentThread().getId() + " set the flag to: " + value);
        };

        // Create multiple threads to concurrently set the flag
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        Thread thread3 = new Thread(task);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

