package shortestPath;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 使用 dijkstra 算法求单源最短路径问题
 * 		前提：图中不能有负权边
 * 		复杂度：o(ElogV)
 * @author Administrator
 * @date   2017-9-4
 */
public class Dijkstra {

	private WeightGraph graph;
	private int s;  // 起始点
	private double[] distTo;  // distTo[i]存储从起始点s到i的最短路径长度
	private boolean[] marked;  // 标记数组, 在算法运行过程中标记节点i是否被访问
	
	// from[i]记录最短路径中, 到达i点的边是哪一条, 可以用来恢复整个最短路径
	private Edge<Double>[] from; 
	
	@SuppressWarnings("unchecked")
	public Dijkstra(WeightGraph graph, int s){
		this.s = s;
		this.graph = graph;
		distTo = new double[graph.V()];
		from = new Edge[graph.V()];
		marked = new boolean[graph.V()];
		
		for( int i = 0 ; i < graph.V() ; i ++ ){
            distTo[i] = 0.0;
            marked[i] = false;
            from[i] = null;
        }
		
		// 使用索引堆记录当前找到的到达每个顶点的最短距离
		IndexMinHeap<Double> ipq = new IndexMinHeap<Double>(graph.V());
		
		// Dijkstra
		distTo[s] = 0.0;
		from[s] = new Edge<Double>(s, s, 0.0);
		marked[s] = true;
		ipq.insert(s, distTo[s]);
		
		// 松弛操作
		// distTo[v]是s到v的最短距离
		while (!ipq.isEmpty()) {
			int v = ipq.extractMinIndex(); // 取出索引堆中的最小值
			marked[v] = true;  // 将节点v标志为已经被访问过
			 // 对v的所有相邻节点进行更新
			for (Edge edge : graph.adj(v)) {
				int w = edge.other(v); // 找到端点
				// 如果从s点到w点的最短路径还没有找到
				if (!marked[w]) {
					// 如果w点以前没有访问过,
                    // 或者访问过, 但是通过当前的v点到w点距离更短, 则进行更新
					if (from[w] == null || (distTo[v] + (Double)edge.wt()) < distTo[w]) {
						// 更新操作，需要更新distTo[w]、from[w]以及索引堆
						distTo[w] = distTo[v] + (Double)edge.wt();
						from[w] = edge;
						if (ipq.contain(w)) {
							ipq.change(w, distTo[w]);
						}else {
							ipq.insert(w, distTo[w]);
						}
					}
				}
			}
		}
	}
	
	public double shortestPathTo(int w){
		return distTo[w];
	}
	
	public boolean hasPathTo(int w){
		return marked[w];
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
