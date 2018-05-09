package unionFind;

/**
 * 并查集:
 * 	对于一组数据，主要支持两个动作：
 * 		union(p, q)
 * 		find(p)
 * 用来回答一个问题：
 * 		isConnected(p , q)
 */
public class UnionFind2 {
	
    // 我们的第二版Union-Find, 使用一个数组构建一棵指向父节点的树
    // parent[i]表示第一个元素所指向的父节点
	private int[] parent;
	private int count;
	
	public UnionFind2(int count){
		this.count = count;
		parent = new int[count];
		// 初始时，集合中的元素两两之间互不连接
		for (int i = 0; i < count; i++) {
			// 每个索引对应的元素都指向自己的父亲
			// 当指向自己时，表示自己是根节点
			parent[i] = i; 
		}
	}
	
	// 查找过程, 查找元素p所对应的集合编号，根节点所对应的编号就是整个集合的编号
	public int find(int p){
		assert(p >= 0 && p < count);
		// 根节点所对应的编号就是整个集合的编号
		while (p != parent[p]) {
			p = parent[p]; // parent[p]指向p的父亲所在的索引
		}
		return p;
	}
	
	// 检查是否连接
	public boolean isConnected(int p, int q){
		return find(p) == find(q);
	}
	
	// 合并
	public void union(int p, int q){
		int pRoot = find(p);
		int qRoot = find(q);
		
		if (pRoot == qRoot) {
			return;
		}
		parent[pRoot] = qRoot;
	}
}
