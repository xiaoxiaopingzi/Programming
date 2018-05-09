/**
 * @author: WanZhiWen
 * @file: Solution_5.java
 * @time: 2018-05-07 21:34
 * <p>
 * 验证回文字符串
 * <p>
 * 回文串”是一个正读和反读都一样的字符串，比如“level”或者“noon”等等就是回文串。
 * <p>
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "race a car"
 * 输出: false
 **/
package String;

import org.junit.Test;

public class Solution_5 {
    @Test
    public void test() {
        System.out.println(isPalindrome2("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome2("race a car"));
    }

    public boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }
        String[] strs = s.split("\\W*");
        StringBuilder builder = new StringBuilder();
        for (String temp : strs) {
            builder.append(temp);
        }
        String regS = builder.toString().toLowerCase();
        char[] chars = regS.toCharArray();
        for (int i = 0; i < (chars.length / 2); i++) {
            if (chars[i] != chars[chars.length - i - 1]) {
                return false;
            }
        }
        return true;
    }


    public boolean isPalindrome2(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }
        String str = s.replaceAll("[^0-9a-zA-Z]", "").toLowerCase();
        char[] chars = str.toCharArray();
        for (int i = 0; i < (chars.length / 2); i++) {
            if (chars[i] != chars[chars.length - i - 1]) {
                return false;
            }
        }
        return true;
    }


    // 用时最短
    public boolean isPalindrome3(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int start = 0;
        int end = s.length() - 1;
        s = s.toLowerCase();
        while (start < end) {
            // 碰到非0-9，a-z、A-Z的字符就直接跳过
            while (start < s.length() &&
                    (s.charAt(start) < '0' || (s.charAt(start) > '9' && s.charAt(start) < 'a') || s.charAt(start) > 'z')) {
                start++;
            }
            while (end >= 0 &&
                    (s.charAt(end) < '0' || (s.charAt(end) > '9' && s.charAt(end) < 'a') || s.charAt(end) > 'z')) {
                end--;
            }
            if (start < end && s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
