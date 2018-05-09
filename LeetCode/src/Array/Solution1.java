package Array;

import org.junit.Test;

/**
 * 给定一个有序数组，你需要原地删除其中的重复内容，使每个元素只出现一次,并返回新的长度。
 * 不要另外定义一个数组，您必须通过用 O(1) 额外内存原地修改输入的数组来做到这一点。
 * <p>
 * 示例：
 * 给定数组: nums = [1,1,2],
 * 你的函数应该返回新长度 2, 并且原数组nums的前两个元素必须是1和2
 * 不需要理会新的数组长度后面的元素
 * author: WanZhiWen
 * time: 2018年3月10日
 */
public class Solution1 {

    @Test
    public void test() {
        int[] nums = {1, 1, 1, 1, 2, 2, 3, 4, 4, 5, 5, 5, 6};
        int temp = removeDuplicates(nums);
        System.out.println(temp);
        for (int i = 0; i < temp; i++) {
            System.out.print(nums[i] + " ");
        }
    }


    private int removeDuplicates(int[] nums) {
        int newLength = 0;
        for (int i = 0; i < nums.length; i++) {
            int temp = duplicatesLength(i, nums);
            i = i + temp;
            nums[newLength] = nums[i];
            newLength = newLength + 1;
        }
        return newLength;
    }

    // 返回和索引j的元素相同的元素的个数
    private int duplicatesLength(int j, int[] nums) {
        int temp = 0;
        for (int i = j; (i + 1) < nums.length; i++) {
            if (nums[j] == nums[i + 1]) {
                temp += 1;
            } else {
                break;
            }
        }
        return temp;
    }


    // 其他人的运行时间最短的解答方式
    // 基本思想是利用一个指针index来表示无重复元素的数组的索引，把index所在的数组"看成"一个新的数组
    public int removeDuplicatesOther(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int index = 1;
        for (int i = 1; i < nums.length; i++) {
//			i和index都从1开始，如果nums[1]和nums[0]不相等，则说明nums[0]不需要改变，
            if (nums[i] != nums[i - 1]) {
                nums[index] = nums[i];
                index += 1;
            }
        }
        return index;
    }
}
