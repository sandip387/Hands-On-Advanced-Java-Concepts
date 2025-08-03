package exception_and_concurrency;

public class PriorityThread extends Thread {
    public void run(){
        for(int i = 1; i <= 5; i++){
            System.out.println(Thread.currentThread().getName() + " Priority: " + Thread.currentThread().getPriority() + " Count: " + i);
        }
    }
}
