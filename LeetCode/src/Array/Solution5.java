package Array;

import java.util.Arrays;

import org.junit.Test;

/**
 * 给定一个整数数组，除了某个元素外其余元素均出现两次。请找出这个只出现一次的元素。
 * 备注：
 * 你的算法应该是一个线性时间复杂度。(即o(N)) 你可以不用额外空间来实现它吗？ 
 * author: WanZhiWen time: 2018年3月11日
 */
public class Solution5 {
	@Test
	public void test() {
		int[] nums= {1};
		System.out.println(singleNumber(nums));
	}

	// 用时9秒
	public int singleNumber(int[] nums) {
		Arrays.sort(nums);
		for (int i = 0; (i+1) < nums.length; i=i+2) {
			if (nums[i] != nums[i+1]) {
				return nums[i];
			}
		}
		return nums[nums.length-1];
	}
	
	// 时间复杂度为o(N)，且没有添加额外的空间，运行时间只要1秒
	public int singleNumber2(int[] nums) {
		int num = 0;
		for (int i = 0; i < nums.length; i++) {
			// a^=b等价于a=a^b，
			// 表示将a和b换算为二进制形式后按位进行异或运算，即遇相同位取0不同位取1
			// 0和其他数异或，得到的值是不变，两个相同的数异或，得到的值为0
			// 这样，重复的元素就会被全部去掉了
			num ^= nums[i];
		}
		return num;
	}
}
