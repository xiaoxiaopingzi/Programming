package MinimumSpanTree;

import java.util.ArrayList;
import java.util.List;

public class KruskalMST {

	private List<Edge<Double>> mst;  // 最小生成树所包含的所有边
	private double mstWeight;   // 最小生成树的权值
	
	// 构造函数, 使用Kruskal算法计算graph的最小生成树
	@SuppressWarnings("unchecked")
	public KruskalMST(WeightGraph graph){
		mst = new ArrayList<Edge<Double>>();
		
		 // 将图中的所有边存放到一个最小堆中
		MinHeap<Edge<Double>> pq = new MinHeap<Edge<Double>>(graph.E());
		for (int i = 0; i < graph.V(); i++) {
			for(Edge edge : graph.adj(i)){
				// 对于无向图，顶点1和顶点2只放一条边
				if (edge.v() <= edge.w()) {
					pq.insert(edge);
				}
			}
		}
		
		UnionFind unionFind = new UnionFind(graph.V());
		while (!pq.isEmpty() && mst.size() < graph.V() - 1) {
			Edge e = pq.extractMin();
			// 使用并查集来判断边e两端的节点是否相连
			if (unionFind.isConnected(e.v(), e.w())) {
				// 如果相连，则说明这条边不能作为最小生成树的边——因为会产生环
				continue;
			}else {
				// 如果不相连，则说明这条边就是最小生成树的边
				mst.add(e); // 将这条边加入到最小生成树中
				unionFind.union(e.v(), e.w()); // 维护并查集
			}
		}
		
		for (int i = 0; i < mst.size(); i++) {
			mstWeight += mst.get(i).wt();
		}
	}
	
	
	 // 返回最小生成树的所有边
    List<Edge<Double>> mstEdges(){
        return mst;
    }

    // 返回最小生成树的权值
    Number result(){
        return mstWeight;
    }
	
}
