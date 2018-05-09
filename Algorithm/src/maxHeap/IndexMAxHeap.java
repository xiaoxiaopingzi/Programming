package maxHeap;

public class IndexMAxHeap {

	// data数组用于存储数据
	private int[] data; 
	// indexes数组用于存储索引
	private int[] indexes;
	// reverse数组用于存储反向索引
	private int[] reverse;
	private int count;  // count指向indexes数组中最后一个元素
	private int capacity; //capacity表示堆的容量
	
	public IndexMAxHeap(int capacity){
		// data数组从索引为1的位置开始存储数据，所以堆的大小应为capacity + 1
		this.data =  new int[capacity + 1];
		this.indexes =  new int[capacity + 1];
		this.reverse =  new int[capacity + 1];
		for (int i = 0; i <= capacity; i++) {
			reverse[i] = 0;  // 0表示reverse[i]在indexes中不存在
		}
		this.count = 0;
		this.capacity = capacity;
	}
	
	public int size(){
		return this.count;
	}
	
	public boolean isEmpty(){
		return this.count == 0;
	}
	
	// 在索引为i的位置插入元素number，传入的i对用户而言，是从0开始索引的
	public void insert(int i, int number){
		assert count + 1 <= capacity;
		assert i + 1 >= 1 && i + 1 <= capacity;
		i = i + 1; // 传入的i对用户而言，是从0开始索引的，真实的数据是从索引1开始的
		data[i] = number; // 在data数组的索引i位置插入元素number
		indexes[count + 1] = i; // 在indexes数组的末尾后面添加一个索引
		reverse[i] = count + 1;
		
		count++; // count指向indexes数组中的最后一个元素的索引
		shiftUp(count); // 对indexes数组进行最大堆排序
	}

	public int extractMax(){
		assert(count > 0);
		int max = data[indexes[1]];
		swap(count, 1);
		reverse[indexes[1]] = 1;
		// count位置的元素需要删除，所以直接指向0
		reverse[indexes[count]] = 0; 
		count-- ;
		shiftDown(1); 
		return max;
	}
	
	// 返回索引堆中最大值的索引
	public int extractMaxIndex(){
		assert(count > 0);
		// 由于用户看来索引是从0开始的，所以这里需要将得到的额最大值的索引减1
		int max = indexes[1] - 1;
		swap(count, 1);
		reverse[indexes[1]] = 1;
		// count位置的元素需要删除，所以直接指向0
		reverse[indexes[count]] = 0; 
		count-- ;
		shiftDown(1); 
		return max;
	}
	
	int getDataByIndex(int i){
		assert( contain(i) );
		return data[i+1];
	}
	
	/**
	 * 判断索引为i的元素是否在堆中
	 * @param i——用户传递过来的i，是从0开始的索引
	 * @return
	 */
	private boolean contain(int i) {
		assert(1<= i + 1 && i + 1 <= capacity);
		// 只需要判断reverse[i+1]不等于0，就表示索引i在indexes数组中
		return reverse[i+1] != 0;
	}

	/**
	 * 将data数组中索引为i的元素设为number
	 * @param i
	 * @param number
	 */
	public void change(int i , int number){
		assert( contain(i) );
		i = i + 1;
		data[i] = number;
		
		// 找到indexes[j] = i,j表示data[i]在堆中的位置
		// 之后shiftUp(j), 再shiftUp(j)
//		for (int j = 0; j < count; j++) {
//			if (indexes[j] == i) {
//				shiftUp(j);
//				shiftDown(j);
//				return;
//			}
//		}
		// 使用反向查找的技术，降低程序的时间复杂度
		int j = reverse[i]; 
		shiftDown(j);
		shiftUp(j);
	}
	
	private void shiftUp(int k) {
		// k的取值最多到2，k==2就是比较data[1]和data[2]
		while( k > 1 && data[indexes[k/2]] < data[indexes[k]] ){
			swap(k/2, k);
			// indexes数组交换索引k/2和k的元素后，reverse元素也需要维护
			reverse[indexes[k/2]] = k/2;
			reverse[indexes[k]] = k;
			k = k / 2;
		}
	}
	
	private void shiftDown(int k) {
		while ( 2*k <= count) {
			int j = 2 * k; 
			if( j + 1 <= count && data[indexes[j+1]] > data[indexes[j]]){
				j += 1; // 当右孩子大于左孩子则j的索引就设为右孩子
			}
			if( data[indexes[k]] > data[indexes[j]]){
				break;
			}
			swap(k, j);
			reverse[indexes[k]] = k;
			reverse[indexes[j]] = j;
			k = j; // 更新k的索引位置
		}
	}

	// 交换indexes数组的位置
	private void swap(int k, int j) {
		int temp = indexes[k];
		indexes[k] = indexes[j];
		indexes[j] = temp;
	}
}
