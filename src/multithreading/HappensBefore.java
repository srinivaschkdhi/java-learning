package multithreading;

public class HappensBefore {
    int a = 0, b = 0, c = 0;
    volatile int x = 0;

    public void writerThread() {
        a = 1;
        b = 1;
        c = 1;

        x = 1;
    }
//all the values which were updated before synchronized have to be updated and visible after reading of synchronized

    public void readerThread() {
        int r2 = x;

        int d1 = a;
        int d2 = b;
        int d3 = c;
    }
}

class HappensBefore2 {
    int a = 0, b = 0, c = 0;
    int x = 0;

    public void writerThread2() {
        a = 1;
        b = 1;
        c = 1;

        synchronized (this) {
            x = 1;
        }
    }
//all the values which were updated before synchronized have to be updated and visible after reading of synchronized
    public void readerThread2() {
        synchronized (this) {
            int r2 = x;
        }

        int d1 = a;
        int d2 = b;
        int d3 = c;
    }
}
//JMM is a specification which guarantees visibility of fields(aka happens before)
//admist reording of instructions