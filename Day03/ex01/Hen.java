package ex01;

public class Hen implements Runnable {
    private int count;
    private EggHenPrinter printer;

    public Hen(int count, EggHenPrinter printer) {
        this.count = count;
        this.printer = printer;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            printer.printHen();
        }
    }
}
