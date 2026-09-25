import java.util.Random;

public class MultiThreadNumberProcessing {

    static int number;
    static boolean newNumber = false;

    // Thread 1: Generates a random number every second
    static class NumberGenerator extends Thread {
        Random random = new Random();

        public void run() {
            while (true) {
                synchronized (MultiThreadNumberProcessing.class) {
                    number = random.nextInt(100) + 1;
                    newNumber = true;

                    System.out.println("\nGenerated Number: " + number);

                    MultiThreadNumberProcessing.class.notifyAll();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    // Thread 2: Computes square if number is even
    static class EvenProcessor extends Thread {
        public void run() {
            while (true) {
                synchronized (MultiThreadNumberProcessing.class) {
                    try {
                        MultiThreadNumberProcessing.class.wait();
                    } catch (InterruptedException e) {
                        break;
                    }

                    if (newNumber && number % 2 == 0) {
                        System.out.println("Even Number - Square: " + (number * number));
                        newNumber = false;
                    }
                }
            }
        }
    }

    // Thread 3: Computes cube if number is odd
    static class OddProcessor extends Thread {
        public void run() {
            while (true) {
                synchronized (MultiThreadNumberProcessing.class) {
                    try {
                        MultiThreadNumberProcessing.class.wait();
                    } catch (InterruptedException e) {
                        break;
                    }

                    if (newNumber && number % 2 != 0) {
                        System.out.println("Odd Number - Cube: " + (number * number * number));
                        newNumber = false;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        NumberGenerator generator = new NumberGenerator();
        EvenProcessor even = new EvenProcessor();
        OddProcessor odd = new OddProcessor();

        generator.start();
        even.start();
        odd.start();
    }
}
