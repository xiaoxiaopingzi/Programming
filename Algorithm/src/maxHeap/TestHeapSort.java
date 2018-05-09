package maxHeap;

import java.util.Arrays;

import org.junit.Test;

import sortingAlgorithm.MergeSort;
import sortingAlgorithm.QuickSort2;
import sortingAlgorithm.QuickSort3Ways;
import sortingAlgorithm.SortHelper;
import sortingAlgorithm.SortInterface;

public class TestHeapSort {

	/**
	 * 堆排序也是o(nlogn)级别的算法
	 */
	@Test
	public void testHeapSort(){
		int n = 100000;
		// 测试一般数组
		int[] arr = SortHelper.generateRandomArray(n, 0, n);
		int[] copyArray = Arrays.copyOf(arr, arr.length);
		int[] copyArray2 = Arrays.copyOf(arr, arr.length);
		int[] copyArray3 = Arrays.copyOf(arr, arr.length);
		int[] copyArray4 = Arrays.copyOf(arr, arr.length);
		int[] copyArray5 = Arrays.copyOf(arr, arr.length);
		SortInterface mergeSort = new MergeSort();
		SortInterface quickSort2 = new QuickSort2();
		SortInterface quickSort3Ways = new QuickSort3Ways();
		SortInterface heapSort = new HeapSort();
		HeapSort2 heapSort2 = new HeapSort2();
		SortInterface heapSort3 = new HeapSort3();
		System.out.println("Test for Random Array, size=" + n + ",random range [0, " + n + "]");
		SortHelper.sortTestHelper("mergeSort", mergeSort, arr);
		SortHelper.sortTestHelper("quickSort2", quickSort2, copyArray);
		SortHelper.sortTestHelper("quickSort3Ways", quickSort3Ways, copyArray2);
		SortHelper.sortTestHelper("heapSort", heapSort, copyArray3);
		SortHelper.sortTestHelper("heapSort2", heapSort2, copyArray4);
		SortHelper.sortTestHelper("heapSort3", heapSort3, copyArray5);
		
		System.out.println();
		
		// 测试近乎有序的数组
		int swapTimes = 100;
		System.out.println("Test for Nearly Ordered Array, size=" + n + ", swapTimes=" + swapTimes);
		arr = SortHelper.generateNearlyOrderedArray(n, swapTimes);
		copyArray = Arrays.copyOf(arr, arr.length);
		copyArray2 = Arrays.copyOf(arr, arr.length);
		copyArray3 = Arrays.copyOf(arr, arr.length);
		copyArray4 = Arrays.copyOf(arr, arr.length);
		copyArray5 = Arrays.copyOf(arr, arr.length);
		SortHelper.sortTestHelper("mergeSort", mergeSort, arr);
		SortHelper.sortTestHelper("quickSort2", quickSort2, copyArray);
		SortHelper.sortTestHelper("quickSort3Ways", quickSort3Ways, copyArray2);
		SortHelper.sortTestHelper("heapSort", heapSort, copyArray3);
		SortHelper.sortTestHelper("heapSort2", heapSort2, copyArray4);
		SortHelper.sortTestHelper("heapSort3", heapSort3, copyArray5);
		
		System.out.println();
		
		// 测试有大量重复的数组
		System.out.println("Test for a lot of repetition Array, size=" + n + ",random range [0,10]");
		arr = SortHelper.generateRandomArray(n, 0, 10);
		copyArray = Arrays.copyOf(arr, arr.length);
		copyArray2 = Arrays.copyOf(arr, arr.length);
		copyArray3 = Arrays.copyOf(arr, arr.length);
		copyArray4 = Arrays.copyOf(arr, arr.length);
		copyArray5 = Arrays.copyOf(arr, arr.length);
		SortHelper.sortTestHelper("mergeSort", mergeSort, arr);
		SortHelper.sortTestHelper("quickSort2", quickSort2, copyArray);
		SortHelper.sortTestHelper("quickSort3Ways", quickSort3Ways, copyArray2);
		SortHelper.sortTestHelper("heapSort", heapSort, copyArray3);
		SortHelper.sortTestHelper("heapSort2", heapSort2, copyArray4);
		SortHelper.sortTestHelper("heapSort3", heapSort3, copyArray5);
	}
}
