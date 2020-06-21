package com.minhaz.dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Strings {
    public static int stoi(String str) {
        int num = 0;
        int length = str.length();
        boolean negative = str.charAt(0) == '-' ? true : false;
        for (int i = negative ? 1 : 0; i < length; i++) {
            int digit = str.charAt(i) - '0';
            num = num * 10 + digit;
        }
        num = negative ? -num : num;
        return num;
    }

    public static String itos(int num) {
        String isNegative = num < 0 ? "-" : "";
        StringBuilder str = new StringBuilder("");
        while (num != 0) {
            str.insert(0, Math.abs(num % 10));
            num = num / 10;
        }

        str.insert(0, isNegative);
        return str.toString();
    }

    public static String baseConversion(String numAsString, int b1, int b2) {
        int num = 0;
        boolean isNegative = numAsString.startsWith("-");
        for (int i = isNegative ? 1 : 0; i < numAsString.length(); i++) {
            num *= b1;
            char c = numAsString.charAt(i);
            num += Character.isDigit(c) ? c - '0' : c - 'A' + 10;
        }

        StringBuilder str = new StringBuilder();
        while (num != 0) {
            int rem = num % b2;
            char c = (char) (rem >= 10 ? 'A' + rem - 10 : '0' + rem);
            str.insert(0, c);
            num /= b2;
        }

        return str.toString();
    }

    public static int SpreadSheetColumnDecoding(String str) {
        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            int c = str.charAt(i);
            result = 26 * result + (c - 'A' + 1);
        }
        return result;
    }

    public static String SpreadSheetColumnEncoding(int num) {
        StringBuilder result = new StringBuilder();
        while (num > 0) {
            int rem = (num - 1) % 26;
            result.append((char) ('A' + rem));
            num = (num - 1) / 26;
        }
        return result.toString();
    }

    public static boolean isUnique(String str) {

        if (str.length() > 128)
            return false;

        boolean[] char_set = new boolean[128];
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (char_set[val])
                return false;
            char_set[val] = true;
        }

        return true;

    }

    public static boolean checkPermutation(String str1, String str2) {
        if (str1.length() != str2.length())
            return false;
        boolean[] chars = new boolean[128];
        for (int i = 0; i < str1.length(); i++) {
            int val = str1.charAt(i);
            if (!chars[val])
                chars[val] = true;
        }

        for (int i = 0; i < str1.length(); i++) {
            int val = str2.charAt(i);
            if (!chars[val])
                return false;
        }
        return true;
    }

    public static int replaceAndRemove(int size, char[] s) {
        // forward iteration: remove "b"s and count the iteration of "a"s
        int writeIdx = 0, aCount = 0;
        for (int i = 0; i < size; i++) {
            if (s[i] != 'b')
                s[writeIdx++] = s[i];
            if (s[i] == 'a')
                aCount++;
        }

        int curIdx = writeIdx - 1;
        writeIdx = writeIdx + aCount - 1;
        final int finalSize = writeIdx + 1;

        while (curIdx >= 0) {
            if (s[curIdx] == 'a') {
                s[writeIdx--] = 'd';
                s[writeIdx--] = 'd';
            } else {
                s[writeIdx--] = s[curIdx];
            }
            --curIdx;
        }
        return finalSize;
    }

    public static String reverseAllWordsInASentence(String str) {
        String[] arr = str.split(" ");
        for (int i = 0; i < arr.length; i++) {
            String revS = reverse(arr[i]);
            arr[i] = revS;
        }

        return String.join(" ", arr);
    }

    public static String reverse(String str) {
        int i = 0, j = str.length() - 1;
        char[] arr = str.toCharArray();
        while (i < j) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }

        return String.copyValueOf(arr);
    }

    public static List<String> phoneMnemonic(String phoneNumber) {
        List<String> mnemonics = new ArrayList<>();
        Map<Integer, String> mapping = Stream
                .of(new Object[][] { { 0, "0" }, { 1, "1" }, { 2, "ABC" }, { 3, "DEF" }, { 4, "GHI" }, { 5, "JKL" },
                        { 6, "MNO" }, { 7, "PQRS" }, { 8, "TUV" }, { 9, "WXYZ" } })
                .collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1]));

        char[] partialMnemonic = new char[phoneNumber.length()];
        phoneMnemonicHelper(mapping, partialMnemonic, 0, phoneNumber, mnemonics);
        return mnemonics;
    }

    // T(n) = O(4^nn)
    private static void phoneMnemonicHelper(Map<Integer, String> mapping, char[] partialMnemonic, int digit,
            String phoneNumber, List<String> mnemonics) {
        if (digit == phoneNumber.length()) {
            mnemonics.add(new String(partialMnemonic));
        } else {
            for (int i = 0; i < mapping.get(phoneNumber.charAt(digit) - '0').length(); i++) {
                char c = mapping.get(phoneNumber.charAt(digit) - '0').charAt(i);
                partialMnemonic[digit] = c;
                phoneMnemonicHelper(mapping, partialMnemonic, digit + 1, phoneNumber, mnemonics);
            }
        }
    }

    public static boolean WordSearch(char[][] board, String word) {
        int charIndex = 0;
        boolean flag = false;
        int[][] visited = new int[board.length][board[0].length];

        for (int i = 0; i < board.length && charIndex < word.length(); i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(charIndex)) {
                    flag = traceWord(i, j, charIndex + 1, board, word, flag, visited);
                    if (flag)
                        return flag;
                }
            }
        }

        return false;
    }

    private static boolean traceWord(int r, int c, int charIndex, char[][] board, String word, boolean flag,
            int[][] visited) {

        int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

        if (visited[r][c] != 0)
            return false;

        if (charIndex == word.length())
            return true;

        visited[r][c] = board[r][c];

        for (int[] action : dir) {
            int row = r + action[0];
            int col = c + action[1];
            if (row >= 0 && row < board.length && col >= 0 && col < board[0].length
                    && board[row][col] == word.charAt(charIndex))
                flag = traceWord(row, col, charIndex + 1, board, word, flag, visited);
            if (flag)
                return true;
        }

        if (!flag)
            visited[r][c] = 0;

        return flag;

    }

}
