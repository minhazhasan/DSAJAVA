package com.minhaz.dsa;

public class BIT {
	
	int size;
	int[] table;
	
	public BIT(int size) {
		table = new int[size];
		this.size = size;
	}
	
	// update position i by delta
	void update(int i, int delta) {
		
		while(i < size) {
			table[i] += delta;
			i += Integer.lowestOneBit(i);
		}
	}
	
	// compute the prefix sum value[1, i]
	int sum(int i) {
		
		int sum = 0;
		while(i > 0) {
			sum += table[i];
			i = i & -i;
		}
		
		return sum;
	}
	
	int rangeSum(int i, int j) {
		
		return sum(j) - sum(i);
	}

}
