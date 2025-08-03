package exception_and_concurrency;

public class ThreadPriorityDemo {
    public static void main(String[] args) {
        System.out.println("\n--- Thread Priority Demo ---");
        // NOTE: Priority is only a HINT to the scheduler, not a strict guarantee of
        // order.

        PriorityThread lowPriorityThread = new PriorityThread();
        lowPriorityThread.setName("Low-Priority-Thread");

        PriorityThread highPriorityThread = new PriorityThread();
        highPriorityThread.setName("High-Priority-Thread");

        lowPriorityThread.setPriority(Thread.MIN_PRIORITY); // Usually 1
        highPriorityThread.setPriority(Thread.MAX_PRIORITY);// Usually 10

        // The high-priority thread will likely get more CPU
        // time and finish first, but this can vary depending on the OS scheduler.
        lowPriorityThread.start();
        highPriorityThread.start();
    }
}
