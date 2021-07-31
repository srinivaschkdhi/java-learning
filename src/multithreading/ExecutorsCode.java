package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsCode {
    public static void main(String[] args) {
        //create the pool
        ExecutorService service = Executors.newFixedThreadPool(10);

        //submit the tasks for execution
        for (int i = 0; i < 10; i++)
        {
            service.execute(new Task());
        }
        System.out.println("Thread Name: "+Thread.currentThread().getName());

        service.shutdown();
    }
}

