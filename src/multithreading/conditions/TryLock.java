package multithreading.conditions;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TryLock {
    private static ReentrantLock lock = new ReentrantLock();

    private static void accessResource() {
        boolean lockAquired = lock.tryLock();
//        boolean lockQuired = lock.tryLock(1, TimeUnit.SECONDS);

        if (lockAquired) {
           try {
               // access resource
           }finally {
               lock.unlock();
           }
        } else{
            // do alternate things
        }

    }
}
