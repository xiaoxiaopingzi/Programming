package Array;

import org.junit.Test;

import java.util.Arrays;

/**
 * 加一
 * 给定一个非负整数组成的非空数组，在该数的基础上加一，返回一个新的数组。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * 示例 2:
 * <p>
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 * author: WanZhiWen
 * time: 2018年4月3日
 */
public class Solution7 {

    @Test
    public void test() {
        int[] nums1 = {7,
                2, 8, 5, 0, 9, 1, 2, 9, 5, 3, 6, 6, 7, 3, 2, 8, 4, 3, 7, 9, 5, 7, 7, 4, 7, 4, 9, 4, 7, 0, 1, 1, 1, 7, 4, 0, 0, 6};
        System.out.println(Arrays.toString(plusOne(nums1)));
    }

    // 不合实际，当输入的数组的长度很长时，根本无法运行
    public int[] plusOne2(int[] digits) {
        long sum = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            sum += digits[i] * Math.pow(10, digits.length - i - 1);
        }
        String s = (sum + 1) + "";
        char[] chars = s.toCharArray();
        int[] nums = new int[chars.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(chars[i] + "");
        }
        return nums;
    }


    public int[] plusOne(int[] digits) {
        boolean isContinue = true;
        int index = digits.length - 1;
        while (isContinue) {
            if (index >= 0) {
                int temp = digits[index] + 1;
                if (temp == 10) {
                    digits[index] = 0;
                    index -= 1;
                } else {
                    digits[index] = temp;
                    isContinue = false;
                }
            } else {
                int[] nums = new int[digits.length + 1];
                nums[0] = 1;
                return nums;
            }
        }
        return digits;
    }

}
