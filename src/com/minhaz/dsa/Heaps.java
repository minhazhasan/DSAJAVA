package com.minhaz.dsa;

public class Heaps {
	public int[] items;
	private int size;
	
	public Heaps() {
		items = new int[10];
		size = 0;
	}
	
	public void insert(int val) {
		items[size++] = val;
		bubbleUp();
	}
	
	public void remove() {
		if(!isEmpty()) {
			items[0] = items[size - 1];
			items[size - 1] = 0;
			bubbleDown();
		} else {
			throw new IllegalStateException();
		}
	}
	
	public void bubbleUp() {
		int currentIdx = size - 1;
		while(!isValid(currentIdx) && currentIdx > 0) {
			
			swap(parent(currentIdx), currentIdx);
			currentIdx = parent(currentIdx);
		}
	}
	
	public void bubbleDown() {
		int invalidRootIdx = 0;
		while(!isValidParent(invalidRootIdx)) {
			invalidRootIdx = resolveParent(invalidRootIdx);
		}
	}
	
	public int leftChild(int rootIndex) {
		return items[leftChildIndex(rootIndex)];
	}
	
	public int rightChild(int rootIndex) {
		return items[rightChildIndex(rootIndex)];
	}
	
	public int leftChildIndex(int index) {
		return 2 * index + 1;
	}
	
	public int rightChildIndex(int index) {
		return 2 * index + 2;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean isValid(int currentIdx) {
		return items[currentIdx] < items[parent(currentIdx)];
	}
	
	public int resolveParent(int rootIdx) {
		
		int currIdx = rootIdx;
//		if(items[rootIdx] < leftChild(rootIdx)) {
//			if(rightChild(rootIdx) == 0) {
//				swap(rootIdx, leftChildIndex(rootIdx));
//				currIdx = leftChildIndex(rootIdx);
//			}
//			else {
//				int idx = (leftChild(rootIdx) > rightChild(rootIdx)) ? leftChildIndex(rootIdx) 
//																	 : rightChildIndex(rootIdx);
//				swap(rootIdx, idx);
//				
//				currIdx = idx;
//			}
//		}
		
		if(leftChild(rootIdx) != 0 && rightChild(rootIdx) != 0) {
			int idx = (leftChild(rootIdx) > rightChild(rootIdx)) ? leftChildIndex(rootIdx) 
					 											 : rightChildIndex(rootIdx);
			
			swap(rootIdx, idx);
			return idx;
		}
		
		if(rightChild(rootIdx) == 0) {
			if(items[rootIdx] < leftChild(rootIdx)) {
				swap(rootIdx, leftChildIndex(rootIdx));
				return leftChildIndex(rootIdx);
			}
		}
		
		return rootIdx;
			
			
	}
	
	public boolean isValidParent(int rootIdx) {
		if(items[rootIdx] < leftChild(rootIdx))
			return false;
		else if(items[rootIdx] < rightChild(rootIdx))
			return false;
		else
			return true;
	}
	public int parent(int currentIdx) {
		return (currentIdx - 1) / 2;
	}
	
	public void swap(int prev, int nxt) {
		int temp = items[prev];
		items[prev] = items[nxt];
		items[nxt] = temp;
	}
}
