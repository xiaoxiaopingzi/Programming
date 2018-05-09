package MinimumSpanTree;

import java.util.ArrayList;
import java.util.List;

// 使用Lazy Prim算法得到最小生成树(MST)
public class LazyPrimMST {

	private WeightGraph graph;  // 图的引用
	private MinHeap<Edge<Double>> pq;   // 最小堆, 算法辅助数据结构
	private boolean[] marked;  // 标记数组, 在算法运行过程中标记节点i是否被访问
	private List<Edge<Double>> mst; // 最小生成树所包含的所有边
	private double mstWeight; // 最小生成树所有边的权值之和
	
	public LazyPrimMST(WeightGraph graph){
		// 算法初始化
		this.graph = graph;
		pq = new MinHeap<Edge<Double>>(graph.E());
		marked = new boolean[graph.V()];
		mst = new ArrayList<Edge<Double>>();
		
		// Lazy Prim
		visit(0);
		while ( !pq.isEmpty() ) {
			Edge<Double> edge = pq.extractMin();
			// 如果pq堆中的权值最小的边的两个端点是同一边的，就说明不是横切边，则直接开始下一次循环
			if (marked[edge.v()] == marked[edge.w()]) {
				continue;
			}
			mst.add(edge);
			// 接下来需要寻找的端口就是v或者w，看哪个节点还未访问
			if ( !marked[edge.v()] ) {
				visit(edge.v());
			}else {
				visit(edge.w());
			}
		}
		
		for (int i = 0; i < mst.size(); i++) {
			mstWeight += mst.get(i).wt();
		}
	}
	
	private void visit(int i) {
		assert ( !marked[i] );
		marked[i] = true;
		for ( Edge<Double> edge : graph.adj(i) ) {
			// 如果节点i的邻居节点以前没有被访问过，就执行下面的代码
			if ( !marked[edge.other(i)] ) {
				pq.insert(edge);
			}
		}
	}

	// 返回最小生成树的权值
	public double result(){
		return mstWeight;
	}
	
	// 返回最小生成树的所有边
	public List<Edge<Double>> mstEdges(){
		return mst;
	}
}
