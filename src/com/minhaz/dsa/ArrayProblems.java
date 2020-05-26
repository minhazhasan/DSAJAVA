package com.minhaz.dsa;

import java.lang.reflect.Array;
import java.util.*;

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

	public static List<Integer> plusOne(LinkedList<Integer> A){
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

	public static int[] multiply(int[] a, int[] b){
		// Step 1: create an array of length a.length + b.length
		// multiplied results can be at most length of a & b
		int[] result = new int[a.length + b.length];

		// Step 2: store the sign bit
		int sign = a[0] * b[0] < 0 ? -1 : 1;
		a[0] = Math.abs(a[0]);
		b[0] = Math.abs(b[0]);

		// Step 3: set the cross border
		int rt = result.length - 1;

		for(int i = b.length - 1; i >= 0; i--){
			int k = rt;
			for(int j = a.length - 1; j >= 0; j--){
				 int sum = b[i] * a[j] + result[k];
				 result[k--] = sum % 10;
				 result[k] = result[k] + sum / 10;
			}
//			if(carry > 0){
//				result[k] = carry;
//				carry = 0;
//			}
			rt--;
		}





		int leadingZeroLength = 0;
		for (int i = 0; i < result.length; i++){
			if(result[i] != 0){
				leadingZeroLength = i;
				break;
			}
		}

		result = Arrays.copyOfRange(result, leadingZeroLength, result.length);
		result[0] = result[0] * sign;

		return result;
	}

	public static boolean advArray(int[] arr){
		int furthestSofar = 0, lastIdx = arr.length - 1;
		for (int i = 0; i <= furthestSofar && furthestSofar < lastIdx; i++){
			int advance = i + arr[i];
			if(advance > furthestSofar){
				furthestSofar = advance;
			}
		}

		return furthestSofar >= lastIdx;
	}

	// need to verify
	public static int minStepNeededToAdvArray(int[] arr){
		int furthestReachSoFar = 0, lastIdx = arr.length - 1;
		int jump = 0;

		for(int i = 0; i <= furthestReachSoFar && furthestReachSoFar < lastIdx; i++){
			int advSoFar = i + arr[i];
			if(advSoFar > furthestReachSoFar){
				furthestReachSoFar = advSoFar;
				jump += 1;
			}
		}

		if(furthestReachSoFar >= lastIdx) return jump;
		return -1;
	}

	public static int[] deleteDuplicates(int[] nums){
		int j = 1, i = 1;
		while(i < nums.length && nums.length > 1){
			if(nums[i - 1] != nums[i]) nums[j++] = nums[i];
			i++;
		}

		System.out.println("Distinct elements: " + j);

		while (j < nums.length){
			nums[j++] = 0;
		}

		return nums;
	}

	// Algorithm: as we know the array is sorted, we are allowed keep
	// at most m duplicates. So, if we compare an element i with j - m distance apart,
	// we know that is the index we need to insert if we encounter a different element.
	// e.g A = [2,2,2,3,5], So, A[3] != A[2], (we start j and i from m because of the constraint),
	// we know that we need update the duplicate index j with A[3]'s value. On the contrary, if
	// A[2] == A[0], we know that, also, A[1] == A[2], because the array is sorted.
	// T(n) = O(n)
	public static int[] deleteDuplciatesAtMost(int[] nums, int m){
		int j = m, dupCounter = 1;
		if(nums.length == 0) return nums;
		for(int i = m; i < nums.length && m > 1; i++){
			if(nums[j - m] != nums[i]){
				int temp = nums[j];
				nums[j] = nums[i];
				nums[i] = temp;
				j++;
			}
		}

		System.out.println("Elements: " + j);

		return nums;
	}

	public static int BuySellStockOnce(int[] nums){
		int maxProfit = 0;
		int bestBuyIndex = nums[0];
		for(int i = 1; i < nums.length; i++){
			int profit = nums[i] - bestBuyIndex;
			if(profit > maxProfit) maxProfit = profit;
			if(nums[i] < bestBuyIndex) bestBuyIndex = nums[i];
		}

		return maxProfit;
	}

	public static int BuySellStockAtMostTwice(int[] nums){
		int maxProfit = 0;
		for (int i = 1; i < nums.length; i++){
			maxProfit = Math.max(maxProfit,
					BuySellStockOnce(Arrays.copyOfRange(nums, 0, i)) + BuySellStockOnce(Arrays.copyOfRange(nums, i + 1, nums.length)));
		}
		return maxProfit;
	}

	public static int longestEqualEntries(int[] nums){
		int longestLength = 1, j = 0, localLength = 1;
		for(int i = 1; i < nums.length; i++){
			if(nums[j] != nums[i]) {
				j++;
				localLength = 1;
			}
			else {
				localLength = i - j + 1;
				longestLength = Math.max(localLength, longestLength);
			}
		}

		return longestLength;
	}



}
