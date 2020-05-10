package com.minhaz.dsa;

import java.util.*;

public class AmazonProblems {
    public static int[] FindPairWithGivenSum(int[] nums, int target){
        // Given a list of positive integers nums and an int target,
        // return indices of the two numbers such that they add up to a target - 30.
        Map<Integer, Integer> map = new HashMap<>();
        int res[] = new int[2];
        int modifiedTarget = target - 30;
        int pairCount = 0;
        for(int i = 0; i < nums.length; i++){
            int rem = modifiedTarget - nums[i];
            if(map.containsKey(rem)){
                if((rem > res[0] || nums[i] > res[1])){
                    res[0] = map.get(rem);
                    res[1] = i;
                }

            }
            map.put(nums[i], i);
        }

        return res;
    }

    public static int twoSumUniquePairs(int[] nums, int target){
        Set<Integer> set = new HashSet<>();
        Set<Integer> pairs = new HashSet<>();
        int count = 0;

        for (int num : nums){
            int rem = target - num;
            if(set.contains(rem) && !pairs.contains(rem)) {
                pairs.add(num);
                pairs.add(rem);
                count++;
            }
            set.add(num);

        }
        return count;
    }
}
