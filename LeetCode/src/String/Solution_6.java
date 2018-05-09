/**
 * @author: WanZhiWen
 * @file: Solution_6.java
 * @time: 2018-05-08 22:00
 * <p>
 * 字符串转整数（atoi）
 * 实现 atoi，将字符串转为整数。
 * <p>
 * 在找到第一个非空字符之前，需要移除掉字符串中的空格字符。如果第一个非空字符是正号或负号，选取该符号，并将其与后面尽可能多的连续的数字组合起来，这部分字符即为整数的值。如果第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * <p>
 * 字符串可以在形成整数的字符后面包括多余的字符，这些字符可以被忽略，它们对于函数没有影响。
 * <p>
 * 当字符串中的第一个非空字符序列不是个有效的整数；或字符串为空；或字符串仅包含空白字符时，则不进行转换。
 * <p>
 * 若函数不能执行有效的转换，返回 0。
 * <p>
 * 说明：
 * <p>
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。如果数值超过可表示的范围，则返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "42"
 * 输出: 42
 * 示例 2:
 * <p>
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 * 我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * 示例 3:
 * <p>
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * 示例 4:
 * <p>
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 * 因此无法执行有效的转换。
 * 示例 5:
 * <p>
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 * 因此返回 INT_MIN (−231) 。
 **/
package String;

import org.junit.Test;

public class Solution_6 {


    @Test
    public void test() {
        System.out.println("输出为：" + myAtoi("4193 with words"));
        System.out.println("输出为：" + myAtoi("words and 987"));
        System.out.println("输出为：" + myAtoi("-91283472332"));
        System.out.println("输出为：" + myAtoi("   -42"));
        System.out.println("输出为：" + myAtoi("+42"));
        System.out.println("输出为：" + myAtoi("   +0 123"));
    }


    // 用时35 ms
    public int myAtoi(String str) {
        // 非空字符中间的空格不能删除，所以使用trim()方法删除字符串str首尾两端的空格
        String s = str.trim();
        if (s.equals("")) {
            return 0;
        }
        System.out.println(str + " 删除首尾空格后的结果为：" + s);
        char[] chars = s.toCharArray();
        boolean isNagtive = false;
        boolean hasZhengHao = false;
        int index = 0;
        if (chars[0] == '-') {
            isNagtive = true;
            index = 1;
        }
        if (chars[0] == '+') {
            hasZhengHao = true;
            index = 1;
        }
        int length = chars.length;
        int[] nums = new int[length];
        for (; index < length; index++) {
            try {
                nums[index] = Integer.parseInt(chars[index] + "");
            } catch (NumberFormatException e) {
                // 如果无法转换，直接跳出循环
                break;
            }
        }
        // index - 1的索引位置是最后的数字的位置
        index = index - 1;
        // 如果是负数
        if (isNagtive) {
            if (index < 1) {
                return 0;
            } else {
                long sum = 0;
                for (int i = 1; i <= index; i++) {
                    sum += Math.pow(10, index - i) * nums[i];
                }
                if ((-sum) < Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                } else {
                    return (int) (-sum);
                }
            }
        } else {
            if (index < 0) {
                return 0;
            } else {
                long sum = 0;
                int i = 0;
                if (hasZhengHao) {
                    i = 1;
                }
                for (; i <= index; i++) {
                    sum += Math.pow(10, index - i) * nums[i];
                }
                if (sum > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                } else {
                    return (int) sum;
                }
            }
        }
    }


    public int myAtoi2(String str) {

        if (str == null) {
            return 0;
        }
        str = str.trim();
        if (str.length() == 0) {
            return 0;
        }

        char[] arr = str.toCharArray();
        String rst = "";
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (i == 0 && (c == '+' || c == '-')) {
                rst += c;
            } else if (c < '0' || c > '9') {
                break;
            } else {
                rst += c;
            }
        }
        if (rst.length() == 0 || rst.equals("+") || rst.equals("-")) {
            return 0;
        }
        if (rst.length() > 11) {
            return rst.charAt(0) == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
        long largeNum = Long.parseLong(rst);
        if (largeNum > Integer.MAX_VALUE || largeNum < Integer.MIN_VALUE) {
            return largeNum > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        return Integer.parseInt(rst);
    }


    // 使用正则表达式进行匹配
    public int atoi3(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        str = str.replaceAll("\\s","");
        if (!str.matches("[+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)")) {
            return 0;
        }
        double rst = Double.parseDouble(str);
        if (rst > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (rst < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else {
            return (int)rst;
        }
    }
}
