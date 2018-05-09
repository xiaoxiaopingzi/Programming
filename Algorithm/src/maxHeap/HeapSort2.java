package maxHeap;

import sortingAlgorithm.SortInterface;

public class HeapSort2 implements SortInterface{

	public void sort(int[] arr) {
		int n = arr.length;
		Integer[] arr2 = new Integer[n];
		for (int i = 0; i < arr2.length; i++) {
			arr2[i] = arr[i];
		}
		MaxHeap<Integer> maxHeap = new MaxHeap<Integer>(arr2);
		
//      不能使用data数组来获取排序好的结果，因为堆排序中，无父子关系的节点在数组中是无序的，
//		所以maxHeap对象中的data变量不是完全排好序的，只有在有父子关系的位置上才是有序的
//		Integer[] data = maxHeap.getData();
//		for (int i = n - 1 ; i >= 0; i--) {
//			arr[i] = data[n-i];
//		}
		for (int i = n -1 ; i >= 0; i--) {
			arr[i] = maxHeap.extractMax();
		}
	}
}
