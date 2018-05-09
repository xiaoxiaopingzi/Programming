/**
 * @author: WanZhiWen
 * @file: Solution_1.java
 * @time: 2018-04-06 14:42
 *
 * 反转字符串
 * 请编写一个函数，其功能是将输入的字符串反转过来。
 * 示例：
 * 输入：s = "hello"
 * 返回："olleh"
 **/
package String;

import org.junit.Test;

import java.util.Arrays;

public class Solution_1 {

    @Test
    public void test() {
        System.out.println(reverseString("hello"));
    }

    // 超出时间限制
    public String reverseString(String s) {
        int sLength = s.length();
        if (s == null || sLength <= 1) {
            return s;
        }
        char[] returnS = new char[sLength];
        for (int i = (sLength - 1); i >= 0; i--) {
            returnS[sLength - i - 1] = s.charAt(i);
        }
        return String.valueOf(returnS);
    }

    public String reverseString2(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        // 可以使用字符串内部的方法将字符串转换为字符数组
        char[] arr = s.toCharArray();
        int length = arr.length;
        for (int i = 0; i < length / 2; i++) {
            char temp = arr[i];
            arr[i] = arr[length - i - 1];
            arr[length - i - 1] = temp;
        }
        return String.valueOf(arr);
    }

    public String reverseString3(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        return new StringBuilder(s).reverse().toString();
    }
}
