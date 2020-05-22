package com.minhaz.dsa;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayProblems {
	private int[] items;
	
	public ArrayProblems(int length) {
		items = new int[length];
	}
	
	public void print() {
		for(int i = 0; i < items.length; i++) {
			System.out.println(items[i]);
		}
	}

	// Problem Definition:
	// given pivot, arrange the in such a way that
	// the elements less than pivot should be on the left side
	// equal to the pivot should be on the middle
	// greater than the pivot should be on the right side.

	public static enum Color {RED, WHITE, BLUE}
	public static void DutchFlagPartitioning(List<Color> colors){
		//dfHelper(colors, 0, colors.size() - 1);
		dfIterative(colors, 3);
	}

	private static void dfHelper(List<Color> colors, int lo, int hi){

		if(lo >= hi) return;

		int lt = lo;
		int i = lo + 1;
		int gt = hi;
		int pivot = colors.get(lo).ordinal();
		while (i <= gt){
			if(colors.get(i).ordinal() < pivot) Collections.swap(colors, i++, lt++);
			else if(colors.get(i).ordinal() > pivot) Collections.swap(colors, i, gt--);
			else i++;
		}
		dfHelper(colors, lo, lt - 1);
		dfHelper(colors, gt + 1, hi);
	}

	private static void  dfIterative(List<Color> colors, int pivIdx){

		Color pivot = colors.get(pivIdx);
		int lt = 0;
		for (int i = 1; i < colors.size(); i++){
			if(colors.get(i).ordinal() < pivot.ordinal()) Collections.swap(colors, i, lt++);
		}

		int gt = colors.size() - 1;
		for(int i = gt; i >= 0 && colors.get(i).ordinal() >= pivot.ordinal(); i--){
			if(colors.get(i).ordinal() > pivot.ordinal()) Collections.swap(colors, i, gt--);
		}
	}

	public static String BinaryAddition(String s1, String s2){
		int i = s1.length() - 1;
		int j = s2.length() - 1;
		int carry = 0;

		StringBuilder str = new StringBuilder();

		while (i >= 0 || j >= 0){
			int bin1 = j >= 0 ? s2.charAt(j--) - '0' : 0;
			int bin2 = i >= 0 ? s1.charAt(i--) - '0' : 0;
			int sum = bin1 + bin2 + carry;
			str.insert(0, sum % 2);
			carry = sum / 2;
		}

		if(carry == 1) str.insert(0, 1);
		return str.toString();
	}

	public static List<Integer> plusOne(List<Integer> A){
		int n = A.size() - 1;
		A.set(n, A.get(n) + 1);
		for(int i = n; i > 0 && A.get(i) == 10; i--){
			A.set(i, 0);
			A.set(i - 1, A.get(i - 1) + 1);
		}

		// check the carry
		if(A.get(0) == 10){
			A.set(0, 0);
			A.add(0, 1);
		}

		return A;
	}

//	public static List<Integer> mult(List<Integer> num1, List<Integer> num2){
//		int carry = 0;
//		List<Integer> res = new ArrayList<>();
//		for (int i = num1.size() - 1; i >= 0; i--){
//			for (int j = num2.size() - 1; j >= 0; j--){
//				int sum = num1.get(i);
//			}
//		}
//	}

//	public static boolean advArray(){
//
//	}

}
