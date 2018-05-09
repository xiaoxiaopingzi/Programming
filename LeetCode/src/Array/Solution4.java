package Array;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 给定一个整数数组，判断是否存在重复元素。
 * 
 * 如果任何值在数组中出现至少两次，函数应该返回 true。如果每个元素都不相同，则返回 false。 author: WanZhiWen time:
 * 2018年3月10日
 */
public class Solution4 {

	// 花费的时间太长
	public boolean containsDuplicate(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			for (int j = (i+1); j < nums.length; j++) {
				if (nums[i] == nums[j]) {
					return true;
				}
			}
		}
		return false;
	}
	
	// 使用了Set集合，花费的时间在20秒左右，还是太长了
	public boolean containsDuplicate2(int[] nums) {
		HashSet<Integer> temp = new HashSet<Integer>();
		for (int i = 0; i < nums.length; i++) {
			temp.add(nums[i]);
		}
		if (temp.size() == nums.length) {
			return false;
		}else {
			return true;
		}
	}
	
	
	/**
	 * 他人的解决方法，只用了5秒左右
	 * @param nums
	 * @return
	 */
	public boolean containsDuplicate3(int[] nums) {
		boolean b = false;
		if (nums.length < 2) {
			return false;
		}
		for (int i = 1; i < nums.length; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (nums[i] > nums[j] && j + 1 != i) {
					int a = nums[i];
					nums[i] = nums[j + 1];
					nums[j + 1] = a;
					break;
				}
				if (nums[i] == nums[j]) {
					return true;
				}

			}

		}
		return b;
	}
	
	// 时间在9秒左右
	public boolean containsDuplicate4(int[] nums) {
		Arrays.sort(nums);
		for (int ind = 1; ind < nums.length; ind++) {
			if (nums[ind] == nums[ind - 1]) {
				return true;
			}
		}
		return false;
	}
	
}
