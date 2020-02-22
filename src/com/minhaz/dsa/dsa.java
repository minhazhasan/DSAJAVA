package com.minhaz.dsa;

import java.util.Arrays;

import com.minhaz.dsa.Sorting;

public class dsa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int arr[] = {3, 2, 8, 5, 9, 1};
//		Sorting.mergeSort(arr, 0, 5);
//		System.out.println(Arrays.toString(arr));
		
//		BST bst = new BST();
//		bst.insert(20);
//		bst.insert(10);
//		bst.insert(30);
//		bst.insert(6);
//		bst.insert(14);
//		bst.insert(3);
//		bst.insert(8);
//		bst.insert(24);
//		bst.insert(26);
		
//		System.out.println(bst.findFast(26));
//		System.out.println(bst.leverOrder().toString());
//		System.out.println(bst.inorder().toString());
//		System.out.println(bst.preorder().toString());
//		System.out.println(bst.postorder().toString());
		
		//System.out.println(new test.MyClass().getValue("foo", 0));
		
		Heaps heap = new Heaps();
		heap.insert(15);
		heap.insert(10);
		heap.insert(3);
		heap.insert(8);
		heap.insert(12);
		heap.insert(9);
		heap.insert(4);
		heap.insert(1);
		heap.insert(24);
		
		heap.remove();
		
		System.out.println(Arrays.toString(heap.items));
		
	}

}
