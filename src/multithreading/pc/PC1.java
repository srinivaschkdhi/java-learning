package multithreading.pc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class PC1 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Item> queue = new ArrayBlockingQueue<>(10); // Handles concurrent thread access

        //Producer
        final Runnable producer = () -> {
            while (true){
                try {
                    queue.put(createItem());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(producer).start();
        new Thread(producer).start();

        //Consumer
        final Runnable consumer = () -> {
            while(true){
                Item item = null;
                try {
                    item = queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(item);
            }
        };

        new Thread(consumer).start();
        new Thread(consumer).start();

        Thread.sleep(1000);
    }

    public static Item createItem(){
        return new Item("apple"+System.currentTimeMillis());
    }
}

class Item {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
       return  name;
    }
}
