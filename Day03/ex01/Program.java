package ex01;

public class Program {
    public static void main(String[] args) {
        int count = Integer.parseInt(args[0].substring(8));
        EggHenPrinter printer = new EggHenPrinter();

        Thread eggThread = new Thread(new Egg(count, printer));
        Thread henThread = new Thread(new Hen(count, printer));

        eggThread.start();
        henThread.start();
    }
}

