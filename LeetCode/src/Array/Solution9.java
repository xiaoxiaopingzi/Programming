package Array;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和 
 * 
 * 给定一个整数数列，找出其中和为特定值的那两个数。
 * 
 * 你可以假设每个输入都只会有一种答案，同样的元素不能被重用。
 * 
 * 示例:
 * 
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 
 * 因为 nums[0] + nums[1] = 2 + 7 = 9 所以返回 [0, 1] 
 * author: WanZhiWen time:
 * 2018年4月3日
 */
public class Solution9 {
	public int[] twoSum(int[] nums, int target) {
		int[] returnValue = new int[2];
		for (int i = 0; i < nums.length-1; i++) {
			for (int j = i+1; j < nums.length; j++) {
				int temp = nums[i] + nums[j];
				if (temp == target) {
					returnValue[0] = i;
					returnValue[1] = j;
					return returnValue;
				}
			}
		}
		return returnValue;
    }

	// 其他的运行时间非常短的方法
	public int[] twoSum2(int[] nums, int target) {
		int[] result = new int[2];
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		for(int i = 0;i < nums.length; i++){
			int differenceNum = target - nums[i];
			if(map.containsKey(nums[i])){
				result[0] = map.get(nums[i]);
				result[1] = i;
				break;
			}
			// map集合中存放的是数组中每个元素的索引以及该索引对应的元素和目标值之间的差距
			map.put(differenceNum,i);
		}
		return result;
	}
}
