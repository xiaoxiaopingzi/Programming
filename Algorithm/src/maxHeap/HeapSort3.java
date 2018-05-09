package maxHeap;

import sortingAlgorithm.SortInterface;

// 原地堆排序
public class HeapSort3 implements SortInterface {

	@Override
	public void sort(int[] arr) {
		int n = arr.length;
		// 先将数组变为最大堆
		for (int i = (n - 1) / 2; i >= 0; i--) {
			// 从第一个非叶子节点开始进行shiftDown()操作
			shiftDown(arr, n, i);
		}
		for (int i = n - 1; i > 0; i--) {
			swap(arr, 0, i); 
			shiftDown(arr, i, 0);

		}
	}

	/**
	 * 对arr数组中的n个元素从k开始shiftDown()
	 * @param arr
	 * @param n
	 * @param k——开始shiftDown的索引，从0开始
	 */
	private void shiftDown(int[] arr, int n, int k) {
		while ( 2*k + 1 < n) {
			int j = 2 * k + 1; 
			if( j + 1 < n && arr[j+1] > arr[j] ){
				j += 1; // 当右孩子大于左孩子则j的索引就设为右孩子
			}
			if( arr[k] > arr[j]){
				break;
			}
			swap(arr, k, j);
			k = j; // 更新k的索引位置
		}
	}

	private void swap(int[] arr, int k, int j) {
		int temp = arr[k];
		arr[k] = arr[j];
		arr[j] = temp;
	}
}
