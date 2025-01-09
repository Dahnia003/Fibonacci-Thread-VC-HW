package cop2805;

public class FibonacciThreadHW {
    public static void main(String[] args) {
        int n = 40; // setting n equals 40 as instructed

        recFibonacci recursiveThread = new recFibonacci();
        recursiveThread.setN(n);
        recursiveThread.start();

        DynFibonacci dynamicThread = new DynFibonacci();
        dynamicThread.setN(n);
        dynamicThread.start();

        // Wait for both threads to finish
        try {
            recursiveThread.join();
            dynamicThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
