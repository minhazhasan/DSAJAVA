package com.minhaz.dsa;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Problems {
	public String RPN(String[] tokens) {
		Stack<Integer> stack = new Stack<Integer>();
		
		for(String token : tokens) {
			
			if(!operator(token)) {
				try {
					stack.add(Integer.parseInt(token));
				} catch(NumberFormatException e) {
					return e.getMessage();
				}
			} else {
				
				if(stack.size() >= 2) {
					int val2 = stack.pop();
					int val1 = stack.pop();
					stack.add(calculate(token, val1, val2));
				} else {
					return Integer.toString(0);
				}
			}
		}
		
		return stack.pop().toString();
	}
	
	private boolean operator(String op) {
		return op.equals("/") || op.equals("*") 
				|| op.equals("+") || op.equals("-");
	}
	
	private int calculate(String token, int val1, int val2) {
		int result;
		switch(token) {
			case "/":
				if(val2 != 0)
					result = val1 / val2;
				else
					result = 0;
				break;
				
			case "*":
				result = val1 * val2;
				break;
			case "+":
				result = val1 + val2;
				break;
			case "-":
				result = val1 - val2;
				break;
			default:
				result = 0;
				break;
		}
		
		return result;
	}
	
	public LocalDate dateFromYear(int year) {

		YearMonth ym = YearMonth.of(year, Month.OCTOBER);
		LocalDate d = ym.atDay(1).with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.TUESDAY));

		return d;
	}

	public char firstNonRepeatingCharacters(String s){
		Map<Character, Integer> freq = new LinkedHashMap<>();
		char[] arr = s.toCharArray();
		for(char c : arr){
			freq.putIfAbsent(c, 0);
			freq.computeIfPresent(c, (k, v) -> v + 1);
		}

		char res = '_';
		for(Map.Entry<Character, Integer> entry : freq.entrySet()){
			if(entry.getValue() == 1) {
				res = entry.getKey();
				break;
			}
		}

		return res;
	}

	public int[] twoSum(int[] nums, int target){
		HashMap<Integer, Integer> map = new HashMap<>();
		int[] res = new int[2];
		for (int i = 0; i < nums.length; i++) {
			int rem = target - nums[i];
			if(map.containsKey(rem)){
				res[0] = map.get(rem);
				res[1] = i;
				break;
			}
			map.put(nums[i], i);
		}

		return res;
	}

	public int[] twoSumSorted(int[] nums, int target) {
		int res[] = new int[2];
		for(int i = 0; i < nums.length; i++){
			int key = target - nums[i];
			int idx = Arrays.binarySearch(nums, key);
			int searchIdx = (idx == i) ? idx + 1 : idx;
			if(searchIdx >= 0 && i < searchIdx){
				res[0] = i + 1;
				res[1] = searchIdx + 1;
				break;
			}
		}

		return res;
	}

	public int[][] spiralMatrix(int n){
		int left = 0, right = n - 1, top = 0, bottom = n - 1;
		int[][] result = new int[n][n];
		int count = 1;

		while (left <= right && top <= bottom){
			// from left to right
			for (int i = left; i <= right; i++)
				result[top][i] = count++;
			// top row done, increment top to reach next row
			top++;

			// from top right column to bottom
			for (int i = top; i <= bottom; i++)
				result[i][right] = count++;

			// rightmost column done, decrement right
			right--;

			// bottom row right to left
			for (int i = right; i >= left; i--)
				result[bottom][i] = count++;

			// bottom row done, decrement bottom
			bottom--;

			// leftmost column bottom to top
			for (int i = bottom; i >= top; i--)
				result[i][left] = count++;

			// leftmost column done, increment left
			left++;

		}

		return result;
	}

	public List<Integer> spiralPrint(int[][] matrix){

		List<Integer> result = new LinkedList<>();
		if(matrix.length == 0) return result;
		int left = 0, right = matrix[0].length - 1;
		int top = 0, bottom = matrix.length - 1;


		while (left <= right && top <= bottom){
			// top row from left to right
			for (int i = left; i <= right; i++)
				result.add(matrix[top][i]);

			// top row done, increment top to reach next row
			top++;

			// rightmost column from top to bottom
			for (int i = top; i <= bottom; i++)
				result.add(matrix[i][right]);

			// rightmost column done, decrement right
			right--;

			// bottom row right to left
			for (int i = right; i >= left && bottom > top; i--)
				result.add(matrix[bottom][i]);

			// bottom row done, decrement bottom
			bottom--;

			// leftmost column bottom to top
			for (int i = bottom; i >= top; i--)
				result.add(matrix[i][left]);

			// leftmost column done, increment left
			left++;

		}

		return result;
	}

	public int buySellStockDC(int[] arr){
		return buySellStockDCHelper(arr, 0);
	}

	public int buySellStockDCHelper(int[] arr, int maxProfit){
		if(arr.length == 1)
			return 0;
		int mid = arr.length / 2;

		int[] leftSubArray = Arrays.copyOfRange(arr, 0, mid);
		int[] rightSubArray = Arrays.copyOfRange(arr, mid, arr.length);

		int left = buySellStockDCHelper(leftSubArray, maxProfit);
		int right = buySellStockDCHelper(rightSubArray, maxProfit);

		int midMax = Arrays.stream(rightSubArray).max().getAsInt() - Arrays.stream(leftSubArray).min().getAsInt();

		return Math.max(Math.max(left, right), midMax);
	}

	public List<Integer> plusOne(List<Integer> arr){
		int n = arr.size() - 1;
		arr.set(n, arr.get(n) + 1);
		for(int i = n; i > 0 && arr.get(i) == 10; i--){
			arr.set(i, 0);
			arr.set(i - 1, arr.get(i - 1) + 1);
		}
		if(arr.get(0) == 10) {
			arr.set(0, 0);
			arr.add(0, 1);
		}
		return arr;
	}

//	private int maxProfit(int[] arr, int[] leftSubArray, int[] rightSubArray, int leftMin, int rightMax) {
//		return 0;
//	}

	public int kthSmallest(int[] arr, int k){
		return 0;
	}

	public int binaryGap(int N){
		int i = 0, j = 0, gap = 0;
		int oneCount = 0;
		while (N != 0){
			int rem = N % 2;
			if(rem == 1) oneCount += 1;
			if(oneCount < 2) i++;
		}

		return 0;
	}




}
