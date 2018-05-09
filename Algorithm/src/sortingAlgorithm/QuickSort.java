package sortingAlgorithm;

import java.util.Random;

public class QuickSort implements SortInterface{

	@Override
	public void sort(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}

	// 对arr[L...r]部分进行快速排序
	private void quickSort(int[] arr, int L, int r) {
		if (L < r){
			return;
		}
		if (r-L<=15){
			InsertSort insertSort = new InsertSort();
			insertSort.sort(arr, L, r);
			return;
		}
        int p = partition(arr, L, r);
		quickSort(arr, L, p - 1);
		quickSort(arr, p + 1, r);
	}

	// 对arr[L...r]部分进行partition操作
	// 返回索引值p,使得arr[L...p-1] < arr[p] < arr[p+1...r]
	private int partition(int[] arr, int L, int r) {
		// 通过下面的两行代码，将arr[L]的值设置为arr[L...r]中一个随机的值
		// 这样就避免了在近乎完全有序的数组中由于固定选择arr[L]这个最小的数而导致生成的排序树十分不均匀的情况
		// 通过下面的随机的方法来进行排序，其算法复杂度的期望为o(nlogn)，算法退化到o(n*n)的概率极其低
		// 对十分有序的数组的优化
		Random random = new Random();
		SortHelper.swap(arr, L, random.nextInt(r-L+1)+L);
		int v = arr[L];
		int j = L;
		// arr[L+1...j] < v ; arr[j+1...i)>v
		for (int i = j + 1; i <= r; i++) {
			if (arr[i] < v) {
//				SortHelper.swap(arr, i, ++j); // 下面的两行代码可以写成下面一行
				SortHelper.swap(arr, i, j + 1);
				j++;
			}
		}
		SortHelper.swap(arr, L, j);
		return j;
	}

}
