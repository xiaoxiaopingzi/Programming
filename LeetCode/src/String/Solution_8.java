/**
 * @author: WanZhiWen
 * @file: Solution_8.java
 * @time: 2018-05-09 13:58
 * <p>
 * 数数并说
 * 报数序列是指一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
 * <p>
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 * <p>
 * 给定一个正整数 n ，输出报数序列的第 n 项。
 * <p>
 * 注意：整数顺序将表示为一个字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1
 * 输出: "1"
 * 示例 2:
 * <p>
 * 输入: 4
 * 输出: "1211"
 **/
package String;

import org.junit.Test;

public class Solution_8 {

    @Test
    public void test(){
        System.out.println(countAndSay(2));
        System.out.println(countAndSay(3));
        System.out.println(countAndSay(4));
    }


    public String countAndSay(int n) {
        if (n <= 1) {
            return n + "";
        }
        String curr = "1";
        for (int i = 2; i <= n; i++) {
            int count = 1;
            char c = curr.charAt(0);
            int size = curr.length();
            StringBuffer sb = new StringBuffer();
            for (int j = 1; j < size; j++) {
                if (curr.charAt(j) == curr.charAt(j - 1)) {
                    count++;
                } else {
                    sb.append(count + String.valueOf(c));
                    c = curr.charAt(j);
                    count = 1;
                }
            }
            sb.append(count + String.valueOf(c)); // append end letter for each row
            curr = sb.toString();
        }

        return curr;
    }
}
