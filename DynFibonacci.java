package cop2805;
//second class to implement the dynamic programming to solve the Fibonacci sequence
public class DynFibonacci extends Thread {
    int n;
    long Time;
    int result;

    public void setN(int n) {
        this.n = n;
    }

    @Override
    public void run() { //run function where I execute the algorithm & time how long it takes
        long startTime = System.currentTimeMillis();
        result = fibonacci(n);
        long endTime = System.currentTimeMillis();
        Time = endTime - startTime;
        System.out.println("Dynamic Thread found the answer: " + result + 
                           " calculated in " + Time + " ms.");
    }

     int fibonacci(int n) { //dynamic pseudocode
        if (n == 0) return 0;
        if (n == 1) return 1;
        int v1 = 0, v2 = 1, v3 = 0;
        for (int i = 2; i <= n; i++) {
            v3 = v1 + v2;
            v1 = v2;
            v2 = v3;
        }
        return v3;
    }
}
