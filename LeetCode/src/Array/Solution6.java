package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * 给定两个数组，写一个方法来计算它们的交集。
 * 
 * 例如: 给定 nums1 = [1, 2, 2, 1], nums2 = [2, 2], 返回 [2, 2].
 * 
 * 注意：
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。 
 * 我们可以不考虑输出结果的顺序。
 * 
 * 跟进:
 * 
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？ 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果nums2的元素存储在磁盘上，内存是有限的，你不能一次加载所有的元素到内存中，你该怎么办？ 
 * author: WanZhiWen time:
 * 2018年3月11日
 */
public class Solution6 {
	@Test
	public void test() {
		int[] nums1 = {1,2};
		int[] nums2 = {1,1};
		int[] temp = intersect(nums1, nums2);
		for (int i = 0; i < temp.length; i++) {
			System.out.println(temp[i] + " ");
		}
	}

	public int[] intersect(int[] nums1, int[] nums2) {
		List<Integer> temp = new ArrayList<Integer>();
		for (int i = 0; i < nums1.length; i++) {
			for (int j = 0; j < nums2.length; j++) {
				if (nums1[i] == nums2[j]) {
					temp.add(nums1[i]);
					// 将nums2[j]从nums2数组中去除
					nums2 = getridvalue(nums2,j);
					break;
				}
			}
		}
		int[] temp2 = new int[temp.size()];
		for (int i = 0; i < temp2.length; i++) {
			temp2[i] = temp.get(i);
		}
        return temp2;
    }

	// 将数组nums2中的索引为j的元素删除
	private int[] getridvalue(int[] nums2, int j) {
		int[] temp = new int[nums2.length-1];
		for (int i = 0; i < temp.length; i++) {
			if (i < j) {
				temp[i] = nums2[i];
			}else {
				temp[i] = nums2[i+1];
			}
		}
		return temp;
	}
	
	// 方法2，运行时间只有5秒左右
	public int[] intersect2(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0, j = 0; i < nums1.length && j < nums2.length;) {
			if (nums1[i] == nums2[j]) {
				list.add(nums1[i]);
				i++;
				j++;
			} else if (nums1[i] > nums2[j]) {
				j++;
			} else {
				i++;
			}
		}

		int[] res = new int[list.size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = list.get(i);
		}
		return res;
	}
}
