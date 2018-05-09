package unionFind;

/**
 * 并查集:
 * 	对于一组数据，主要支持两个动作：
 * 		union(p, q)
 * 		find(p)
 * 用来回答一个问题：
 * 		isConnected(p , q)
 */
//我们的第三版Union-Find
public class UnionFind3 {
	
    // 我们的第三版Union-Find, 使用一个数组构建一棵指向父节点的树
    // parent[i]表示i元素所指向的父节点
	private int[] parent;
	private int[] size; // size[i]表示以i为根的集合中元素的个数
	private int count;
	
	public UnionFind3(int count){
		this.count = count;
		parent = new int[count];
		size = new int[count];
		// 初始时，集合中的元素两两之间互不连接
		for (int i = 0; i < count; i++) {
			// 每个索引对应的元素都指向自己的父亲
			// 当指向自己时，表示自己是根节点
			parent[i] = i; 
			size[i] = 1;
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
		
		// 根据两个元素所在树的元素个数不同判断合并方向
        // 将元素个数少的集合合并到元素个数多的集合上
		if (size[pRoot] < size[qRoot]) {
			parent[pRoot] = qRoot; // 将元素个数少的树的parent指向qRoot
			size[qRoot] += size[pRoot];
		}else {
			parent[qRoot] = pRoot;
			size[pRoot] += size[qRoot]; // 合并之后需要维护集合中元素的个数
		}
	}
}
