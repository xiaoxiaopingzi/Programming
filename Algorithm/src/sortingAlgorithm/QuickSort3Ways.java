package sortingAlgorithm;

import java.util.Random;

// 快速排序的第三种方法——三路处理
public class QuickSort3Ways implements SortInterface{

	@Override
	public void sort(int[] arr) {
		quickSort(arr, 0 ,arr.length - 1);
	}

	// 对arr[L...r]部分进行快速排序
	private void quickSort(int[] arr, int L, int r) {
		if (L >= r) {
			return;
		}
		if (r - L <= 15) {
			InsertSort insertSort = new InsertSort();
			insertSort.sort(arr, L, r);
			return;
		}
		// partition
		int[]  p = partition(arr, L, r);
		quickSort(arr, L, p[0]);
		quickSort(arr, p[1], r);
	}

	// 将arr[L...r]分为 <v; ==v; >v 三部分
	// 之后递归对<v ; >v 两部分进行三路快速排序
	private int[] partition(int[] arr, int L, int r) {
		Random random = new Random();
		SortHelper.swap(arr, L, random.nextInt(r-L+1)+L);
		int v = arr[L];
		
		// 根据下面的变量的初始值得到的注释数组都为空
		int lt = L; // arr[L+1...lt] < v
		int gt = r + 1; // arr[gt...r] > v
		int i = L + 1; // arr[lt+1...i) == v
		
		while (i < gt) {
			if(arr[i] < v){
				SortHelper.swap(arr, i, lt+1);
				lt++;
				i++;
			}else if (arr[i] > v) {
				SortHelper.swap(arr, i, gt-1);
				gt--;
			}else {
				i++;
			}
		}
		SortHelper.swap(arr, L, lt);
		int[] indexArray = new int[2];
		indexArray[0] = lt - 1 ;
		indexArray[1] = gt ;
		return indexArray;
	}
}
