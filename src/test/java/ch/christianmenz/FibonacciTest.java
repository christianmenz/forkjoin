package ch.christianmenz;

import org.junit.Test;

import static org.junit.Assert.*;

public class FibonacciTest {

    @Test
    public void shouldCalculateFibonacci() {
        Fibonacci fibonacci = new Fibonacci();
        long start = System.currentTimeMillis();
        System.out.println(fibonacci.f(10_000_000));
        System.out.println(System.currentTimeMillis() - start);
    }

}