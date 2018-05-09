package sortingAlgorithm;

import java.util.Arrays;

public class mergeSortBU implements SortInterface{

	@Override
	public void sort(int[] arr) {
		int n = arr.length;
		for (int size = 1; size <= n; size = 2*size) {
			for (int i = 0; i + size < n; i += 2*size) {
				// 对arr[i...i+size-1]和arr[i+size...i+2*size-1]进行归并
				merge(arr, i, i + size - 1, Math.min(i + 2*size - 1, n-1));
			}
		}
	}
	
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
