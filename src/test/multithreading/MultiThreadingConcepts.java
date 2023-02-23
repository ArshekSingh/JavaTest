package test.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
* This example includes the following multithreading concepts:

1. Thread creation and execution using ExecutorService and Runnable interface.
2. Thread synchronization using the synchronized keyword to ensure mutual exclusion of shared resources.
3. Thread sleep using the Thread.sleep() method to simulate processing time.
4. Thread pool management using ExecutorService, which allows efficient reuse of threads to improve performance.
5. Proper termination of threads using shutdown() and awaitTermination() methods to avoid blocking the program indefinitely.*/

public class MultiThreadingConcepts {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        // Create and submit tasks
        for (int i = 0; i < 10; i++) {
            executor.submit(new Task(i));
        }
        // Shutdown the executor
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Task implements Runnable {
        private int taskId;

        public Task(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {
            // Example of multithreading concepts:
            // Synchronization
            synchronized (MultiThreadingConcepts.class) {
                System.out.println("Starting task " + taskId);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Ending task " + taskId);
            }
        }
    }
}
