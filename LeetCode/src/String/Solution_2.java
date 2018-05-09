/**
 * @author: WanZhiWen
 * @file: Solution_2.java
 * @time: 2018-05-06 21:40
 * 颠倒整数
 * <p>
 * 给定一个 32 位有符号整数，将整数中的数字进行反转。
 * <p>
 * 示例 1:
 * 输入: 123
 * 输出: 321
 * 示例 2:
 * <p>
 * 输入: -123
 * 输出: -321
 * 示例 3:
 * <p>
 * 输入: 120
 * 输出: 21
 * <p>
 * 注意:
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。
 * 根据这个假设，如果反转后的整数溢出，则返回 0。
 *
 * #### 方法1
 * 每次加上x%10，然后x不断减小～0
 * 注意处理MAX_VALUE, MIN_VALUE
 * 符号不重要, 直接处理, 也会保留.
 *
 * #### 方法2
 * 转换成String 然后 reverse
 * Space O(n), time O(n)
 *
 **/
package String;

import org.junit.Test;

public class Solution_2 {
    @Test
    public void test() {
        System.out.println(reverse2(-13123));
        System.out.println(reverse(-13123));
    }

    //Operate directly on the number and compute the final result using %
    public int reverse2(int x) {
        long rst = 0;
        while (x != 0) {
            rst = rst * 10 + x % 10;
            x /= 10;
            if (rst > Integer.MAX_VALUE || rst < Integer.MIN_VALUE) {
                return 0;
            }
        }
        return (int) rst;
    }


    public int reverse(int x) {
        if (-9 <= x && x <= 9) {
            return x;
        } else {
            String temp = null;
            if (x < 0) {
                temp = "-" + reverseString((-x) + "");
            } else {
                temp = reverseString(x + "");
            }
            try {
                return Integer.parseInt(temp);
            } catch(Exception e){
                // 如果反转后的整数出现溢出，则直接返回0
                return 0;
            }
        }
    }

    public String reverseString(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        return new StringBuilder(s).reverse().toString();
    }
}
