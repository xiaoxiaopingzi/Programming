package sortingAlgorithm;

// 选择排序
public class SelectionSort implements SortInterface{
	// 对数组进行从小到大排序
	public  void sort(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			// 寻找[i,n)区间的最小值
			int minIndex = i;
			for (int j = i + 1; j < n; j++) {
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}
			SortHelper.swap(arr, i, minIndex);
		}
	}
}
 