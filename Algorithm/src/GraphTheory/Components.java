package GraphTheory;

//求无权图的联通分量的个数
public class Components {
	private Graph graph;        // 图的引用
    private boolean[] visited;  // 记录dfs的过程中节点是否被访问
    // 记录连通分量(连通图)个数，联通分量之间没有任何边连接
    private int ccount;    
    private int[] id; // id用来表示每个节点所在的连通分量
	
	public Components(Graph graph){
		this.graph = graph;
		// 使用graph.V()获取图中节点的个数
		// false为boolean型变量的默认值
		this.visited = new boolean[graph.V()];
		this.ccount = 0;
		this.id = new int[graph.V()];
		
		for (int i = 0; i < graph.V(); i++) {
			visited[i] = false;
			id[i] = -1;  // 初始值全为-1
		}
		
		for (int i = 0; i < graph.V(); i++) {
			if (!visited[i]) {
				dfs(i);
				// 执行了一次深度优先遍历后，同一个连通图中的节点一定已经全部遍历了
				// 没遍历的节点一定是在另外的连通图中
				ccount ++; 
			}
		}
	}

	// 图的深度优先遍历
	// 复杂度为：稀疏图(邻接表) ： o(V + E)
	// 每个节点都需要访问一次，同时需要访问每个节点的邻接节点
	// 稠密图(邻接矩阵) ： o(V^2)
	// 每个节点都需要访问一次，每个节点都需要遍历以获取改节点的所有邻接节点
	private void dfs(int v) {
		visited[v] = true;
//		Iterator<Integer>  adjIterator = graph.adj(v).iterator();
//		if (adjIterator.hasNext()) {
//			int temp = adjIterator.next();
//			// 如果temp节点没有被访问过，则使用递归继续执行dfs
//			if (!visited[temp]) {
//				dfs(temp);
//			}
//		}
// 同一个连通分量的ccount值是相同的，所以可以用ccount来表示节点所在的连通分量
		id[v] = ccount;
		
		for(int i : graph.adj(v)){
			if (!visited[i]) {
				dfs(i);
			}
		}
	}
	
	public int count(){ 
		return this.ccount;
	}
	
	// 判断节点v和节点w是否相连——并查集
	public boolean isConnection(int v, int w){
		assert( v >= 0 && v < graph.V());
		assert( w >= 0 && w < graph.V());
		return id[v] == id[w];
	}
}
