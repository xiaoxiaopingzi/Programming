 package maxHeap;

/**
 * 使用最大堆来存储数据 
 * 最大堆的性质：使用完全二叉树存储数据
 * 	1、父节点的值一定大于子节点的值
 * 	2、除最后一层节点外，其他层节点必须是满二叉树
 * 	3、最后一层的节点必须都在左侧
 *	4、leftChild = 2*k, rightChild = 2*k + 1
 *  5、parent = k/2
 *  6、取出的元素只能是根元素——即data[1]
 */
//在堆的有关操作中，需要比较堆中元素的大小，所以Item需要extends Comparable
@SuppressWarnings({ "unchecked"})
public class MaxHeap<Item extends Comparable> {

	private Item[] data; 
	// count用于指定data数组当前最后一个数组元素的索引
	private int count; 
	private int capacity; //capacity表示堆的容量
	
	// 将n个元素逐个插入到一个空堆中，算法复杂度是O(nlogn)
	public MaxHeap(int capacity){
		// data数组从索引为1的位置开始存储数据，所以堆的大小应为capacity + 1
		this.data = (Item[]) new Comparable[capacity + 1];
		this.count = 0;
		this.capacity = capacity;
	}
	
	// 下面的heapify的过程，算法复杂度为O(n)
	public MaxHeap(Item[] arr){
		int n = arr.length;
		this.data = (Item[]) new Comparable[n + 1];
		this.capacity = n;
		// 将arr数组中的值存储到data数组中
		for (int i = 0; i < n; i++) {
			data[i+1] = arr[i]; 
		}
		// data数组存储了n个数据后count指针需要变化
		// 此时count指向了最后一个叶子节点
		count = n; 
		// 从最后一个叶子节点的父节点开始，执行shiftDown()操作，直到i等于1
		for (int i = count/2; i >= 1; i--) {
			// 从第一个非叶子节点开始进行shiftDown()操作，直到所有的非叶子节点都操作完
			shiftDown(i);
		}
	}
	
	// 返回这个堆的大小
	public int size(){
		return this.count;
	}
	
	public boolean isEmpty(){
		return this.count == 0;
	}
	// 向data数组中插入一个数据
	public void insert(Item number){
		// 防止数组越界
		assert count + 1 <= capacity;
		// 从索引为1的位置开始存储
		data[count+1] = number;
		count++; // count指向当前最后一个元素的索引
		shiftUp(count);
	}

	// 取出data数组中根的元素——最大的元素
	// 从最大堆中取出堆顶元素, 即堆中所存储的最大数据
	public Item extractMax(){
		assert(count > 0);
		Item max = data[1];
		swap(count, 1);
		count-- ;
		shiftDown(1); // 堆顶元素取出后，需要更新堆
		return max;
	}
	
	public Item[] getData() {
		return data;
	}

	// 对新插入的元素进行最大堆二叉树排序
	private void shiftUp(int k) {
		// k的取值最多到2，k==2就是比较data[1]和data[2]
		while( k > 1 && data[k/2].compareTo(data[k]) < 0 ){
			swap(k/2, k);
			k = k / 2;
		}
	}
	
	// 取出根元素后，需要更新data数组，使其符合最大堆的性质
	private void shiftDown(int k) {
		// 当data数组中索引为k的位置有左孩子时就需要比较
		while ( 2*k <= count) {
			// 判断应该选择左孩子还是右孩子
			int j = 2 * k; 
			if( j + 1 <= count && data[j+1].compareTo(data[j]) > 0){
				j += 1; // 当右孩子大于左孩子则j的索引就设为右孩子
			}
			if( data[k].compareTo(data[j]) > 0){
				break;
			}
			swap(k, j);
			k = j; // 更新k的索引位置
		}
	}

	// 交换data数组中索引为j和k的元素
	private void swap(int k, int j) {
		Item temp = data[k];
		data[k] = data[j];
		data[j] = temp;
	}
}
