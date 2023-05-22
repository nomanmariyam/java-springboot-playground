package de.anshaana.playground.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayExample {
    private static final int NUM_EVENTS = 3;
    private static AtomicIntegerArray counter = new AtomicIntegerArray(NUM_EVENTS);

    public static void main(String[] args) {
        Runnable task = () -> {
            // Simulating different events
            int eventIndex = (int) (Math.random() * NUM_EVENTS);

            // Increment the counter for the event
            counter.incrementAndGet(eventIndex);
            System.out.println("Thread " + Thread.currentThread().getId() +
                    " incremented counter for event " + eventIndex);
        };

        // Create multiple threads to concurrently count events
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(task);
            threads[i].start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Print the final count for each event
        for (int i = 0; i < NUM_EVENTS; i++) {
            System.out.println("Count for event " + i + ": " + counter.get(i));
        }
    }
}






