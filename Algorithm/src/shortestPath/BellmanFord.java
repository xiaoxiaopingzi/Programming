package shortestPath;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Bellman-Ford 单源最短路径算法
 * 		可以处理负权边
 * 		前提：图中不能有负权环，实际上有了负权环，就不存在最短路径了
 * 		复杂度:o(EV)
 * @author Administrator
 * @date   2017-9-4
 */
public class BellmanFord {

	private WeightGraph graph;
	private int s;  // 起始点
	private double[] distTo;  // distTo[i]存储从起始点s到i的最短路径长度
	
	// from[i]记录最短路径中,到达i点的边是哪一条,可以用来恢复整个最短路径
	private Edge<Double>[] from;       
	private boolean hasNegativeCycle;   // 标记图中是否有负权环
	
	@SuppressWarnings("unchecked")
	public BellmanFord(WeightGraph graph, int s) {
		this.s = s;
		this.graph = graph;
		distTo = new double[graph.V()];
		from = new Edge[graph.V()];
		
		
		for( int i = 0 ; i < graph.V() ; i ++ ){
            from[i] = null;
        }
		
		// Bellman-Ford
		// 设置distTo[s] = 0, 并且让from[s]不为NULL, 表示初始s节点可达且距离为0
		distTo[s] = 0.0;
		from[s] = new Edge<Double>(s, s, 0.0);
		// 每次循环中对所有的边进行一遍松弛操作
        for (int pass = 1 ; pass < graph.V() ; pass ++ ) {
        	// 遍历所有边的方式是先遍历所有的顶点, 然后遍历和所有顶点相邻的所有边
        	// 进行V-1次循环, 每一次循环求出从起点到其余所有点, 最多使用pass步可到达的最短距离
        	for (int i = 0; i < graph.V(); i++) {
        		for (Edge edge : graph.adj(i)) {
        			// 对于每一个边首先判断e->v()可达
                    // 之后看如果e->w()以前没有到达过， 显然我们可以更新distTo[e->w()]
                    // 或者e->w()以前虽然到达过, 但是通过这个e我们可以获得一个更短的距离, 即可以进行一次松弛操作, 我们也可以更新distTo[e->w()]
        			if (from[edge.w()] == null || distTo[edge.v()] + (Double)edge.wt() < distTo[edge.w()]) {
						distTo[edge.w()] = distTo[edge.v()] + (Double)edge.wt();
						from[edge.w()] = edge;
					}
        		}
        	}
		}
        
        hasNegativeCycle = detectNegativeCycle();
	}
	
	// 经过了Bellman-Ford之后，如果还有边可以进行松弛操作，则说明中有负权环
	// 如果没有边可以进行松弛操作，则说明中没有负权环
	@SuppressWarnings("unchecked")
	private boolean detectNegativeCycle() {
        	for (int i = 0; i < graph.V(); i++) {
        		for (Edge edge : graph.adj(i)) {
        			if (from[edge.w()] == null || distTo[edge.v()] + (Double)edge.wt() < distTo[edge.w()]) {
        				return true;
					}
        		}
        	}
		return false;
	}

	
	 // 返回图中是否有负权环
    boolean negativeCycle(){
        return hasNegativeCycle;
    }

	public double shortestPathTo(int w){
		assert w >= 0 && w < graph.V();
        assert !hasNegativeCycle;
        assert hasPathTo(w);
        return distTo[w];
	}
	
	public boolean hasPathTo(int w){
		 assert( w >= 0 && w < graph.V() );
	     return from[w] != null;
	}
	
	// 查询从s点到w点的路径, 存放在List中
	public List<Edge<Double>> shortestPath(int w) {
		if (hasPathTo(w)) {
			Stack<Edge<Double>> s = new Stack<Edge<Double>>();
			Edge<Double> e = from[w];
			while (e.v() != this.s) {
				s.push(e);
				e = from[e.v()];
			}
			s.push(e);
			List<Edge<Double>> path = new ArrayList<Edge<Double>>();
			while (!s.empty()) {
				path.add(s.pop());
			}
			return path;
		} else {
			System.out.println("节点" + this.s + "没有到节点" + w + "的路径！");
			return null;
		}
	}
	
	// 打印出从s点到w点的路径
    void showPath(int w){
        assert w >= 0 && w < graph.V();
        assert hasPathTo(w);
        List<Edge<Double>> path =  shortestPath(w);
        for( int i = 0 ; i < path.size() ; i ++ ){
            System.out.print( path.get(i).v() + " -> ");
            if( i == path.size()-1 )
                System.out.println(path.get(i).w());
        }
    }
}
