package ex01;

public class Egg implements Runnable {
    private int count;
    private EggHenPrinter printer;

    public Egg(int count, EggHenPrinter printer) {
        this.count = count;
        this.printer = printer;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            printer.printEgg();
        }
    }
}
