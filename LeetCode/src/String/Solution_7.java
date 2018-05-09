/**
 * @author: WanZhiWen
 * @file: Solution_7.java
 * @time: 2018-05-09 13:09
 * <p>
 * 实现strStr()
 * 实现 strStr() 函数。
 * <p>
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 * <p>
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * <p>
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 **/
package String;

import org.junit.Test;

public class Solution_7 {

    @Test
    public void test() {
        System.out.println(strStr("hello", "ll"));
        System.out.println(strStr("hella", "la"));
        System.out.println(strStr("aaaaa", "bba"));
        System.out.println(strStr("aaaaa", ""));
        System.out.println(strStr("", "sd"));
    }

    public int strStr(String haystack, String needle) {
        if (needle.equals("")) {
            return 0;
        }
        if (haystack.equals("")) {
            return -1;
        }
        int haystackLength = haystack.length();
        int needleLength = needle.length();
        for (int i = 0; i < (haystackLength - needleLength + 1); i++) {
            String s = haystack.substring(i, i + needleLength);
            if (needle.equals(s)) {
                return i;
            }
        }
        return -1;
    }

    public int strStr2(String haystack, String needle) {
        if (haystack == null || needle == null)
            return -1;
        if (haystack.equals("") || needle.equals(""))
            return 0;
        if (needle.length() > haystack.length()) {
            return -1;
        }
        // 直接使用String 内置的indexOf方法来查找needle出现的位置
        return haystack.indexOf(needle);
    }


    public int strStr3(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        int i = 0;
        int j = 0;
        for (i = 0; i < haystack.length() - needle.length() + 1; i++) {
            for (j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }
            if (j == needle.length()) {
                return i;
            }
        }
        return -1;
    }
}
