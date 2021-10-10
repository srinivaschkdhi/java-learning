package multithreading.conditions;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WaitNotifyCondition {
    String monitor;
    Lock lock = new ReentrantLock();
    Condition conditionMet = lock.newCondition();

    public synchronized void execute(){
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            monitor.notify();
            monitor.notifyAll();
    }

    public void execute2(){
        lock.lock();

        try {
            conditionMet.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        conditionMet.signal();
        conditionMet.signalAll();

        lock.unlock();
    }
}
