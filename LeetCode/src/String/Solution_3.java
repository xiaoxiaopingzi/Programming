/**
 * @author: WanZhiWen
 * @file: Solution_3.java
 * @time: 2018-05-07 13:00
 * <p>
 * 字符串中的第一个唯一字符
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * <p>
 * 案例:
 * <p>
 * s = "leetcode"
 * 返回 0.
 * <p>
 * s = "loveleetcode",
 * 返回 2.
 * <p>
 * <p>
 * 注意事项：您可以假定该字符串只包含小写字母。
 **/
package String;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Solution_3 {

    @Test
    public void test() {
        System.out.println(firstUniqChar("loveleetcode"));
    }

    // 用时 65 ms
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int times = 0;
            for (int j = 0; j < chars.length; j++) {
                if (chars[i] == chars[j]) {
                    times += 1;
                    // 如果chars[i]在整个字符串中出现了两次以上，则说明该元素重复了，则可以直接开始下一轮循环
                    if (times > 1) {
                        break;
                    }
                }
            }
            if (times == 1) {
                return i;
            }
        }
        return -1;
    }

    //方法2: 按照题意, 找到第一个 first index == last index的字母.
    //
    //方法3: 用hashmap存字母的index, 有些重复字母的index就会是个list.
    //      找到单一index, 结合成list, sort, return list.get(0)
    // 用时 41 ms
    public int firstUniqChar2(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    // 用时78ms
    public int firstUniqChar3(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        final Map<Character, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            final char letter = s.charAt(i);
            if (!map.containsKey(letter)) {
                map.put(letter, new ArrayList<Integer>());
            }
            map.get(letter).add(i);
        }
        final ArrayList<Integer> indexes = new ArrayList<>();
        for (Map.Entry<Character, ArrayList<Integer>> entry : map.entrySet()) {
            // 这样indexes集合中保存的就是字符串s中所有未重复的字符的索引
            if (entry.getValue().size() == 1) {
                indexes.addAll(entry.getValue());
            }
        }

        if (indexes.size() == 0) {
            return -1;
        }
        // 对indexes进行排序，排序之后，indexes的第一个元素就是我们所需要的值
        Collections.sort(indexes);
        return indexes.get(0);
    }

    // 用时 7 ms
    public int firstUniqChar4(String s) {
        int start;
        int end;
        int result = s.length();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            start = s.indexOf(ch);
            end = s.lastIndexOf(ch);
            // start == -1表示字符串s中不存在这个字符
            // start == end表示字符串s中该字符只出现了一次
            if (start == end && start != -1) {
                // 需要比较result, start的大小，因为我们需要找的是第一个不重复的字符，即索引值最小的不重复的字符
                result = Math.min(result, start);
            }
        }
        if (result == s.length()) {
            return -1;
        }
        return result;
    }
}
