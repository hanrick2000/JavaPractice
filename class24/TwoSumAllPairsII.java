package laiofferClass24;

import java.util.Arrays;
import java.util.*;

public class TwoSumAllPairsII {
	//Method 1: sort the array first and use two pointers.
	public List<List<Integer>> allPairs(int[] array, int target) {
		//Assumptions: array is not null, array.length >= 2.
		Arrays.sort(array);
		List<List<Integer>> result = new ArrayList<>();
		int left = 0;
		int right = array.length - 1;
		while (left < right) {
			//ignore all the consecutive duplicate values when we want
			// to determine the smaller element of the pair
			if (left > 0 && array[left] == array[left - 1]) {
				left++;
				continue;
			}
			int cur = array[left] + array[right];
			if (cur == target) {
				result.add(Arrays.asList(array[left], array[right]));
				left++;
				right--;
			}else if (cur < target) {
				left++;
			}else {
				right--;
			}
		}
		return result;
	}
	
	//method 2 use HashSet.
	public List<List<Integer>> allPairsII(int[] array, int target) {
		// assumptions: array is not null, and array.length >= 2
		List<List<Integer>> result = new ArrayList<>();
		//Record the number of existence of the value 
		Map<Integer, Integer> map = new HashMap<>();
		for (int num : array) {
			//Two cases when we need to make the pair a solution:
			//1. if 2 * x == target, and we need to make sure there is no duplicates.
			//2. if x + y == target, adn this is the first time both x and y
			Integer count = map.get(num);
			if (num * 2 == target && count != null && count == 1) {
				result.add(Arrays.asList(num, num));
			} else if (map.containsKey(target - num) && count == null){
				result.add(Arrays.asList(target - num, num));
			}
			if (count == null) {
				map.put(num,  1);
			}else {
				map.put(num, count + 1);
			}
		}
		return result;
	}

}
