package ex05;

public class Program {
    public static void main(String[] args) throws Exception {
        Menu menu = new Menu(args.length > 0 && args[0].equals("--profile=dev"));
        menu.start();
    }
}
