package Array;

import org.junit.Test;

/**
 * 旋转图像 
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * 
 * 将图像旋转 90 度（顺时针）。
 * 
 * 注意：
 * 
 * 你必须在原矩阵中旋转图像，请不要使用另一个矩阵来旋转图像。
 * 
 * 例 1:
 * 
 * 给出的输入矩阵 = [ [1,2,3], [4,5,6], [7,8,9] ],
 * 
 * 旋转输入矩阵，使其变为 : [ [7,4,1], [8,5,2], [9,6,3] ]
 * 
 * 
 * 例 2:
 * 
 * 给出的输入矩阵 = [ [ 5, 1, 9,11], [ 2, 4, 8,10], [13, 3, 6, 7], [15,14,12,16] ],
 * 
 * 旋转输入矩阵，使其变为 : [ [15,13, 2, 5], [14, 3, 4, 1], [12, 6, 8, 9], [16, 7,10,11] ]f
 * author: WanZhiWen time: 2018年4月5日
 */
public class Solution11 {
	@ Test
	public void test(){
		int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
		rotate(matrix);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	
	public void rotate(int[][] matrix) {
		int n = matrix.length;
		int[][] temp = new int[n][n];
		for (int i = 0; i < n; i++) {
			int[] tempRow = matrix[i];
			for (int j = 0; j < n; j++) {
				temp[j][n-i-1] = tempRow[j];
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix[i][j] = temp[i][j];
			}
		}
	}
}
