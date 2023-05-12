package sbu.cs.CalculatePi;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PiCalculator {

    /**
     * Calculate pi and represent it as a BigDecimal object with the given floating point number (digits after . )
     * There are several algorithms designed for calculating pi, it's up to you to decide which one to implement.
     Experiment with different algorithms to find accurate results.

     * You must design a multithreaded program to calculate pi. Creating a thread pool is recommended.
     * Create as many classes and threads as you need.
     * Your code must pass all of the test cases provided in the test folder.

 //  * @param floatingPoint the exact number of digits after the floating point
     * @return pi in string format (the string representation of the BigDecimal object)
     */

    public static final int max = 10_000;
    public static final int step = 10_000_000;
    public static int threadCount = 5;
    public static int current = 0;
    public static BigDecimal[] numbers = new BigDecimal[max];

    static class PIcalculateThread extends Thread {


        int start;
        public PIcalculateThread(int start) {
            this.start = start;
        }
        @Override
        public void run() {

            while (true) {
                try {
                    compute();
                    start = increment();
                } catch (IndexOutOfBoundsException e) {
                    break;
                }
            }
        }
        private synchronized int increment() {
            current += step;
            return current;
        }
        private void compute() {
            MathContext mc = new MathContext(1000);
            for (int i = start; i < start + step && i < max; i++) {
                BigDecimal a = BigDecimal.valueOf(i + 0.5);
                BigDecimal x = a.divide(BigDecimal.valueOf(max), mc);
                x = x.multiply(x, mc);
                BigDecimal b = x.add(BigDecimal.valueOf(1));
                BigDecimal c = b.multiply(BigDecimal.valueOf(max), mc);
                BigDecimal d = BigDecimal.valueOf(4);
                numbers[i] = d.divide(c, mc);
                if (i + 1 == max) throw new IndexOutOfBoundsException();
            }
        }


        //**commented lines are the second way **


//        MathContext mc = new MathContext(1000);
//        private final int threadCount;
//        private final int threadRemainder;
//        private int n;
//        BigDecimal sum = BigDecimal.valueOf(0);
//
//        public PIcalculateThread(int threadCount, int threadRemainder, int n) {
//            this.threadCount = threadCount;
//            this.threadRemainder = threadRemainder;
//            this.n = n;
//        }
//        @Override
//        public void run() {
//            for (int i = 0; i <= n; i++) {
//                if (i % threadCount == threadRemainder) {
//                    BigDecimal pow = BigDecimal.valueOf(Math.pow(-1, i));
//                    BigDecimal dividen = BigDecimal.valueOf((2 * i) + 1);
//                    BigDecimal rslt = pow.divide(dividen, mc);
//                    sum = sum.add(rslt);
//                }
//            }
//        }
//        public BigDecimal getSum() {
//            return sum;
//        }
    }


    public static String calculate(int floatingPoint)
    {

        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new PIcalculateThread(i * current);
        }
        for (int i = 0; i < threadCount; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threadCount; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        BigDecimal sum = BigDecimal.valueOf(0);
        for (int i = 0; i < max; i++) {
            sum = sum.add(numbers[i]);
        }
        sum = sum.setScale(floatingPoint, RoundingMode.HALF_EVEN);

        return String.valueOf(sum);


//        ExecutorService threadPool = Executors.newFixedThreadPool(100_000)
//        // TODO
//        int threadCount = 100;
//        int N = 100_000;
//        PIcalculateThread[] threads = new PIcalculateThread[threadCount];
//        for (int i = 0; i < threadCount; i++) {
//            threads[i] = new PIcalculateThread(threadCount, i, N);
//            threads[i].start();
//        }
//        for (int i = 0; i < threadCount; i++) {
//            try {
//                threads[i].join();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        BigDecimal pi = BigDecimal.valueOf(0);
//        for (int i = 0; i < threadCount; i++) {
//            pi = pi.add(threads[i].getSum());
//        }
//
//        pi = pi.multiply(BigDecimal.valueOf(4));
//        pi = pi.setScale(floatingPoint, RoundingMode.HALF_EVEN);
//
//        return String.valueOf(pi);
    }

    public static void main(String[] args) throws InterruptedException {
        // Use the main function to test the code yourself

        Scanner scanner = new Scanner(System.in);
        int floating = scanner.nextInt();
        calculate(floating);

    }
}
