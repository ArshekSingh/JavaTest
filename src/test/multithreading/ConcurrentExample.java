package test.multithreading;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;


/*
* 1. The use of ConcurrentHashMap to provide thread-safe access to a shared data structure.
2. The use of Semaphore to limit the number of threads that can access the shared data structure at any given time.
3. Thread creation and execution using the Thread class and lambda expressions.
4. Thread sleep using the Thread.sleep() method to simulate processing time.
5. Iterating through a ConcurrentHashMap to print the results of the concurrent execution.
In this example, we create a shared ConcurrentHashMap to store the number of times each thread executes a task.
* We use a Semaphore to limit the number of threads that can access the shared data structure to five at any given time.
* Each thread acquires the semaphore, updates the map with the number of times it has executed a task, sleeps for
* a short time, and then releases the semaphore. After all the threads have completed, we iterate through the
* ConcurrentHashMap and print the results of the concurrent execution.*/

public class ConcurrentExample {

    public static void main(String[] args) throws InterruptedException {
        final int NUM_THREADS = 5;
        final int NUM_ITERATIONS = 10;
        final Semaphore semaphore = new Semaphore(NUM_THREADS);
        final ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        for (int i = 0; i < NUM_ITERATIONS; i++) {
            for (int j = 0; j < NUM_THREADS; j++) {
                final int taskId = j;
                new Thread(() -> {
                    try {
                        semaphore.acquire();
                        String threadName = Thread.currentThread().getName();
                        int value = map.getOrDefault(threadName, 0);
                        map.put(threadName, value + 1);
                        System.out.println("Task " + taskId + " executed by " + threadName);
                        Thread.sleep(100);
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }

        Thread.sleep(1000);

        System.out.println("\nResult:");
        map.forEach((key, value) -> System.out.println(key + " : " + value));
    }
}
