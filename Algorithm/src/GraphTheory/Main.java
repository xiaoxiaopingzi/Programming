package GraphTheory;

import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		Graph graph = new DenseGraph(13, false);
		@SuppressWarnings("unused")
		ReadGraph readGraph = new ReadGraph(graph, "testG1.txt");
		System.out.println("test G1 in DenseGraph Graph:");
//		graph.show();
		Components component = new Components(graph);
		System.out.println("图1中连通图的个数为：" + component.count());
		// 测试图1中节点0和节点6是否连通
		System.out.println("图1中节点0和节点6是否连通：" + component.isConnection(0, 6));
		System.out.println("图1中节点0和节点6的路径为：");
		Path path = new Path(graph, 0);
		path.showPath(6);
		 
		System.out.println();

		graph = new SparseGraph(6, false);
		readGraph = new ReadGraph(graph , "testG2.txt" );
        System.out.println("test G2 in SparseGraph Graph:");
        graph.show();
        component = new Components(graph);
		System.out.println("图2中连通图的个数为：" + component.count());
		// 测试图2中节点0和节点4是否连通
		System.out.println("图2中节点0和节点4是否连通：" + component.isConnection(0, 4));
		System.out.println("图2中节点0和节点4的路径为：");
		path = new Path(graph, 0);
		path.showPath(4);
		
		System.out.println();
		
		graph = new SparseGraph(7, false);
		readGraph = new ReadGraph(graph , "testG3.txt" );
		System.out.println("test G3 in SparseGraph Graph:");
		graph.show();
        component = new Components(graph);
		System.out.println("图3中连通图的个数为：" + component.count());
		// 测试图3中节点0和节点6是否连通
		System.out.println("图3中节点0和节点6是否连通：" + component.isConnection(0, 4));
		System.out.println("图3中节点0和节点6的路径为：");
		path = new Path(graph, 0);
		path.showPath(6);
		ShortestPath shortestPath = new ShortestPath(graph, 0);
		shortestPath.showPath(6);
	}
}
