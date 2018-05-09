package Array;

import org.junit.Test;

/**
 * 将包含 n 个元素的数组向右旋转 k 步。
 * 例如，如果 n = 7 , k = 3，给定数组 [1,2,3,4,5,6,7] ，向右旋转后的结果为 [5,6,7,1,2,3,4]。
 * 注意:
 * 尽可能找到更多的解决方案，这里最少有三种不同的方法解决这个问题。
 *  author: WanZhiWen time: 2018年3月10日
 */
public class Solution3 {

	@Test
	public void test() {
		int[] temp = { -1 };
		int k = 2;
		if (k <= temp.length) {
			rotate2(temp, k);
		} else {
			rotate1(temp, k);
		}
		for (int i = 0; i < temp.length; i++) {
			System.out.print(temp[i] + " ");
		}
	}

	// 花费的时间太长了
	// 将包含 n个元素的数组向右旋转 k步——将数组右移k位
	public void rotate1(int[] nums, int k) {
		for (int i = 0; i < k; i++) {
			for (int j = (nums.length - 1); j > 0; j--) {
				int temp = nums[j];
				nums[j] = nums[j - 1];
				nums[j - 1] = temp;
			}
		}
	}

	// 只适合k <= n的情况，不适合k > n的情况
	public void rotate2(int[] nums, int k) {
		int[] tempArray = new int[k];
		for (int i = 0; i < tempArray.length; i++) {
			tempArray[i] = nums[nums.length - k + i];
		}
		for (int i = (nums.length - k - 1); i >= 0; i--) {
			int temp = nums[i];
			nums[i] = nums[i + k];
			nums[i + k] = temp;
		}
		for (int i = 0; i < k; i++) {
			nums[i] = tempArray[i];
		}
	}

	// 他人的答案——时间更短，也更健壮
	public void rotate(int[] nums, int k) {
		if (nums == null || nums.length == 0 || k % nums.length == 0)
			return;
		int t = k % nums.length;
		int m = nums.length - t;
		reverse(nums, 0, m - 1);
		reverse(nums, m, nums.length - 1);
		reverse(nums, 0, nums.length - 1);
	}

	private void reverse(int[] tnums, int s, int e) {
		while (s < e) {
			int tmps = tnums[s];
			tnums[s] = tnums[e];
			tnums[e] = tmps;
			s++;
			e--;
		}
	}
}
