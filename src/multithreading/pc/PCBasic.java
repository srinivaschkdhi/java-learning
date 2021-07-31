package multithreading.pc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

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

    }
}
