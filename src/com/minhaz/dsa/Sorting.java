package com.minhaz.dsa;

public class Sorting {
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
}
