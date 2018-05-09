/**
 * @author: WanZhiWen
 * @file: Solution_9.java
 * @time: 2018-05-09 13:58
 * <p>
 * <p>
 * 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * <p>
 * 所有输入只包含小写字母 a-z 。
 **/
package String;

import org.junit.Test;

public class Solution_9 {

    @Test
    public void test() {
//        String[] strs = {"dog","racecar","car"};
//        String[] strs = {"flower","flow","flight"};
        String[] strs = {"aa", "aa"};
        System.out.println(longestCommonPrefix(strs));
    }

    // 用时10 ms
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        int minLength = Integer.MAX_VALUE;
        for (String s : strs) {
            if (s.length() < minLength) {
                minLength = s.length();
            }
        }
        if (minLength == 0) {
            return "";
        }
        String base = strs[0];
        int index = 0;
        for (int i = 0; i < minLength; i++) {
            boolean isBreak = false;
            for (String s : strs) {
                if (s.charAt(i) != base.charAt(i)) {
                    isBreak = true;
                    break;
                }
            }
            if (isBreak) {
                index = i - 1;
                break;
            } else {
                index = i;
            }
        }
        if (index < 0) {
            return "";
        } else {
            return base.substring(0, index + 1);
        }
    }


    // 用时 7 ms，没有求minLength， 通过strs[i].length() > ind来保证不会出现出现数组越界
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String prefix = "";
        int ind = 0;
        while (ind < strs[0].length()) {
            char c = strs[0].charAt(ind);
            boolean valid = false;
            for (int i = 1; i < strs.length; i++) {
                if (strs[i].length() > ind && strs[i].charAt(ind) == c) {
                    valid = true;
                } else {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                prefix += "" + c;
            } else {
                break;
            }
            ind++;
        }//END WHILE
        return prefix;
    }
}
