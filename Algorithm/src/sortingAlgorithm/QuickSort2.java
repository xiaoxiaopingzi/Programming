package sortingAlgorithm;

import java.util.Random;

public class QuickSort2 implements SortInterface{

	@Override
	public void sort(int[] arr) {
		quickSort(arr, 0 ,arr.length - 1);
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

	// 对大量重复数组的优化
	// 对arr[L...r]部分进行partition操作
	// 返回索引值p,使得arr[L...p-1] < arr[p] < arr[p+1...r]
	private int partition(int[] arr, int L, int r) {
		// 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
		Random random = new Random();
		SortHelper.swap(arr, L, random.nextInt(r-L+1)+L);
		int v = arr[L];
		// arr[l+1...i) <= v; arr(j...r] >= v
		int i = L + 1, j = r;
		while(true){
			// 不能写 arr[i]<= v，因为这样会造成两方的树失去平衡
			while (i <= r && arr[i] < v) {
				i++;
			}
			while (j >= L + 1 && arr[j] > v) {
				j--;
			}
			if (i > j) {
				break;
			}
			SortHelper.swap(arr, i, j);
			i++;
			j--;
		}
		SortHelper.swap(arr, L , j);
		return j;
	}

}
