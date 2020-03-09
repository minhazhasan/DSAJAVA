package com.minhaz.dsa;

import java.util.HashMap;
import java.util.Map;

public class MyClass {
    private Map<String,Integer> map;

    public MyClass() {
        map = new HashMap<>();
        map.put("foo", 1);
        map.put("bar", 3);
    }

    public int getValue(String input, int numRetries) throws Exception {
        //int num = 0;
        try {
            return map.getOrDefault(input, 0);
        }
        catch (Exception e) {
            if (numRetries > 3) {
                throw e;
            }
            System.out.println("Called: " + numRetries);
            return getValue(input, numRetries + 1);
        }

        //return num;
    }
}
