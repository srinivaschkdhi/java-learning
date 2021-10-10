package multithreading.futures;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Future1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
//        Callable<Integer> callable = () -> new Random().nextInt();
        ExecutorService service = Executors.newFixedThreadPool(10);
//      service.execute(callable);// compile time error. execute takes only Runnable
        Future<Integer> future = service.submit(new Task());
        Integer integer = future.get();//blocking operation. blocks main thread
        System.out.println("integer = " + integer);

        Integer integer1 = future.get(1, TimeUnit.SECONDS);// Wait for 1 second,if not finished throw TimeoutException

        future.cancel(false);
        future.isCancelled();
        future.isDone();



        List<Future> futureList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            future = service.submit(new Task());
            futureList.add(future);
        }

        for (int i = 0; i < 100; i++) {
            Future<Integer> future1 = futureList.get(i);
            Integer integer2 = future1.get();
        }
    }

    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            return new Random().nextInt();
        }
    }
}
