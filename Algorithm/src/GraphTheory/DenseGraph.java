package GraphTheory;

import java.util.ArrayList;
import java.util.List;

// 稠密图 —— 使用邻接矩阵
public class DenseGraph implements Graph{
    private int n;  // 节点数
    private int m;  // 边数
    private boolean directed;   // 是否为有向图
    private boolean[][] g;      // 图的具体数据
    
	public DenseGraph( int n, boolean directed){
		this.n = n;
		this.m = 0;  // 初始化没有任何边
		this.directed = directed;
		// g初始化为n*n的布尔矩阵, 每一个g[i][j]均为false, 表示没有任和边
        // false为boolean型变量的默认值
		this.g = new boolean[n][n];
	}
	
	// 返回节点个数
	public int V(){
		return n;
	}
	
	// 返回边的个数
	public int E(){
		return m;
	}
	
	// 向图中添加一个边
	public void addEdge(int v, int w){
		assert(v >= 0 && v < n);
		assert(w >= 0 && w < n);
		
		if (!hasEdge(v, w)) {
			g[v][w] = true;
			if (!directed) { // 如果是无向图，则需要将g[w][v]也置为true
				g[w][v] = true;
			}
			m ++;  // 添加一条边后边数m需要加1
		}
	}
	
	// 验证图中是否有从v到w的边
	public boolean hasEdge(int v, int w){
		assert(v >= 0 && v < n);
		assert(w >= 0 && w < n);
		return g[v][w];
	}
	
	// 返回图中一个顶点的所有邻边
    // 由于java使用引用机制，返回一个List不会带来额外开销
    public Iterable<Integer> adj(int v) {
        assert v >= 0 && v < n;
        // 集合都是可迭代对象
        List<Integer> adjV = new ArrayList<Integer>();
        for(int i = 0 ; i < n ; i ++ )
            if( g[v][i] ){
            	adjV.add(i);
            }
        return adjV;
    }

    // 显示图的信息 —— 将邻接矩阵显示出来
    public void show(){
        for( int i = 0 ; i < n ; i ++ ){
            for( int j = 0 ; j < n ; j ++ ){
            	System.out.print(g[i][j]+"\t");
            }
            System.out.println();
        }
    }
}
