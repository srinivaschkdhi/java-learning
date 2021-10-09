package multithreading.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WithoutThreadLocal {
    public static void main(String[] args) {
        Runnable task1 = () -> {
            String birthDate = new UserService().birthDate(100);
            System.out.println(birthDate);
        };

        Runnable task2 = () -> {
            String birthDate = new UserService().birthDate(200);
            System.out.println(birthDate);
        };

        new Thread(task1).start();
        new Thread(task2).start();

        // two threads two simple date format objects




        for(int i = 0; i < 10;i++){
            int id = i;
            Runnable task = () -> {
                String birthDate = new UserService().birthDate(id);
                System.out.println(birthDate);
            };
            new Thread(task).start();
        }

        // 10 threads 10 simpledateformat objects

        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            int id = 1;
            Runnable task = () -> {
                String birthDate = new UserService().birthDate(id);
                System.out.println(birthDate);
            };
            threadPool.submit(task);
        }

        // 1000 tasks 10 threads 1000 simpledateformat objects

    }
}

class WithoutThreadLocal2{
    static ExecutorService threadPool = Executors.newFixedThreadPool(10);
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int id = i;
            Runnable task = () -> {
                String birthDate = new UserService().birthDate(id);
                System.out.println(birthDate);
            };
            threadPool.submit(task);
        }
        // 1 global simple date format object. 1000 tasks. data integrity problem.
        // if synchronized performance is low
    }
}
class UserService {
    public String birthDate(int userId) {
        Date birthDate = birthDateFromDB(userId);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(birthDate);
    }

    Date birthDateFromDB(int userId) {
        return new Date();
    }
}
