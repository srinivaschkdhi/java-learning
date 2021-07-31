package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsWithCoreCount {
    public static void main(String[] args) {
        //get count of available cores
        int coreCount = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(coreCount);

        //submit tasks for execution
        for (int i = 0; i < 100; i++) {
            service.execute(new CpuIntensiveTask());
        }
    }
}
class  CpuIntensiveTask implements Runnable{
    @Override
    public void run() {
        System.out.println("intensive work done");
    }
}