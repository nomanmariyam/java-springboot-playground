package de.anshaana.playground.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {
    private static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        Runnable task = () -> {
            int value = counter.incrementAndGet();
            System.out.println("Thread " + Thread.currentThread().getId() + " incremented counter to: " + value);
        };

        // Create multiple threads to concurrently increment the counter
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        Thread thread3 = new Thread(task);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

