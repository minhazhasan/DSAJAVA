package com.minhaz.dsa;

import java.util.Comparator;

public class Pair<X, Y> {
    public X x;
    public Y y;

    public Pair(X x, Y y){
        this.x = x;
        this.y = y;
    }


//    @Override
//    public int compare(Pair t1, Pair t2) {
//        Integer x1 = (int) t1.x;
//        Integer x2 = (int) t2.x;
//        return x1 < x2 ? x1 : x2;
//    }
}
