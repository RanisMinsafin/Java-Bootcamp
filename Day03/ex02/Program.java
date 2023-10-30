package ex02;

import java.util.Arrays;
import java.util.Random;

public class Program {

    public static void main(String[] args) {
        int arraySize = Integer.parseInt(args[0].substring(12));
        int threadsCount = Integer.parseInt(args[1].substring(15));

        Random random = new Random();
        int[] array = random.ints(arraySize, 0, 1000).toArray();

        int defaultSum = Arrays.stream(array).sum();
        System.out.println("Sum: "+ defaultSum);

        int step = arraySize / threadsCount;
        Thread[] threads = new Thread[threadsCount];
        int startIndex = 0;
        for (int i = 0; i < threadsCount; i++) {
            if(i != 0){
                startIndex = i * (step + 1);
            }
            int endIndex = (i == threadsCount - 1) ? arraySize - 1 : startIndex + step;
            int threadNumber = i + 1;

            threads[i] = new Thread(new SummationTask(startIndex, endIndex, threadNumber, array));
            threads[i].start();
        }
    }
}
