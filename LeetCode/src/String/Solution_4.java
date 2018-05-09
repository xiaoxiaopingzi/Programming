/**
 * @author: WanZhiWen
 * @file: Solution_4.java
 * @time: 2018-05-07 14:31
 * <p>
 * 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的一个字母异位词。
 * <p>
 * 例如，
 * s = "anagram"，t = "nagaram"，返回 true
 * s = "rat"，t = "car"，返回 false
 * <p>
 * 注意:
 * 假定字符串只包含小写字母。
 * <p>
 * 提升难度:
 * 输入的字符串包含 unicode 字符怎么办？你能能否调整你的解法来适应这种情况？
 **/
package String;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution_4 {

    @Test
    public void test() {
        System.out.println(isAnagram("car", "rat"));
    }

    // 用时 68 ms
    // 将字符串s和t中的每个字符存放到一个hashMap中，然后比较这两个hashMap，
    // 如果这两个hashMap是不相同的，则返回false，否则返回true
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        final Map<Character, Integer> mapS = new HashMap<>();
        final Map<Character, Integer> mapT = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char letter = s.charAt(i);
            char letterT = t.charAt(i);
            if (!mapS.containsKey(letter)) {
                mapS.put(letter, 1);
            } else {
                int temp = mapS.get(letter) + 1;
                mapS.put(letter, temp);
            }
            if (!mapT.containsKey(letterT)) {
                mapT.put(letterT, 1);
            } else {
                int temp = mapT.get(letterT) + 1;
                mapT.put(letterT, temp);
            }
        }

        // 比较两个Map的key-value是否完全相同
        for (char schar : mapS.keySet()) {
            int temp = mapS.get(schar);
            int temp2 = 0;
            try {
                temp2 = mapT.get(schar);
            } catch(Exception e){
                return false;
            }
            if (temp != temp2) {
                return false;
            }
        }
        return true;
    }



    // 用时 4 ms
    public boolean isAnagram2(String s, String t) {
        int[] ints = new int[26];
        int[] intt = new int[26];
        for (char c : s.toCharArray()){
            ints[c - 'a']++;
        }
        for (char c : t.toCharArray()){
            intt[c - 'a']++;
        }
        return Arrays.equals(ints,intt);
    }
}
