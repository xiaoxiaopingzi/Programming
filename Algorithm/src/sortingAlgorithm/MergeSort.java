package sortingAlgorithm;

import java.util.Arrays;

// 归并排序
public class MergeSort implements SortInterface{

	@Override
	public void sort(int[] arr) {
		mergeSort(arr, 0, arr.length - 1);
	}
	
	// 递归使用归并排序，对arr[L...r]的范围进行排序
	private void mergeSort(int[] arr, int L, int r){
//		if (L >= r) {
//			return;
//		}
		
		// 优化2: 对于小规模数组, 使用插入排序
		if (r - L <= 15) {
			InsertSort insertSort = new InsertSort();
			insertSort.sort(arr, L, r);
			return;
		}
		
//		int mid = (L + r) / 2; // 当L和r都是很大的整数时，会出现溢出
		int mid = L / 2 + r / 2 ; 
		mergeSort(arr, L, mid);
		mergeSort(arr, mid + 1, r);
		
		// 优化1: 对于arr[mid] <= arr[mid+1]的情况,不进行merge
        // 对于近乎有序的数组非常有效,但是对于一般情况,有一定的性能损失
		if (arr[mid] > arr[mid+1]) {
			merge(arr, L, mid, r);
		}
	}

	// 归并
	// 将arr[L...mid]和arr[mid+1...r]两部分进行归并
	private void merge(int[] arr, int L, int mid, int r) {
		// 临时空间，用于存储arr数组未排序前的值
		int[] aux = Arrays.copyOfRange(arr, L, r+1); 
		for (int i = L; i <= r; i++) {
			aux[i-L] = arr[i];  // i最开始的值为L
		}
		// 初始化，i指向左半部分的起始索引位置l；j指向右半部分起始索引位置mid+1
		int i = L;
		int j = mid + 1;
		for (int k = L; k <= r; k++) {
			// 首先确认左索引未发生越界
			if (i > mid) {   // 如果左半部分元素已经全部处理完毕
				arr[k] = aux[j-L];
				j++;
			}else if (j > r) { // 如果右半部分元素已经全部处理完毕
				arr[k] = aux[i-L];
				i++;
			}else if(aux[i-L] < aux[j-L]){ // 左半部分所指元素 < 右半部分所指元素
				arr[k] = aux[i-L];	
				i++;
			}else { // 左半部分所指元素 >= 右半部分所指元素
				arr[k] = aux[j-L];
				j++;
			}
		}
	}
	
}
