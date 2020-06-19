package com.minhaz.dsa;

import java.util.*;

public class ArrayProblems {
	private int[] items;

	public ArrayProblems(int length) {
		items = new int[length];
	}

	public void print() {
		for (int i = 0; i < items.length; i++) {
			System.out.println(items[i]);
		}
	}

	// Problem Definition:
	// given pivot, arrange the in such a way that
	// the elements less than pivot should be on the left side
	// equal to the pivot should be on the middle
	// greater than the pivot should be on the right side.

	public static enum Color {
		RED, WHITE, BLUE
	}

	public static void DutchFlagPartitioning(List<Color> colors) {
		// dfHelper(colors, 0, colors.size() - 1);
		dfIterative(colors, 3);
	}

	private static void dfHelper(List<Color> colors, int lo, int hi) {

		if (lo >= hi)
			return;

		int lt = lo;
		int i = lo + 1;
		int gt = hi;
		int pivot = colors.get(lo).ordinal();
		while (i <= gt) {
			if (colors.get(i).ordinal() < pivot)
				Collections.swap(colors, i++, lt++);
			else if (colors.get(i).ordinal() > pivot)
				Collections.swap(colors, i, gt--);
			else
				i++;
		}
		dfHelper(colors, lo, lt - 1);
		dfHelper(colors, gt + 1, hi);
	}

	private static void dfIterative(List<Color> colors, int pivIdx) {

		Color pivot = colors.get(pivIdx);
		int lt = 0;
		for (int i = 1; i < colors.size(); i++) {
			if (colors.get(i).ordinal() < pivot.ordinal())
				Collections.swap(colors, i, lt++);
		}

		int gt = colors.size() - 1;
		for (int i = gt; i >= 0 && colors.get(i).ordinal() >= pivot.ordinal(); i--) {
			if (colors.get(i).ordinal() > pivot.ordinal())
				Collections.swap(colors, i, gt--);
		}
	}

	public static String BinaryAddition(String s1, String s2) {
		int i = s1.length() - 1;
		int j = s2.length() - 1;
		int carry = 0;

		StringBuilder str = new StringBuilder();

		while (i >= 0 || j >= 0) {
			int bin1 = j >= 0 ? s2.charAt(j--) - '0' : 0;
			int bin2 = i >= 0 ? s1.charAt(i--) - '0' : 0;
			int sum = bin1 + bin2 + carry;
			str.insert(0, sum % 2);
			carry = sum / 2;
		}

		if (carry == 1)
			str.insert(0, 1);
		return str.toString();
	}

	public static List<Integer> plusOne(LinkedList<Integer> A) {
		int n = A.size() - 1;
		A.set(n, A.get(n) + 1);
		for (int i = n; i > 0 && A.get(i) == 10; i--) {
			A.set(i, 0);
			A.set(i - 1, A.get(i - 1) + 1);
		}

		// check the carry
		if (A.get(0) == 10) {
			A.set(0, 0);
			A.add(0, 1);
		}

		return A;
	}

	public static int[] multiply(int[] a, int[] b) {
		// Step 1: create an array of length a.length + b.length
		// multiplied results can be at most length of a & b
		int[] result = new int[a.length + b.length];

		// Step 2: store the sign bit
		int sign = a[0] * b[0] < 0 ? -1 : 1;
		a[0] = Math.abs(a[0]);
		b[0] = Math.abs(b[0]);

		// Step 3: set the cross border
		int rt = result.length - 1;

		for (int i = b.length - 1; i >= 0; i--) {
			int k = rt;
			for (int j = a.length - 1; j >= 0; j--) {
				int sum = b[i] * a[j] + result[k];
				result[k--] = sum % 10;
				result[k] = result[k] + sum / 10;
			}
			rt--;
		}

		int leadingZeroLength = 0;
		for (int i = 0; i < result.length; i++) {
			if (result[i] != 0) {
				leadingZeroLength = i;
				break;
			}
		}

		result = Arrays.copyOfRange(result, leadingZeroLength, result.length);
		result[0] = result[0] * sign;

		return result;
	}

	public static boolean advArray(int[] arr) {
		int furthestSofar = 0, lastIdx = arr.length - 1;
		for (int i = 0; i <= furthestSofar && furthestSofar < lastIdx; i++) {
			int advance = i + arr[i];
			if (advance > furthestSofar) {
				furthestSofar = advance;
			}
		}

		return furthestSofar >= lastIdx;
	}

	// need to verify
	public static int minStepNeededToAdvArray(int[] arr) {
		int furthestReachSoFar = 0, lastIdx = arr.length - 1;
		int jump = 0;

		for (int i = 0; i <= furthestReachSoFar && furthestReachSoFar < lastIdx; i++) {
			int advSoFar = i + arr[i];
			if (advSoFar > furthestReachSoFar) {
				furthestReachSoFar = advSoFar;
				jump += 1;
			}
		}

		if (furthestReachSoFar >= lastIdx)
			return jump;
		return -1;
	}

	public static int[] deleteDuplicates(int[] nums) {
		int j = 1, i = 1;
		while (i < nums.length && nums.length > 1) {
			if (nums[i - 1] != nums[i])
				nums[j++] = nums[i];
			i++;
		}

		System.out.println("Distinct elements: " + j);

		while (j < nums.length) {
			nums[j++] = 0;
		}

		return nums;
	}

	// Algorithm: as we know the array is sorted, we are allowed keep
	// at most m duplicates. So, if we compare an element i with j - m distance
	// apart,
	// we know that is the index we need to insert if we encounter a different
	// element.
	// e.g A = [2,2,2,3,5], So, A[3] != A[2], (we start j and i from m because of
	// the constraint),
	// we know that we need update the duplicate index j with A[3]'s value. On the
	// contrary, if
	// A[2] == A[0], we know that, also, A[1] == A[2], because the array is sorted.
	// T(n) = O(n)
	public static int[] deleteDuplciatesAtMost(int[] nums, int m) {
		int j = m, dupCounter = 1;
		if (nums.length == 0)
			return nums;
		for (int i = m; i < nums.length && m > 1; i++) {
			if (nums[j - m] != nums[i]) {
				int temp = nums[j];
				nums[j] = nums[i];
				nums[i] = temp;
				j++;
			}
		}

		System.out.println("Elements: " + j);

		return nums;
	}

	public static int BuySellStockOnce(int[] nums) {
		int maxProfit = 0;
		int bestBuyIndex = nums[0];
		for (int i = 1; i < nums.length; i++) {
			int profit = nums[i] - bestBuyIndex;
			if (profit > maxProfit)
				maxProfit = profit;
			if (nums[i] < bestBuyIndex)
				bestBuyIndex = nums[i];
		}

		return maxProfit;
	}

	// Buy Sell Stock at most 2 times (T(n) = O(n^2), Space = O(1))
	public static int BuySellStockAtMostTwice(int[] nums) {
		int maxProfit = 0;
		for (int i = 1; i < nums.length; i++) {
			maxProfit = Math.max(maxProfit, BuySellStockOnce(Arrays.copyOfRange(nums, 0, i))
					+ BuySellStockOnce(Arrays.copyOfRange(nums, i, nums.length)));
		}
		return maxProfit;
	}

	// Buy Sell Stock at most 2 times (T(n) = O(n), Space = O(n))
	public static int BuySellStockAtMostTwice2(int[] nums) {
		int maxProfit = Integer.MIN_VALUE;
		int[] firstBuySellProfits = new int[nums.length];
		int minPrice = Integer.MAX_VALUE;

		for (int i = 0; i < nums.length; i++) {
			minPrice = Math.min(minPrice, nums[i]);
			maxProfit = Math.max(maxProfit, nums[i] - minPrice);
			firstBuySellProfits[i] = maxProfit;
		}
		int maxPrice = Integer.MIN_VALUE;
		for (int i = nums.length - 1; i >= 0; i--) {
			maxPrice = Math.max(maxPrice, nums[i]);
			int previousDayProfit = (i - 1) < 0 ? 0 : firstBuySellProfits[i - 1];
			maxProfit = Math.max(maxProfit, (maxPrice - nums[i]) + previousDayProfit);
		}

		return maxProfit;
	}

	// Buy Sell Stock at most 2 times (T(n) = O(n) , Space = O(1))
	public static int BuySellStockAtMostTwice3(int[] nums) {
		int bestBuyIndex = 0, bestSellIndex = 0, maxProfit = 0, minPrice = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] < minPrice) {
				bestBuyIndex = i;
				minPrice = nums[i];
			}

			int profit = nums[i] - minPrice;

			if (profit > maxProfit) {
				bestSellIndex = i;
				maxProfit = profit;
			}
		}

		if (bestBuyIndex > 0) {
			maxProfit = Math.max(maxProfit, maxProfit + BuySellStockOnce(Arrays.copyOfRange(nums, 0, bestBuyIndex)));
		}

		if (bestSellIndex < nums.length - 1) {
			maxProfit = Math.max(maxProfit,
					maxProfit + BuySellStockOnce(Arrays.copyOfRange(nums, bestSellIndex + 1, nums.length)));
		}

		return maxProfit;
	}

	public static List<String> applyPermutation(List<String> nums, List<Integer> perm) {
		for (int i = 0; i < perm.size(); i++) {
			int j = perm.get(i);
			while (j != i) {
				Collections.swap(nums, i, j);
				Collections.swap(perm, i, j);
				j = perm.get(i);
			}
		}
		return nums;
	}

	// The key insight is that we want to increase the permutation by as little as
	// possible.
	// for that, we need to find longest decreasing suffix.
	// swap the element immediate before the suffix with the min element that is
	// larger than that element;
	// reverse the suffix to maintain the change as little as possible.
	public static List<Integer> nextPermutation(List<Integer> perm) {
		int k = perm.size() - 2;
		while (k >= 0 && perm.get(k) > perm.get(k + 1))
			k--;
		if (k == -1)
			return Collections.emptyList();
		int minIndex = perm.indexOf(Collections.min(perm.subList(k + 1, perm.size())));
		Collections.swap(perm, k, minIndex);
		Collections.reverse(perm.subList(k + 1, perm.size()));

		return perm;
	}

	public static List<Integer> prevPermutation(List<Integer> perm) {
		int k = perm.size() - 2;
		while (k >= 0 && perm.get(k) < perm.get(k + 1))
			k--;
		if (k == -1)
			return Collections.emptyList();
		int minIndex = perm.indexOf(Collections.min(perm.subList(k + 1, perm.size())));
		Collections.swap(perm, k, minIndex);
		Collections.reverse(perm.subList(k + 1, perm.size()));

		return perm;
	}

	public static void offlineRandomSampling(List<Integer> data, int k) {
		Random rand = new Random();
		for (int i = 0; i < k; i++) {
			Collections.swap(data, i, i + rand.nextInt(data.size() - i));
		}
	}

	public static List<Integer> onlineRandomSampling(Iterator<Integer> sequence, int k) {
		List<Integer> runningSample = new ArrayList<>();
		for (int i = 0; sequence.hasNext() && i < k; i++) {
			runningSample.add(sequence.next());
		}

		int numSeenSoFar = k;
		Random rand = new Random();
		while (sequence.hasNext()) {
			int x = sequence.next();
			numSeenSoFar += 1;
			final int idxToReplace = rand.nextInt(numSeenSoFar);
			if (idxToReplace < k)
				runningSample.set(idxToReplace, x);
		}
		return runningSample;
	}

	public static List<Integer> randomPermutation(int n) {
		List<Integer> data = new ArrayList<>();
		Random random = new Random();
		for (int i = 1; i <= n; i++) {
			data.add(i);
		}

		Collections.shuffle(data);
		return data;
	}

	public static List<Integer> generateRandomSubset(int n, int k) {
		// List<Integer> data = new ArrayList<>();
		// for (int i = 1; i <= n; i++) data.add(i);
		// offlineRandomSampling(data, k);
		// return data.subList(0, k);

		Map<Integer, Integer> pairs = new HashMap<>();
		Random randNextGen = new Random();
		for (int i = 0; i < k; i++) {

			int randIndx = i + randNextGen.nextInt(n - i);
			Integer ptr1 = pairs.get(randIndx);
			Integer ptr2 = pairs.get(i);

			if (ptr1 == null && ptr2 == null) {
				pairs.put(i, randIndx);
				pairs.put(randIndx, i);
			} else if (ptr1 != null && ptr2 == null) {
				pairs.put(randIndx, i);
				pairs.put(i, ptr1);
			} else if (ptr2 != null && ptr1 == null) {
				pairs.put(randIndx, ptr2);
				pairs.put(i, randIndx);
			} else {
				pairs.put(randIndx, ptr2);
				pairs.put(i, ptr1);
			}

		}

		List<Integer> result = new ArrayList<>(k);
		for (int i = 0; i < k; i++) {
			result.add(pairs.get(i));
		}

		return result;
	}

	public static int nonUniformRandomNumberGeneration(List<Integer> values, List<Double> probabilities) {
		List<Double> prefixSumProbabilities = new ArrayList<>();
		prefixSumProbabilities.add(0.0);

		// Creating endpoints for the intervals corresponding to the probabilities.
		for (double p : probabilities) {
			prefixSumProbabilities.add(prefixSumProbabilities.get(prefixSumProbabilities.size() - 1) + p);
		}

		Random r = new Random();

		// random number between [0.0, 1.0]
		final double uniform01 = r.nextDouble();

		// find the index of the interval that uniform01 lies in.
		int it = Collections.binarySearch(prefixSumProbabilities, uniform01);

		if (it < 0) {

			// we want the index of the first element in the array which is
			// greater than the key.
			//
			// When a key is not present in the array, Collections.binarySearch()
			// returns the negative of 1 plus the smallest index whose entry
			// is greater than the key.
			//
			// Therefore. if the return value is negative, by taking its absolute
			// value and adding 1 to it, we get the desired index.
			final int intervalIdx = (Math.abs(it) - 1) - 1;
			return values.get(intervalIdx);
		} else {
			return values.get(it);
		}
	}

	// Multidimensional Arrays

	// Valid Sudoku Problem
	public static boolean validSudoku(int[][] partialBoard) {
		// check row constraints
		for (int i = 0; i < partialBoard.length; i++) {
			if (checkDuplicate(partialBoard, i, i + 1, 0, partialBoard[1].length))
				return false;
		}

		// check column constraints
		for (int j = 0; j < partialBoard[1].length; j++) {
			if (checkDuplicate(partialBoard, 0, partialBoard.length, j, j + 1))
				return false;
		}

		// check sub-grid
		int region = (int) Math.sqrt(partialBoard.length);
		for (int I = 0; I < region; I++) {
			for (int J = 0; J < region; J++) {
				if (checkDuplicate(partialBoard, I * region, (I + 1) * region, J * region, (J + 1) * region))
					return false;
			}
		}

		return true;
	}

	private static boolean checkDuplicate(int[][] partialBoard, int startRow, int endRow, int startCol, int endCol) {
		Set<Integer> set = new HashSet<>();
		for (int i = startRow; i < endRow; i++) {
			for (int j = startCol; j < endCol; j++) {
				int digit = partialBoard[i][j];
				if (digit != 0) {
					if (!set.contains(digit))
						set.add(digit);
					else
						return true;
				}
			}
		}

		return false;
	}

	public static List<Integer> SpiralOrder(int[][] matrix) {
		int left = 0, right = matrix[0].length - 1;
		int top = 0, bottom = matrix.length - 1;
		List<Integer> spiralMatrix = new ArrayList<>();

		while (left <= right && top <= bottom) {
			for (int i = left; i <= right; i++)
				spiralMatrix.add(matrix[top][i]);
			top++;
			for (int i = top; i <= bottom; i++)
				spiralMatrix.add(matrix[i][right]);
			right--;
			for (int i = right; i >= left && top <= bottom; i--)
				spiralMatrix.add(matrix[bottom][i]);
			bottom--;
			for (int i = bottom; i >= top && left <= right; i--)
				spiralMatrix.add(matrix[i][left]);
			left++;
		}

		return spiralMatrix;
	}

	public static List<Integer> SpiralOrder2(int[][] matrix) {
		final int[][] SHIFT = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
		int dir = 0, x = 0, y = 0;
		int cols = matrix[0].length;
		int rows = matrix.length;
		List<Integer> spiralOrdering = new ArrayList<>();
		for (int i = 0; i < matrix.length * matrix[0].length; i++) {
			spiralOrdering.add(matrix[x][y]);
			matrix[x][y] = 0;
			int nextX = x + SHIFT[dir][0];
			int nextY = y + SHIFT[dir][1];
			if (nextX < 0 || nextX >= rows || nextY >= cols || nextY < 0 || matrix[nextX][nextY] == 0) {

				dir = (dir + 1) % 4;
				nextX = x + SHIFT[dir][0];
				nextY = y + SHIFT[dir][1];
			}

			x = nextX;
			y = nextY;
		}

		return spiralOrdering;
	}

	public static int[][] RotateMatrix(int[][] matrix) {
		final int size = matrix.length - 1;
		for (int i = 0; i < (matrix.length / 2); i++) {
			for (int j = i; j < size - i; j++) {
				int temp1 = matrix[i][j];

				matrix[i][j] = matrix[size - j][i];
				matrix[size - j][i] = matrix[size - i][size - j];
				matrix[size - i][size - j] = matrix[j][size - i];
				matrix[j][size - i] = temp1;

			}
		}

		return matrix;
	}

	public static List<List<Integer>> PascalsTriangle(int numRows) {
		List<List<Integer>> triangleMatrix = new ArrayList<>();

		for (int i = 0; i < numRows; i++) {
			List<Integer> innerList = new ArrayList<>();
			int j = 0;
			while (j <= i) {
				if (j > 0 && j != i) {
					int val = triangleMatrix.get(i - 1).get(j) + triangleMatrix.get(i - 1).get(j - 1);
					innerList.add(val);
				} else {
					innerList.add(1);
				}
				j++;
			}
			triangleMatrix.add(innerList);
		}

		return triangleMatrix;
	}

	public static List<Integer> NthRowPascalsTriangle(int rowIndex) {

		List<Integer> nthRowIntegers = new ArrayList<>();
		for (int i = 0; i < rowIndex; i++) {
			for (int j = nthRowIntegers.size() - 1; j > 0; j--) {
				int val = nthRowIntegers.get(j) + nthRowIntegers.get(j - 1);
				nthRowIntegers.set(j, val);
			}
			nthRowIntegers.add(1);
		}

		return nthRowIntegers;
	}

	public static List<Integer> mergeTwoSortedArrays(List<Integer> A, List<Integer> B) {
		int pointerA = 0, pointerB = B.size() - 1;
		int currIdx = A.size() - 1;

		for (int i = 0; i < A.size(); i++) {
			if (A.get(i) == null)
				break;
			pointerA++;
		}

		pointerA--;

		while (currIdx >= 0 && pointerA >= 0 && pointerB >= 0) {
			if (A.get(pointerA) > B.get(pointerB))
				A.set(currIdx--, A.get(pointerA--));
			else if (A.get(pointerA) < B.get(pointerB))
				A.set(currIdx--, B.get(pointerB--));
			else {
				A.set(currIdx--, A.get(pointerA--));
				A.set(currIdx--, B.get(pointerB--));
			}
		}

		// if A starts with the larger number than in B
		while (currIdx >= 0 && pointerB >= 0)
			A.set(currIdx--, B.get(pointerB--));

		return A;
	}

	public static int longestEqualEntries(int[] nums) {
		int longestLength = 1, j = 0, localLength = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[j] != nums[i]) {
				j++;
				localLength = 1;
			} else {
				localLength = i - j + 1;
				longestLength = Math.max(localLength, longestLength);
			}
		}

		return longestLength;
	}

}
