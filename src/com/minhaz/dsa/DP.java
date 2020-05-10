package com.minhaz.dsa;

import java.net.Inet4Address;
import java.util.*;

public class DP {
	static HashMap<Integer, Integer> memo = new HashMap<>();
	// Problems to finish
	public static void LCS(String s1, String s2) {}
	public static void LCSS(String s1, String s2) {}

	public static void frogJump(int[] k){}
	public static void maximumSumRectangle(int[][] matrix){}
	public static void LPS(String s){}
	public static void matrixParenthesization(int[][] m1, int[][] m2){}
	public static void weightedScheduling(int[] schedules, int[] weights){}


	// Completed Problems
	static int maxSum = Integer.MIN_VALUE;
	public static int maximumSumSubArray(int[] nums){
		return maxArrayHelper(nums);

	}


	private static int maxArrayHelper(int[] nums){
		int localSum = nums[0], startIdx = 0, endIdx = 0;
		int maxSum = Integer.MIN_VALUE;
		for (int i = 1; i < nums.length; i++){
			int tempSum = localSum + nums[i];
			if(tempSum > nums[i])
				localSum = tempSum;
			else {
				localSum = nums[i];
				startIdx = i;
			}

			if(localSum > maxSum){
				maxSum = localSum;
				endIdx = i;
			}
		}

		return maxSum;
	}

	public static int maxSumMatrixBruteForce(int[][] nums){
		int maxSum = Integer.MIN_VALUE;
		int localSum = 0;

		// for each block, from that block for each rectangle,
		// calculate the sum of that rectangle
		// T(n) = O(n^6)
		for(int i = 0; i < nums.length; i++){
			for(int j = 0; j < nums[0].length; j++){
				for(int i1 = i; i1 < nums.length; i1++){
					for (int j1 = j; j1 < nums[0].length; j1++){
						for(int r1 = i; r1 <= i1; r1++){
							for(int c1 = j; c1 <= j1; c1++){
								localSum += nums[r1][c1];
							}
						}
						if(localSum > maxSum) maxSum = localSum;
						localSum = 0;
					}
				}
			}
		}
		return maxSum;
	}

	public static int MaximalRectangleDP(int[][] nums){
		int maxSum = Integer.MIN_VALUE;
		int r1 = 0, c1 = 0, r2 = 0, c2 = 0;

		// row1 = top left row, col1 = top left column
		// row2 = bottom right row, col2 = bottom right column
		for (int row1 = 0; row1 < nums.length; row1++){

			int[] partialColSum = new int[nums[0].length];

			// for every pair of rows
			for (int row2 = row1; row2 < nums.length; row2++){


				int acc = 0, col1 = 0;

				// apply kadane's algorithm for vertical sum of every row a[row2][col2]
				for (int col2 = 0; col2 < nums[0].length; col2++){
					partialColSum[col2] += nums[row2][col2];
					acc += partialColSum[col2];
					if(acc > maxSum){
						maxSum = acc;
						r1 = row1;
						c1 = col1;
						r2 = row2;
						c2 = col2;
					} else if(acc <= 0) {
						acc = 0;
						col1 = col2 + 1;
					} else col1 = col2;
				}
			}
		}
		System.out.println(String.format("Rectangle coords: (%d,%d) - (%d,%d)",  r1, c1, r2, c2));
		return maxSum;
	}

	public static int LIS(int[] seq){

		int[] cache = new int[seq.length];

		long startTime = System.currentTimeMillis();
		int lis =  LISHelper(seq, 0, cache);
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime + " ms");
		return lis;
	}

	private static int LISHelper(int[] seq, int i, int[] cache) {

		int res = 1; // when seq has only one element left

		if(cache[i] != 0) return cache[i];

		for (int k = i + 1; k < seq.length; k++){
			if(seq[i] < seq[k])
				res = Math.max(res, 1 + LISHelper(seq, k, cache));
		}
		cache[i] = res;
		return cache[i];
	}

	static int count = 0;
	public static int editDistance(String x, String y){

		Map<Pair<Integer, Integer>, Integer> memo = new HashMap<>();

//		int min = editDistanceHelper(x, y, 0, 0);
//		int min = editDistanceRecursive(x, y);
		int min = editDistanceHelper(x, y, 0, 0, memo);
		return min;
	}



	private static int editDistanceRecursive(String x, String y){
		if(x.isEmpty()) return y.length();
		if(y.isEmpty()) return x.length();
		int cost = x.charAt(0) != y.charAt(0) ? 1 : 0;
		int sub = editDistanceRecursive(x.substring(1), y.substring(1)) + cost;
		int ins = editDistanceRecursive(x, y.substring(1)) + 1;
		int del = editDistanceRecursive(x.substring(1), y) + 1;
		int min = min(sub, ins, del);
		return min;
	}

	private static int editDistanceHelper(String x, String y, int i, int j, Map<Pair<Integer, Integer>, Integer> memo){

		if (j == y.length()) return x.length() - i;
		if (i == x.length()) return y.length() - j;

		Pair ij = new Pair(i, j);
		if(memo.containsKey(ij)) return memo.get(ij);

		if (x.charAt(i) == y.charAt(j))
			return editDistanceHelper(x, y, i + 1, j + 1, memo);

		int rep = editDistanceHelper(x, y, i + 1, j + 1, memo) + 1;
		int del = editDistanceHelper(x, y, i, j + 1, memo) + 1;
		int ins = editDistanceHelper(x, y, i + 1, j, memo) + 1;
		int min = min(rep, del, ins);
		memo.put(new Pair<>(i, j), min);
		return min;
	}

	public static int editDistanceBottomUp(String x, String y){
		int[][] dp = new int[x.length() + 1][y.length() + 1];
		for (int i = 1; i < dp.length; i++){
			dp[i][0] = i;
		}
		for (int j = 1; j < dp[0].length; j++){
			dp[0][j] = j;
		}

		for (int i = 1; i < dp.length; i++){
			for (int j = 1; j < dp[0].length; j++){
				int cost = x.charAt(i - 1) != y.charAt(j - 1) ? 2 : 0;
				dp[i][j] = min(dp[i - 1][j] + 1, dp[i][j - 1], dp[i - 1][j - 1] + cost);
			}
		}
		return dp[x.length()][y.length()];
	}

	private static int min(int... numbers){
		return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
	}


	public static int fiboMemo(int n){

		int f = 0;
		if(memo.containsKey(n))
			return memo.get(n);
		if(n < 2) return 1;
		else f = fiboMemo(n - 1) + fiboMemo(n - 2);
		memo.putIfAbsent(n, f);
		return f;
	}

	public static int fiboBottomUp(int n){
		int[] fArr = new int[n + 1];
		fArr[0] = 1;
		fArr[1] = 1;
		for(int i = 2; i <= n; i++){
			fArr[i] = fArr[i - 1] + fArr[i - 2];
		}

		return fArr[n];
	}


	public static int Z1Kanpsack(int[] weights, int[] values, int sack) {
		int[] cache = new int[sack + 1];
		List<Integer> sol = new ArrayList<>();

		for(int i = 0; i< weights.length; i++){
			for(int mSack = 1; mSack < cache.length; mSack++){
				if(mSack >= weights[i]){
					int rem = mSack - weights[i];
					cache[mSack] = Math.max(values[i] + cache[rem], cache[mSack]);
				}
			}
		}
		return cache[sack];
	}

	public static int biomialCoefficient(int n, int m){
		int[][] bc = new int[n + 1][n + 1];
		bc[0][0] = 1;
		for (int i = 1; i < bc.length; i++) {
			bc[i][0] = 1;
			bc[i][i] = 1;
		}
		for (int i = 2; i < bc.length; i++){
			for (int j = 1; j < i; j++){
				bc[i][j] = bc[i - 1][j - 1] + bc[i - 1][j];
			}
		}

		return bc[n][m];
	}

	public static int CoinChange(int n){
		int[] cost = new int[n + 1];
		cost[0] = 0;
		for (int i = 1; i < cost.length; i++)
			cost[i] = Integer.MAX_VALUE;
		return coinChangeHelper(n, new int[]{2}, cost);
	}

	private static int coinChangeHelper(int n, int[] denom, int[] cost){

		if(n == 0) return 0;

		if(n < 0) {
			return -1;
		}

		if(cost[n] != Integer.MAX_VALUE) return cost[n];

		int min = Integer.MAX_VALUE;

		for(int i = 0; i < denom.length; i++) {
			int rem = n - denom[i];
			int result = coinChangeHelper(rem, denom, cost);
			if(result < min && result >= 0) {
				min = 1 + result;
			}
		}
		cost[n] = (min == Integer.MAX_VALUE) ? -1 : min;

		return cost[n];
	}

	public static int CoinChangeBottomUp(int amount, int[] coins){

		int[][] dpTable = new int[coins.length + 1][amount + 1];

		for (int i = 1; i < dpTable[0].length; i++) {
			dpTable[0][i] = Integer.MAX_VALUE;
		}

//		for(int row = 1; row < dpTable.length; row++){
//			for (int col = 1; col < dpTable[0].length; col++){
//				if(col >= coins[row - 1]){
//					int cached = dpTable[row][col - coins[row - 1]];
//					int taken = cached == Integer.MAX_VALUE ? Integer.MAX_VALUE : 1 + cached;
//					dpTable[row][col] = Math.min(taken, dpTable[row - 1][col]);
//				} else {
//					dpTable[row][col] = dpTable[row - 1][col];
//				}
//			}
//		}

		int[] cache = new int[amount + 1];
		for (int i = 1; i < cache.length; i++) cache[i] = Integer.MAX_VALUE;
		for (int i : coins){
			for (int j = 1; j < cache.length; j++){
				if(j >= i){
					int change = j - i;
					int cached = cache[change];
					int taken = cached == Integer.MAX_VALUE ? Integer.MAX_VALUE : 1 + cached;
					cache[j] = Math.min(cache[j], taken);
				}
			}
		}
//		int min = dpTable[coins.length][amount] != Integer.MAX_VALUE ? dpTable[coins.length][amount] : -1;
		int min = cache[amount] != Integer.MAX_VALUE ? cache[amount] : -1;
		return min;
	}


}
