package sortingAlgorithm;

// 插入排序——对于非常有序的数组，插入排序的时间复杂度是o(n),非常简洁
public class InsertSort implements SortInterface{
	public  void sort(int[] arr) {
		// 不断挪动元素，直到找到元素合适的位置
		for (int i = 1; i < arr.length; i++) {
			// 寻找元素arr[i]合适的插入位置
			int temp = arr[i];
			int j; // j保存元素e应该插入的位置
			for (j = i; j > 0; j--) {
				if (temp < arr[j-1]) {
					arr[j] = arr[j-1];
				}else {
					break; // 找到了合适的位置就终止
				}
			}
			// 将元素arr[i]插入到合适的位置
			arr[j] = temp;
		}
	}
	
	// 未经过优化的插入排序方法，需要进行大量的数组之间元素的交换，因此效率不高
	public  void sortNoOptimization(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j > 0 & (arr[j] < arr[j-1]); j--) {
				SortHelper.swap(arr, j, j-1);
			}
		}
	}

	// 在归并排序中，当r-L较小时，可以使用插入排序，因为此时元素有序的可能性更大
	public void sort(int[] arr, int L, int r) {
		assert L >= 0 && L <= r && r < arr.length;
		for (int i = L + 1; i <= r; i++) {
			int temp = arr[i];
			int j; 
			for (j = i; j > L && (temp < arr[j-1]); j--) {
				arr[j] = arr[j-1];
			}
			arr[j] = temp;
		}
	}
}
