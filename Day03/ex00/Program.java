package ex00;

public class Program {
    public static void main(String[] args) {
        Integer count = Integer.parseInt(args[0].substring(8));
        Thread egg = new Thread(new Runner("Egg", count));
        egg.start();
        Thread hen = new Thread(new Runner("Hen", count));
        hen.start();

        for (int i = 0; i < count; i++) {
            System.out.println("Human");
        }
    }
}
