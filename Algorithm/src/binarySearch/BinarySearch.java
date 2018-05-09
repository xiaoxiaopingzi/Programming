package binarySearch;

/**
 * 二分查找法，在有序数组arr中，查找target
 * 如果找到target，返回对应位置的索引index
 * 如果没有找到target，返回-1
 * @author Administrator
 */
public class BinarySearch {

	public static int binarySearch(int[] arr, int target) {
		
		// 在arr[L...r]之中查找target
		int L = 0, r = arr.length - 1; 
		while (L <= r) {
//			int mid = (L + r) / 2; // 这样写，可能会造成溢出
			int mid = L + (r - L)/2;
			if (arr[mid] == target) {
				return mid;
			}else if (arr[mid] < target) {
				// 在arr[mid+1...r]中查找target，因为arr[mid]!=target
				L = mid + 1 ;
			}else {
				// 在arr[L...mid-1]中查找target
				r = mid - 1;
			}
		}
		return -1;
	}
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7,8,9,45};
		int index = binarySearch(arr, 8);
		System.out.println("arr[" +index +"]=" +arr[index]);
		
	}
}
