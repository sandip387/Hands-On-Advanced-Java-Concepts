package $02_exception_and_concurrency;

public class PrintTable {
    // The 'synchronized' keyword acts like a "lock". When one thread enters this
    // method, no other thread can enter it (on the same object) until the first
    // thread is finished. This prevents the outputs from mixing up.
    public synchronized void printTable(int n) {
        System.out.println("\n--- Printing Table for " + n + " ---");
        for (int i = 1; i <= 5; i++) {
            System.out.println(n + " x " + i + " = " + (n * i));
            try {
                // Adding small delay to make the effect of synchronization more obvious.
                // Without 'synchronized', the other thread would jump in during this sleep
                // time.
                Thread.sleep(400);
            } catch (Exception e) {
                System.out.println("Thread was interrupted: " + e.getMessage());
            }
        }
    }
}
