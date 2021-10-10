package multithreading.pc;

import java.util.List;
import java.util.concurrent.*;

public class PCBasic {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

        final Runnable producer = () -> {
//            queue.put("data");
        };

        final Runnable consumer = () -> {
//           String data =  queue.take();
        };

        new Thread(producer).start();
        new Thread(consumer).start();

        LinkedBlockingDeque<Integer> linkedBlockingDeque = new LinkedBlockingDeque<>();
        SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();


        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Runnable> runnables = executorService.shutdownNow();

        for (Runnable runnable : runnables) {
            System.out.println(runnables);
        }
    }
}
