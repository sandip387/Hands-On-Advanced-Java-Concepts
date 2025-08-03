package exception_and_concurrency;

public class MyThread extends Thread {
    private PrintTable table;
    private int number;

    MyThread(PrintTable table, int number) {
        this.table = table;
        this.number = number;
    }

    // The run() method runs the thread that will execute in a separate path of
    // execution when we call start().
    @Override
    public void run() {
        table.printTable(number);
    }
}
