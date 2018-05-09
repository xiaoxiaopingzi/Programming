package GraphTheory;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 寻找节点之间的路径
public class Path {

	private Graph graph;        // 图的引用
    private boolean[] visited;  // 记录深度优先遍历的过程中节点是否被访问
    private int[] from;  // 记录来源, from[i]表示查找的路径上i的上一个节点
    private int s;    // source节点,起始点
	
    // 获取s(source)节点到任意节点的一条路径
    // 构造函数, 寻路算法, 寻找图graph从s点到其他点的路径
	public Path(Graph graph, int s){
		assert( s >= 0 && s < graph.V());
		this.graph = graph;
		// 使用graph.V()获取图中节点的个数
		this.s = s;
		this.visited = new boolean[graph.V()];
		this.from = new int[graph.V()];
		for (int i = 0; i < graph.V(); i++) {
			visited[i] = false;
			from[i] = -1;
		}
		// 寻路算法
		dfs(s);
	}
	
	// 图的深度优先遍历 —— 递归
	// 深度优先遍历也叫深度优先搜索(Depth First Search)
	// 复杂度为：稀疏图(邻接表) ： o(V + E)
	// 每个节点都需要访问一次，同时需要访问每个节点的邻接节点
	// 稠密图(邻接矩阵) ： o(V^2)
	// 每个节点都需要访问一次，每个节点都需要遍历以获取改节点的所有邻接节点
	private void dfs(int v) {
		visited[v] = true;
		for(int i : graph.adj(v)){
			if (!visited[i]) {
				from[i] = v; // i节点来源于v节点
				dfs(i);
			}
		}
	}
	
	// 从节点s到节点w是否有路
	public boolean hasPath(int w){
		assert( w >= 0 && w < graph.V());
		// 如果visited[w]为true，就表示dfs(s)的过程中访问到了w节点
		// 则s节点到w节点之间一定有一条路
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
			System.out.println("节点" + this.s + "没有到节点" + w + "的路径！" );
			return null;
		}
	}
	
	public void showPath(int w){
		assert hasPath(w) ;
		List<Integer> path = getPath(w);
		System.out.print("DFS:");
		for( int i = 0 ; i < path.size() ; i ++ ){
            System.out.print(path.get(i));
            if( i == path.size() - 1 )
                System.out.println();
            else
                System.out.print(" -> ");
        }
	}
}
