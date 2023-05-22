package de.anshaana.playground.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceExample {
    private static AtomicReference<String> reference = new AtomicReference<>("Initial Value");

    public static void main(String[] args) {
        Runnable task = () -> {
            String updatedValue = "Updated Value";
            reference.set(updatedValue);
            System.out.println("Thread " + Thread.currentThread().getId() + " set the reference to: " + updatedValue);
        };

        // Create multiple threads to concurrently set the reference
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        Thread thread3 = new Thread(task);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}



