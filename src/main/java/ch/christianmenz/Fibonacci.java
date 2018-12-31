package ch.christianmenz;

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

public class Fibonacci {

    public BigInteger f(int n) {
        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;

        int h = (n + 1) / 2;

        RecursiveTask<BigInteger> f0$ = new RecursiveTask<BigInteger>() {
            @Override
            protected BigInteger compute() {
                return f(h - 1);
            }
        };
        f0$.fork();

        BigInteger f1 = f(h);
        BigInteger f0 = f0$.join();

        if (n % 2 == 1) {
            BigInteger a = f0.multiply(f0);
            BigInteger b = f1.multiply(f1);
            return a.add(b);
        } else {
            return f0.shiftLeft(1).add(f1).multiply(f1);
        }
    }
}
