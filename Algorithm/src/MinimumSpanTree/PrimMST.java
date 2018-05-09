package MinimumSpanTree;

import java.util.ArrayList;
import java.util.List;

// 优化过后的Prime，使用索引堆进行优化
public class PrimMST {
	private WeightGraph graph;
	private IndexMinHeap<Double> ipq; // 索引堆
	private Edge<Double>[] edgeTo; // edgeTo存放和这个节点相邻的最小横切边
	private boolean[] marked;  // 标记数组, 在算法运行过程中标记节点i是否被访问
	private List<Edge<Double>> mst; // 最小生成树所包含的所有边
	private double mstWeight; // 最小生成树所有边的权值之和
	
	@SuppressWarnings("unchecked")
	public PrimMST(WeightGraph graph){
		// 算法初始化
		this.graph = graph;
		ipq = new IndexMinHeap<Double>(graph.V());
		marked = new boolean[graph.V()];
		edgeTo = new Edge[graph.V()];
		mst = new ArrayList<Edge<Double>>();
		for( int i = 0 ; i < graph.V() ; i ++ ){
            marked[i] = false;
            edgeTo[i] = null;
        }
		
		// Prim
		visit(0);
		while ( !ipq.isEmpty() ) {
			// 使用最小索引堆找出已经访问的边中权值最小的边
            // 最小索引堆中存储的是顶点的索引, 通过顶点的索引找到相对应的边
            int v = ipq.extractMinIndex();
            assert( edgeTo[v] != null );
            mst.add( edgeTo[v] );
            visit( v );
		}
		
		for (int i = 0; i < mst.size(); i++) {
			mstWeight += mst.get(i).wt();
		}
	}

	private void visit(int i) {
		assert ( !marked[i] );
		marked[i] = true;
		// 访问顶点V的所有邻边
		for ( Edge<Double> edge : graph.adj(i) ) {
			int w = edge.other(i);
			// 如果边的另一端点未被访问
			if ( !marked[w] ) {
				 // 如果从没有考虑过这个端点, 直接将这个端点和与之相连接的边加入索引堆
				if( edgeTo[w] == null ){
                    edgeTo[w] = edge;
                    ipq.insert(w, edge.wt());
                }else if (edge.wt() < edgeTo[w].wt()) {
                	// 如果曾经考虑这个端点, 但现在的边比之前考虑的边更短, 则进行替换
                	edgeTo[w] = edge;
                	ipq.change(w, edge.wt());
				}
			}
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
