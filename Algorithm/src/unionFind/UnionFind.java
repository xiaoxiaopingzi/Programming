package unionFind;

/**
 * 并查集:
 * 	对于一组数据，主要支持两个动作：
 * 		union(p, q)
 * 		find(p)
 * 用来回答一个问题：
 * 		isConnected(p , q)
 */
public class UnionFind {
	private int[] id;
	private int count;
	
	public UnionFind(int count){
		this.count = count;
		id = new int[count];
		// 初始化, 每一个id[i]指向自己
		// 表示初始时，每个元素自身组成一个集合
		for (int i = 0; i < count; i++) {
			// id数组中保存的值就是对应索引所在的集合
			id[i] = i;
		}
	}
	
	// 查找过程, 查找元素p所对应的集合编号,id[p]就表示元素p所在的集合
	public int find(int p){
		assert(p >= 0 && p < count);
		// 由于id数组保存的值就是对应位置的索引所在的集合，
		// 所以id[p]就是p所在的集合
		return id[p];
	}
	
	// 查看元素p和元素q是否所属一个集合
    // O(1)复杂度
	public boolean isConnected(int p, int q){
		return find(p) == find(q);
	}
	
	// 合并元素p和元素q所属的集合
    // O(n) 复杂度
	public void union(int p, int q){
		int pID = find(p);
		int qID = find(q);
		
		if (pID == qID){
			return;
		}
		
		// 合并过程需要遍历一遍所有元素, 将两个元素的所属集合编号合并
		for (int i = 0; i < count; i++) {
			// pID集合可能有多个元素
			// 需要将pID集合中的所有元素与qID集合合并
			if (id[i] == pID) {
				id[i] = qID;
			}
		}
	}
}
