package multithreading;

public class SampleThreads {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Task());
        thread1.start();
        System.out.println("Thread Name: "+Thread.currentThread().getName());


        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Task());
            thread.start();
        }
        System.out.println("Thread Name: "+Thread.currentThread().getName());
    }
}
class Task implements Runnable{
    @Override
    public void run() {
        System.out.println("Thread Name: "+Thread.currentThread().getName());
    }
}
