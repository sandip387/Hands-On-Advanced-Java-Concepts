package exception_and_concurrency;

public class SynchronizationDemo {
    public static void main(String[] args) {
        System.out.println("--- Thread Synchronization Demo ---");
        System.out.println("One thread will complete its table before the next begins.");

        // ONE instance of shared resource.
        PrintTable sharedTable = new PrintTable();

        // two threads, BOTH of them reference to exact same sharedTable object.
        MyThread t1 = new MyThread(sharedTable, 2);
        MyThread t2 = new MyThread(sharedTable, 5);

        // Start the threads. The JVM schedules them to run. The starting order can't be
        // predicted.
        t1.start();
        t2.start();
    }
}
