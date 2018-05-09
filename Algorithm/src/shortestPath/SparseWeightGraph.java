package shortestPath;

import java.util.ArrayList;
import java.util.List;

// 稀疏图 —— 邻接表
public class SparseWeightGraph implements WeightGraph{
    private int n;  // 节点数
    private int m;  // 边数
    private boolean directed;   // 是否为有向图
    private List<Edge<Double>>[] g;
    
    @SuppressWarnings("unchecked")
	public SparseWeightGraph(int n, boolean directed){
    	this.n = n;
    	this.m = 0;
    	this.directed = directed;
    	g = new ArrayList[n];
    	for (int i = 0; i < n; i++) {
			g[i] = new ArrayList<Edge<Double>>();
		}
    }
	// 返回节点个数
	public int V(){
		return n;
	}
	
	// 返回边的个数
	public int E(){
		return m;
	}
	
	// 由于判断是否有边的函数的时间复杂度为o(n)，所以在邻接表中不判断是否已经有边
	// 所以可能出现平行边
	public void addEdge(int v, int w, double weight){
		assert(v >= 0 && v < n);
		assert(w >= 0 && w < n);
		
		g[v].add(new Edge<Double>(v, w, weight));
		if (v != w && !directed) {
			// v != w是为了防止自环边的出现，当v == w时，g[v]==g[w]
			g[w].add(new Edge<Double>(w, v, weight));
		}
		m ++;
	}
	
	// 判断节点v是否有到节点w的边，时间复杂度为o(n)
	public boolean hasEdge(int v, int w){
		assert(v >= 0 && v < n);
		assert(w >= 0 && w < n);
		
		for (int i = 0; i < g[v].size(); i++) {
			if (g[v].get(i).other(v) == w) {
				return true;
			}
		}
		return false;
	}
	
	// 返回图中一个顶点的所有邻边
    // 由于java使用引用机制，返回一个Vector不会带来额外开销,
    public Iterable<Edge<Double>> adj(int v) {
        assert(v >= 0 && v < n);
        // 集合都是可迭代对象
        return g[v];
    }
    
    // 显示图的信息  —— 将邻接表显示出来
	public void show() {
		for (int i = 0; i < g.length; i++) {
			System.out.print("节点" + i + "的邻接节点为：");
			for (int j = 0; j < g[i].size(); j++) {
				Edge<Double> e = g[i].get(j);
				System.out.print("( to:" + e.other(i) + ",wt:" + e.wt() + ")\t");
			}
			System.out.println();
		}
	}
}
