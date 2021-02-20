package com.company.fib;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Fibonacci1 {


    private static int fib(int n){
        System.out.println(1);
        if(n==0) return 0;
        if(n==1) return 1;
        int leftFib = fib(n - 1);
        int rightFib = fib(n - 2);
        return leftFib + rightFib;
    }

}
