/**
 * @author: WanZhiWen
 * @file: Interview_3.java
 * @time: 2018-05-06 14:30
 * <p>
 * 剑指offer——面试题3
 * 面试题3（一）：找出数组中重复的数字
 * 题目：在一个长度为n的数组里的所有数字都在0到n-1的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，
 * 也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。例如，如果输入长度为7的数组{2, 3, 1, 0, 2, 5, 3}，
 * 那么对应的输出是重复的数字2或者3。
 **/

public class Interview_3 {
    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 3, 4, 4};
        System.out.println(duplcate(nums));
    }

    // 判断数组中是否存在重复的数字
    //   true  - 输入有效，并且数组中存在重复的数字
    //   false - 输入无效，或者数组中没有重复的数字
    private static boolean duplcate(int[] numbers) {
        if (numbers.length <= 0) {
            return false;
        }
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < 0 || numbers[i] > (numbers.length - 1)) {
                return false;
            }
        }
        for (int i = 0; i < numbers.length; i++) {
            while (numbers[i] != i) {
                if (numbers[i] == numbers[numbers[i]]) {
                    return true;
                } else {
                    int temp = numbers[i];
                    numbers[i] = numbers[temp];
                    numbers[temp] = temp;
                }
            }
        }
        return false;
    }
}
