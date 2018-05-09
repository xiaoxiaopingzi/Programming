package Array;

import java.util.HashSet;
import java.util.Set;

/**
 * 有效的数独 判断一个数独是否有效，
 * 根据：Sudoku Puzzles - The Rules。
 * 
 * 数独部分填了数字，空的部分用 '.' 表示。
 * 
 * 说明: 一个有效的数独（填了一部分的）不一定是可解的，只要已经填的数字是有效的即可。
 *  author: WanZhiWen time: 2018年4月3日
 */
public class Solution10 {
	char[] tempColumn = new char[9];
	char[] tempGrid = new char[9];
	public boolean isValidSudoku(char[][] board) {
		for (int i = 0; i < 9; i++) {
			// 对数独矩阵的某一行进行校验
			if (!Check(board[i])) {
				return false;
			}
			// 取数独矩阵的一列
			for (int j = 0; j < 9; j++) {
				tempColumn[j] = board[j][i];
			}
			// 对数独矩阵的某一列进行校验
			if (!Check(tempColumn)) {
				return false;
			}
		}
		// 取数独矩阵的一个网格
		int index = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int j2 = 0; j2 < 3; j2++) {
					for (int j3 = 0; j3 < 3; j3++) {
						tempGrid[index] = board[3*i+j2][3*j+j3];
						index++;
					}
				}
				if (!Check(tempGrid)) {
					return false;
				}else {
					index = 0;
				}
			}
		}
		return true;
	}

	// 检查一行的数独是否是有效的(每行只能是1-9的数字，并且每个数字只能出现一次)
	private boolean Check(char[] cs) {
		// 使用Set集合去重
		Set<Integer> tempSet = new HashSet<Integer>();
		int length = 0;
		for (int i = 0; i < cs.length; i++) {
			try {
				int temp = Integer.parseInt(cs[i]+"");
				if (temp == 0) {
					return false;
				}else {
					length++;
					tempSet.add(temp);
				}
			} catch (Exception e) {
				if (".".equals(cs[i]+"")) {
					continue;
				}else {
					return false;
				}
			}
		}
		if (tempSet.size() != length) {
			return false;
		}else {
			return true;
		}
	}
}
