/**
 * @author: WanZhiWen
 * @file: Main.java
 * @time: 2018-04-16 18:22
 **/
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 使用 for循环来不断的输入数据
        while (scanner.hasNextLine()) {
            // 注意：一定要使用nextLine来获取输入数据
            String input = scanner.nextLine();
            String[] nums = input.split(" ");
            int[] tempx = new int[2];
            for (int i = 0; i < 2; i++) {
                tempx[i] = Integer.parseInt(nums[i+1]);
            }
            int numberSum = (int) (Math.pow(2,tempx[0]) - Math.pow(2,tempx[1]));
            System.out.println(numberOf1(numberSum)+1);

        }
        scanner.close();
    }

    private static int numberOf1(int n){
        int count = 0;
        while(n!=0){
            n = n&(n-1);
            count++;
        }
        return count;
    }
}