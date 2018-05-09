package sortingAlgorithm;

import java.util.Random;

public class SortHelper {
	// // SortTestHelper不允许产生任何实例
	private SortHelper(){}
	
	// 生成有n个 元素的随机数组，每个元素的范围为[rangeL, rangeR]
	public static int[] generateRandomArray(int n, int rangeL, int rangeR) {
		if(rangeR <= rangeL || n < 1){
			return null;
		}else {
			Random random = new Random();
			int[] arr = new int[n];
			for (int i = 0; i < n; i++) {
				// rand.nextInt(100)——这行代码将生成范围0~100 之间的随机数，有趣的是，取值可能为 0 ，但不可能为100
				// arr[i] = random.nextInt(rangeR) % (rangeR - rangeL + 1) + rangeL;
				arr[i] = random.nextInt(rangeR - rangeL + 1) + rangeL;
			}
			return arr;
		}
	}
	
	// 生成一个近乎有序的数组
    // 首先生成一个含有[0...n-1]的完全有序数组, 之后随机交换swapTimes对数据
    // swapTimes定义了数组的无序程度:
    // swapTimes == 0 时, 数组完全有序
    // swapTimes 越大, 数组越趋向于无序
	public static int[] generateNearlyOrderedArray(int n, int swapTimes){
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = i;
		}
		Random random = new Random();
		for (int i = 0; i < swapTimes; i++) {
			int index1 = random.nextInt(n);
			int index2 = random.nextInt(n);
			swap(arr, index1, index2);
		}
		return arr;
	}

	// 判断数组arr是否是从小到大排序的
	public static boolean isSorted(int[] arr) {
		for (int i = 0; i < arr.length - 1 ; i++) {
			if (arr[i] > arr[i+ 1]) {
				return false;
			}
		}
		return true;
	}
	
	public static void swap(int[] arr, int index1, int index2){
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
	
	public static int[] copyArray(int[] arr) {
		int[] copyArray = new int[arr.length];
		for (int i = 0; i < copyArray.length; i++) {
			copyArray[i] = arr[i];
		}
		return copyArray;
	}
	
	public static void sortTestHelper(String name, SortInterface sortAlgorithm, int[] arr) {
		long startTime = System.currentTimeMillis();
		sortAlgorithm.sort(arr);
		long endTime = System.currentTimeMillis();
		assert isSorted( arr ); // 断言
		System.out.println(name + "算法花费了" + (endTime - startTime) + "毫秒");
//		System.out.println(name + "排序后的结果为：" + Arrays.toString(arr));
	}
}
