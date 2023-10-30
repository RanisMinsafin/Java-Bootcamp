package ex02;

public class SummationTask implements Runnable {
    private final String THREAD_OUTPUT = "Thread %d: from %d to %d sum is %d\n";
    private int startIndex;
    private int endIndex;
    private int threadNumber;
    private int[] array;
    private static int sumByThreads = 0;


    public SummationTask(int startIndex, int endIndex, int threadNumber, int[] array) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.threadNumber = threadNumber;
        this.array = array;
    }

    @Override
    public void run() {
        int sum = 0;
        for (int i = startIndex; i <= endIndex; i++) {
            sum += array[i];
        }
        sumByThreads += sum;

        System.out.printf(THREAD_OUTPUT, threadNumber, startIndex, endIndex, sum);
        if (endIndex == array.length - 1) {
            System.out.println("Sum by threads: " + sumByThreads);
        }
    }
}
