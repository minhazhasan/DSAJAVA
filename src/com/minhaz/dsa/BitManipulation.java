package com.minhaz.dsa;

public class BitManipulation {

    public static boolean isPowerOf2(int n){
        return (n != 0) & (((n & (n - 1))) == 0);
    }

    // T(n) = O(n)
    public static int parity(int n){
        int res = 0;
        while (n != 0){
            res ^= (n & 1);
            n >>>= 1;
        }
        return res;
    }

    // T(n) = O(k) k = number of set bits
    public static int parityFaster(int n){
        int res = 0;
        while (n != 0){
            res ^= 1;
            n = n & (n - 1);
        }
        return res;
    }


}
