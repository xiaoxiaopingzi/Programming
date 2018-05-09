package maxHeap;

import sortingAlgorithm.SortInterface;

public class HeapSort implements SortInterface{

	@Override
	public void sort(int[] arr) {
		int n = arr.length;
		MaxHeap<Integer> maxHeap = new MaxHeap<Integer>(n);
		for (int i = 0; i < n; i++) {
			maxHeap.insert(arr[i]);
		}
		for (int i = n -1 ; i >= 0; i--) {
			arr[i] = maxHeap.extractMax();
		}
	}
}
