package com.minhaz.dsa;

public class BitManipulation {

    public static boolean isPowerOf2(int n){
        return (n != 0) & (((n & (n - 1))) == 0);
    }

}
