package multithreading.conditions;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Condition1 {
    private Lock lock = new ReentrantLock();
    private Condition conditionMet = lock.newCondition();

    public void method1(){
        lock.lock();

        try {
            conditionMet.await(); // suspend here
            // can now do dependant operations resume here
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void method2(){
        lock.lock();
        // do some operations
        conditionMet.signal();

        lock.unlock();
    }

    public String consume() throws InterruptedException {
        lock.lock();
        int count = 0;

        while(count == 0) //Spurious wake ups
            conditionMet.await();

        lock.unlock();

        return "";
    }
}
