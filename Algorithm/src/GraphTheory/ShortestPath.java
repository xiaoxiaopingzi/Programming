package GraphTheory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class ShortestPath {

	private Graph graph;
	private int source;
	private boolean[] visited; // visited数组用来表示节点是否已经加入到队列中
	private int[] from;
	private int[] ord; // ord数组记录从source节点到每一个节点的最短距离
	
	public ShortestPath(Graph graph, int source){
		assert( source >= 0 && source < graph.V());
		this.graph = graph;
		this.source = source;
		this.visited = new boolean[graph.V()];
		this.from = new int[graph.V()];
		this.ord = new int[graph.V()];
		for (int i = 0; i < graph.V(); i++) {
			visited[i] = false;
			from[i] = -1;
			ord[i] = -1;
		}
		
		bfs(graph, source);
	}

	// 无向图最短路径算法  —— 广度优先遍历
	// 广度优先遍历也叫广度优先搜索(Breadth First Search)
	// 复杂度为：稀疏图(邻接表) ： o(V + E)
	// 每个节点都需要访问一次，同时需要访问每个节点的邻接节点
	// 稠密图(邻接矩阵) ： o(V^2)
	// 每个节点都需要访问一次，每个节点都需要遍历以获取改节点的所有邻接节点
	private void bfs(Graph graph, int source) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(source);
		visited[source] = true;
		ord[source] = 0;
		while (!queue.isEmpty()) {
			int v = queue.poll(); // 取出队首元素
			for (Integer i : graph.adj(v)) {
				if (!visited[i]) {  // 如果没有加入到队列中，就往下执行
					// 将节点加入到队列中，并维护其他信息
					queue.offer(i);
					visited[i] = true;
					from[i] = v;
					ord[i] = ord[v] + 1;
				}
			}
		}
	}
	
	// 从节点source到节点w是否有路
	public boolean hasPath(int w){
		assert( w >= 0 && w < graph.V());
		return visited[w];
	}
	
	// 查询从s点到w点的路径, 存放在List中
	public List<Integer> getPath(int w){
		if (hasPath(w)) {
			 // 堆栈，先进后出
			 Stack<Integer> s = new Stack<Integer>();
			 int p = w;
			 // 由于源节点的from[s]=-1，所以使用p==-1来作为循环结束条件
			 while (p != -1) {
				s.push(p);
				p = from[p];
			}
			 List<Integer> path = new ArrayList<Integer>();
			 while (!s.empty()) {
				path.add(s.pop());
			}
			 return path;
		}else {
			System.out.println("节点" + this.source + "没有到节点" + w + "的路径！" );
			return null;
		}
	}
	
	public void showPath(int w){
		assert hasPath(w) ;
		List<Integer> path = getPath(w);
		System.out.print("BFS:");
		for( int i = 0 ; i < path.size() ; i ++ ){
            System.out.print(path.get(i));
            if( i == path.size() - 1 )
                System.out.println();
            else
                System.out.print(" -> ");
        }
	}
	
	// 返回从source节点到w节点的最短路径的长度
	public int length(int w){
		assert( w >= 0 && w < graph.V());
		return ord[w];
	}
}
