package com.minhaz.dsa;

import java.util.Arrays;

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
	
	public static void quicksort(int arr[]) {
		
	}
	
	private static void swap(int arr[], int left, int right) {
		int temp = arr[left];
		arr[left] = arr[right];
		arr[right] = temp;
	}

//	public static int countingInversions(int[] arr){
//
//		aux = new int[arr.length];
//		int inv = mergeSortAndCount(arr, aux, 0, arr.length - 1);
//
//	}

	public static int SortAndCount(int[] a){
		if(a.length == 1)
			return 0;
		int mid = a.length / 2;
		int[] leftSubArray = Arrays.copyOfRange(a, 0, mid + 1);
		int[] rightSubArray = Arrays.copyOfRange(a, mid, a.length);
		int rA = SortAndCount(leftSubArray);
		int rB = SortAndCount(rightSubArray);
		int rC = mergeAndCount(leftSubArray, rightSubArray);

		return rA + rB + rC;
	}


	private static int mergeAndCount(int[] leftSubArray, int[] rightSubArray) {
		int length = leftSubArray.length + rightSubArray.length;
		int k = 0;
		int[] mergedArray = new int[length];
		int currA = 0, currB = 0;
		int invCounter = 0;
		while (currA < leftSubArray.length && currB < rightSubArray.length){
			if(leftSubArray[currA] < rightSubArray[currB]) mergedArray[k++] = leftSubArray[currA++];
			else {
				invCounter = leftSubArray.length - currA;
				mergedArray[k++] = rightSubArray[currB++];
			}
		}

		while (currA < leftSubArray.length) mergedArray[k++] = leftSubArray[currA++];
		while (currB < rightSubArray.length) mergedArray[k++] = rightSubArray[currB++];
		return invCounter;
	}
}
