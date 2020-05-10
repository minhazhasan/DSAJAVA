package com.minhaz.dsa;

import java.util.Arrays;
import java.util.Collections;

public class Sorting {

	private static int[] aux;

	public static void BubbleSort(int arr[]) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr.length - i - 1; j++) {
				if(arr[j] > arr[j+1])
					swap(arr, j, j+1);
			}
		}
	}
	
	public static void insertionSort(int arr[]) {
		for(int i = 1; i < arr.length; i++) {
			int key = arr[i]; // sorted item
			
			// j = last sorted index
			for(int j = i - 1; j >=0 && arr[j] > key; j--) {
				arr[j + 1] = arr[j];
				arr[j] = key;
			}
		}
	}
	
	public static void mergeSort(int arr[], int low, int high) {
		if(low < high) {
			int mid = (low + high) / 2;
			mergeSort(arr, low, mid); // divide the array
			mergeSort(arr, mid + 1, high); // recursively sort the sub-array using backtracking
			merge(arr, low, mid, high); // combine the arrays
		}
	}
	
	private static void merge(int arr[], int low, int mid, int high) {
		// subarray1 = arr[low....mid], subarray2 = arr[mid+1, high] both sorted
		int N = high - low + 1; // 0 based indexing
		int b[] = new int[N];
		int left = low, right = mid + 1, bIdx = 0;
		
		while(left <= mid && right <= high) {
			b[bIdx++] = (arr[left] <= arr[right]) ? arr[left++] : arr[right++];  
		}
		
		while(left <= mid) b[bIdx++] = arr[left++];
		while(right <= high) b[bIdx++] = arr[right++];
		
		for(int k = 0; k < N; k++) {
			arr[low + k] = b[k];
		}
	}
	
	public static void quicksort(int[] arr) {
		shuffle(arr);
		quicksort(arr, 0, arr.length - 1);
	}

	public static void Quick3Way(int[] arr, int lo, int hi){
		if(hi <= lo) return;
		int lt = lo, i = lo + 1, gt = hi;
		int v = arr[lo];
		while (i <= gt){

			if(arr[i] < v) swap(arr, lt++, i++);
			else if(arr[i] > v) swap(arr, i, gt--);
			else i++;
		}

		Quick3Way(arr, lo, lt - 1);
		Quick3Way(arr, gt + 1, hi);
	}


	public static void quicksort(int[] arr, int lo, int hi){
		if(hi <= lo)
			return;
		int j = partition(arr, lo, hi);
		quicksort(arr, lo, j - 1);
		quicksort(arr, j + 1, hi);
	}

	public static int quickSelect(int[] arr, int k){
		int n = arr.length;
		shuffle(arr);
		int lo = 0, hi = n - 1, kthLargeIdx = n - k;
		while (hi > lo){

			int j = partition(arr, lo, hi);
			if(j == kthLargeIdx) return arr[kthLargeIdx];
			else if(j > kthLargeIdx) hi = j - 1;
			else if(j < kthLargeIdx) lo = j + 1;
		}

		return arr[kthLargeIdx];

	}

	private static int partition(int[] arr, int lo, int hi) {

		//Partition into a[lo....i-1], a[i], a[i+1....hi].
		int i = lo, j = hi + 1; // left and right scan indices.
		Integer pivot = arr[lo];
		while (true){
			while (arr[++i] < pivot) if(i == hi) break;
			while (pivot < arr[--j]) if(j == lo) break;
			if(i >= j) break;
			swap(arr, i, j);
		}

		swap(arr, lo, j);
		return j;
	}


	private static void shuffle(int[] a){
		int n = a.length;
		for(int i = 0; i < n; i++){
			int r = i + (int)(Math.random() * (n - i));
			int swap = a[r];
			a[r] = a[i];
			a[i] = swap;
		}
	}
	
	private static void swap(int arr[], int left, int right) {
		int temp = arr[left];
		arr[left] = arr[right];
		arr[right] = temp;
	}


	public static int SortAndCount(int[] a){

		int rA = 0, rB = 0, rC = 0, count = 0;
		if(a.length == 1)
			return 0;
		int mid = a.length / 2;
		int[] leftSubArray = Arrays.copyOfRange(a, 0, mid);
		int[] rightSubArray = Arrays.copyOfRange(a, mid, a.length);
		rA = SortAndCount(leftSubArray);
		rB = SortAndCount(rightSubArray);
		rC = mergeAndCount(a, leftSubArray, rightSubArray);

		return rA + rB + rC;
	}


	private static int mergeAndCount(int[] a, int[] leftSubArray, int[] rightSubArray) {
		int length = leftSubArray.length + rightSubArray.length;
		int k = 0;
		int currA = 0, currB = 0;
		int invCounter = 0;
		int[] mergedArray = new int[length];

		while (currA < leftSubArray.length && currB < rightSubArray.length){
			if(leftSubArray[currA] < rightSubArray[currB]) mergedArray[k++] = leftSubArray[currA++];
			else {
				invCounter += leftSubArray.length - currA;
				mergedArray[k++] = rightSubArray[currB++];
			}
		}

		while (currA < leftSubArray.length) mergedArray[k++] = leftSubArray[currA++];
		while (currB < rightSubArray.length) mergedArray[k++] = rightSubArray[currB++];

		for (int i = 0; i < length; i++) {
			a[i] = mergedArray[i];
		}

		return invCounter;
	}
}
