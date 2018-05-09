package shortestPath;

import java.io.IOException;

import org.junit.Test;

public class Main {
    
 // 测试我们的Dijkstra最短路径算法
    @Test
    public void testDijkstra() throws IOException{
    	String filename = "shortestPath/dijkstra.txt";
        int V = 5;
//        SparseWeightGraph g = new SparseWeightGraph(V, true);
        SparseWeightGraph g = new SparseWeightGraph(V, false);
        // Dijkstra最短路径算法同样适用于有向图
        @SuppressWarnings("unused")
		ReadWeightGraph readGraph = new ReadWeightGraph(g, filename);
        System.out.println("Test Dijkstra:\n");
        Dijkstra dij = new Dijkstra(g,0);
        for( int i = 1 ; i < V ; i ++ ){
            if(dij.hasPathTo(i)) {
                System.out.println("Shortest Path to " + i + " : " + dij.shortestPathTo(i));
                dij.showPath(i);
            }
            else
                System.out.println("No Path to " + i );

            System.out.println("----------");
        }
    }
    
    // 测试我们的Bellman-Ford最短路径算法
    // 对于负权边，一般是有向图，因为对于无向图，如果有负权边，则一定会出现负权环
    @Test
    public void testBellmanFord() throws IOException{
//		String filename = "shortestPath/bellmanFord.txt";
		String filename = "shortestPath/testG_negative_circle.txt";
		int V = 5;

		SparseWeightGraph g = new SparseWeightGraph(V, true);
		@SuppressWarnings("unused")
		ReadWeightGraph readGraph = new ReadWeightGraph(g, filename);

		System.out.println("Test Bellman-Ford:\n");
		BellmanFord bellmanFord = new BellmanFord(g, 0);
		if (bellmanFord.negativeCycle())
			System.out.println("The graph contain negative cycle!");
		else
			for (int i = 1; i < V; i++) {
				if (bellmanFord.hasPathTo(i)) {
					System.out.println("Shortest Path to " + i + " : "
							+ bellmanFord.shortestPathTo(i));
					bellmanFord.showPath(i);
				} else
					System.out.println("No Path to " + i);

				System.out.println("----------");
			}
     }
}
