package com.minhaz.dsa;

import java.util.*;

public class Backtracking {
	
	// place a queen in a row major box.
	// check if the placement is valid or not.
	// check which partial tree returns the complete solution with n placements.
	// return the solution.
	
	public static void nQueensRecursive(boolean[][] grid) {
		
	}
	
	public static List<List<Integer>> generateSubsets(int n) {
		List<List<Integer>> sol = new ArrayList<>();
		List<Integer> subList = new ArrayList<>();

		generateSubsetHelper(sol, subList, n);
		return sol;
 	}

 	public static List<String> generatePermutations(String str){
		HashSet<Character> set = new LinkedHashSet<>();
		List<String> sol = new ArrayList<>();
		char[] chars = str.toCharArray();

		permHelper(chars, set, sol, 0);

		return sol;
	}

	public static void permHelper(char[] chars, HashSet<Character> set, List<String> sol, int l){

		if(l == chars.length){
			createPermSet(set, sol);
			return;
		} else {
			for(char c : chars){
				if(!set.contains(c)){
					set.add(c);
					permHelper(chars, set, sol, l + 1);
					set.remove(c);
				}
			}
		}
	}

	private static void createPermSet(HashSet<Character> set, List<String> sol) {
		StringBuilder str = new StringBuilder();
		for(char s : set){
			str.append(s);
		}

		sol.add(str.toString());
	}

	public static void generateSubsetHelper(List<List<Integer>> sol, List<Integer> subList, int n){
		
		//Generating subsets of binary positions
		
		if(subList.size() == n) {
			sol.add(new ArrayList<Integer>(subList));
			return;
		} else {
			for (int i = 0; i < 2; i++) {
				subList.add(i);
				generateSubsetHelper(sol, subList, n);
				subList.remove(subList.size() - 1);
			}
		}
		
	}



	public static void generateSubSetsFromBinary(int[] nums, List<List<Integer>> subsetPos){

		int j = 0;
		for(List<Integer> pos : subsetPos){
			for(int i = 0; i < nums.length; i++){
				if(pos.get(j) != 0) {
					pos.set(j, nums[i]);
					j++;
				}
				else {
					pos.remove(j);
				}
			}

			j = 0;
		}

		System.out.println(subsetPos.toString());
	}
	
	public static void generateSubsetHelper2(List<List<Integer>> sol, List<Integer> subList, int n, int i){
		
		// Generating subset as we traverse
		
		if(i == n + 1) {
			sol.add(new ArrayList<Integer>(subList));
			return;
		} else {
			subList.add(i);
			generateSubsetHelper2(sol, subList, n, i + 1);
			subList.remove(subList.size() - 1);
			generateSubsetHelper2(sol, subList, n, i + 1);
		}
		
//		sol.add(new ArrayList<Integer>(subList));
	}
	
	
}
