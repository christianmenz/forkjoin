package ch.christianmenz.exceptions;

import java.util.concurrent.RecursiveTask;

public class ExceptionHandling {


    public static void main(String[] args) {
        RecursiveTask<String> task$ = new RecursiveTask<String>() {
            @Override
            protected String compute() {
                System.out.println("Going to throw up!");
                throw new NullPointerException();
            }
        };

        task$.fork();

        task$.join(); // I should get the exception here

        System.out.println("done");

    }
}
