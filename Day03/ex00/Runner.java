package ex00;

public class Runner implements Runnable {
    private Integer count;
    private String name;

    public Runner(String name, Integer count) {
        this.name = name;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println(name);
        }
    }
}
