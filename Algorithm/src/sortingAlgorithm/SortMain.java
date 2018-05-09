package sortingAlgorithm;

import java.util.Arrays;

import org.junit.Test;

public class SortMain {
	 
	@Test
	public void testTime() {
		int n = 10000;
		int[] arr = SortHelper.generateRandomArray(n, 0, n);
		int[] copyArray = SortHelper.copyArray(arr);
		SortInterface selectionSort = new SelectionSort();
		SortInterface insertSort = new InsertSort();
		
		System.out.println("Test for Random Array, size=" + n + ",random range [0, " + n + "]");
		SortHelper.sortTestHelper("selectionSort", selectionSort, arr);
		SortHelper.sortTestHelper("insertSort", insertSort, copyArray);
		
		System.out.println();
		
		// 对于十分有序的数组，优化后的插入排序的性能非常好
		System.out.println("Test for Nearly Ordered Array, size=" + n + ",random range [0, " + n + "]");
		arr = SortHelper.generateNearlyOrderedArray(n, 10);
		copyArray = SortHelper.copyArray(arr);
		SortHelper.sortTestHelper("selectionSort", selectionSort, arr);
		SortHelper.sortTestHelper("insertSort", insertSort, copyArray);
	}
	
	@Test
	public void testSelectionSort() {
		int n = 10000;
		int[] arr = SortHelper.generateRandomArray(n, 0, n);
		SortInterface selectionSort = new SelectionSort();
		SortHelper.sortTestHelper("selectionSort", selectionSort, arr);
	}

	@Test
	public void testInsertSort() {
		int n = 10000;
		int[] arr = SortHelper.generateRandomArray(n, 0, n);
		SortInterface insertSort = new InsertSort();
		SortHelper.sortTestHelper("insertSort", insertSort, arr);
	}
	
	// Merge Sort是我们学习的第一个O(nlogn)复杂度的算法
    // 可以在1秒之内轻松处理100万数量级的数据
    // 注意：不要轻易尝试使用SelectionSort, InsertionSort或者BubbleSort处理100万级的数据
    // 否则，你就见识了O(n^2)的算法和O(nlogn)算法的本质差异：）
	@Test
	public void testMergeSort(){
		int n = 20000;
		int[] arr = SortHelper.generateRandomArray(n, 0, n);
		int[] copyArray = SortHelper.copyArray(arr);
		SortInterface insertSort = new InsertSort();
		SortInterface mergeSort = new MergeSort();
		System.out.println("Test for Random Array, size=" + n + ",random range [0, " + n + "]");
		SortHelper.sortTestHelper("insertSort", insertSort, arr);
		SortHelper.sortTestHelper("mergeSort", mergeSort, copyArray);
		
		System.out.println();
		
		// 对于十分有序的数组，优化后的插入排序的性能非常好
		arr = SortHelper.generateNearlyOrderedArray(n, 10);
		copyArray = SortHelper.copyArray(arr);
		System.out.println("Test for Nearly Ordered Array, size=" + n + ",random range [0, " + n + "]");
		SortHelper.sortTestHelper("insertSort", insertSort, arr);
		SortHelper.sortTestHelper("mergeSort", mergeSort, copyArray);
	}
	
	@Test
	public void testMergeSortBU(){
		int n = 20000;
		int[] arr = SortHelper.generateRandomArray(n, 0, n);
		int[] copyArray = SortHelper.copyArray(arr);
		SortInterface mergeSortBU = new mergeSortBU();
		SortInterface mergeSort = new MergeSort();
		System.out.println("Test for Random Array, size=" + n + ",random range [0, " + n + "]");
		SortHelper.sortTestHelper("mergeSortBU", mergeSortBU, arr);
		SortHelper.sortTestHelper("mergeSort", mergeSort, copyArray);
	}
	
	/**
	 * 快速排序是一个o(nlogn)复杂度的算法
	 * 可以在1秒之内轻松处理100万数量级的数据
	 */
	@Test
	public void testQuickSort(){
		int n = 1000000;
		int[] arr = SortHelper.generateRandomArray(n, 0, n);
		int[] copyArray = Arrays.copyOf(arr, arr.length);
		SortInterface quickSort = new QuickSort();
		SortInterface mergeSort = new MergeSort();
		System.out.println("Test for Random Array, size=" + n + ",random range [0, " + n + "]");
		SortHelper.sortTestHelper("quickSort", quickSort, arr);
		SortHelper.sortTestHelper("mergeSort", mergeSort, copyArray);
		
		System.out.println();
		
		// 对于十分有序的数组，未优化的快速排序性能很差，会退化为o(n*n)级别的算法
		System.out.println("Test for Nearly Ordered Array, size=" + n + ",random range [0, " + n + "]");
		arr = SortHelper.generateNearlyOrderedArray(n, 100);
		copyArray = Arrays.copyOf(arr, arr.length);
		SortHelper.sortTestHelper("mergeSort", mergeSort, copyArray);
		SortHelper.sortTestHelper("quickSort", quickSort, arr);
	}
	
	/**
	 * 测试经过优化后的快速排序算法，优化后的快速排序算法对于重复性很高的
	 * 数组的排序性能依然很好，未优化的算法对于数组长度大于70000左右就会
	 * 直接抛出堆栈溢出错误，完全无法处理大的数组，并且处理时间也很长，这是
	 * 因为在这种情况下快速排序退化为了o(n*n)级别的算法
	 */
	@Test
	public void testQuickSort2(){
		int n = 40000;
		int[] arr = SortHelper.generateRandomArray(n, 0, 10);
		int[] copyArray = Arrays.copyOf(arr, arr.length);
		int[] copyArray2 = Arrays.copyOf(arr, arr.length);
		SortInterface quickSort = new QuickSort();
		SortInterface mergeSort = new MergeSort();
		SortInterface quickSort2 = new QuickSort2();
		System.out.println("Test for Random Array, size=" + n + ",random range [0, " + n + "]");
		SortHelper.sortTestHelper("mergeSort", mergeSort, arr);
		SortHelper.sortTestHelper("quickSort2", quickSort2, copyArray);
		SortHelper.sortTestHelper("quickSort", quickSort, copyArray2);
	}
	
	@Test
	public void testQuickSort3Ways(){
		int n = 100000;
		int[] arr = SortHelper.generateRandomArray(n, 0, n);
		int[] copyArray = Arrays.copyOf(arr, arr.length);
		int[] copyArray2 = Arrays.copyOf(arr, arr.length);
		SortInterface mergeSort = new MergeSort();
		SortInterface quickSort2 = new QuickSort2();
		SortInterface quickSort3Ways = new QuickSort3Ways();
		System.out.println("Test for Random Array, size=" + n + ",random range [0, " + n + "]");
		SortHelper.sortTestHelper("mergeSort", mergeSort, arr);
		SortHelper.sortTestHelper("quickSort2", quickSort2, copyArray);
		SortHelper.sortTestHelper("quickSort3Ways", quickSort3Ways, copyArray2);
		
		System.out.println();
		
		int swapTimes = 100;
		System.out.println("Test for Nearly Ordered Array, size=" + n + ", swapTimes=" + swapTimes);
		arr = SortHelper.generateNearlyOrderedArray(n, swapTimes);
		copyArray = Arrays.copyOf(arr, arr.length);
		copyArray2 = Arrays.copyOf(arr, arr.length);
		SortHelper.sortTestHelper("mergeSort", mergeSort, arr);
		SortHelper.sortTestHelper("quickSort2", quickSort2, copyArray);
		SortHelper.sortTestHelper("quickSort3Ways", quickSort3Ways, copyArray2);
		
		System.out.println();
		
		System.out.println("Test for a lot of repetition Array, size=" + n + ",random range [0,10]");
		arr = SortHelper.generateRandomArray(n, 0, 10);
		copyArray = Arrays.copyOf(arr, arr.length);
		copyArray2 = Arrays.copyOf(arr, arr.length);
		SortHelper.sortTestHelper("mergeSort", mergeSort, arr);
		SortHelper.sortTestHelper("quickSort2", quickSort2, copyArray);
		SortHelper.sortTestHelper("quickSort3Ways", quickSort3Ways, copyArray2);
	}
}
