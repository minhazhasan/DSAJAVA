package com.minhaz.dsa;

import java.util.HashMap;
import java.util.Map;

public class Strings {
    public static int stoi(String str){
        int num = 0;
        int length = str.length();
        boolean negative = str.charAt(0) == '-' ? true : false;
        for (int i = negative ? 1 : 0; i < length; i++){
            int digit = str.charAt(i) - '0';
            num = num * 10 + digit;
        }
        num = negative ? -num : num;
        return num;
    }

    public static String itos(int num){
        String isNegative = num < 0 ? "-" : "";
        StringBuilder str = new StringBuilder("");
        while (num != 0){
            str.insert(0, Math.abs(num % 10));
            num = num / 10;
        }

        str.insert(0, isNegative);
        return str.toString();
    }

    public static String baseConversion(String numAsString, int b1, int b2){
        int num = 0;
        boolean isNegative = numAsString.startsWith("-");
        for (int i = isNegative ? 1 : 0; i < numAsString.length(); i++){
            num *= b1;
            char c = numAsString.charAt(i);
            num += Character.isDigit(c) ? c - '0' : c - 'A' + 10;
        }

        StringBuilder str = new StringBuilder();
        while (num != 0){
            int rem = num % b2;
            char c = (char)(rem >= 10 ? 'A' + rem - 10 : '0' + rem);
            str.insert(0, c);
            num /= b2;
        }

        return str.toString();
    }

    public static int SpreadSheetColumnDecoding(String str){
        int result = 0;
        for (int i = 0; i < str. length(); i++){
            int c = str.charAt(i);
            result = 26 * result + (c - 'A' + 1);
        }
        return result;
    }

    public static String SpreadSheetColumnEncoding(int num){
        StringBuilder result = new StringBuilder();
        while (num > 0){
            int rem = (num - 1) % 26;
            result.append((char)('A' + rem));
            num = (num - 1) / 26;
        }
        return result.toString();
    }

    

}
