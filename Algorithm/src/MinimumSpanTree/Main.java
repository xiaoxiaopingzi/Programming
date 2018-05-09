package MinimumSpanTree;

import java.io.IOException;
import java.util.List;

// 测试权重图
public class Main {
	public static void main(String[] args) throws IOException {
        String filename = "weightGraph/testG1.txt";
        WeightGraph graph = new SparseWeightGraph(8, false);
        @SuppressWarnings("unused")
		ReadWeightGraph readGraph = new ReadWeightGraph(graph, filename);
        System.out.println("使用稀疏权重图测试图5:");
        graph.show();
 
        System.out.println();

//        graph = new DenseWeightGraph(8, false);
//        readGraph = new ReadWeightGraph(graph , filename );
//        System.out.println("使用稠密权重图测试图5:");
//        graph.show();
//
//        System.out.println();
        
        // Test Lazy Prim MST
        System.out.println("Test Lazy Prim MST:");
        LazyPrimMST lazyPrimMST = new LazyPrimMST(graph);
        List<Edge<Double>> mst = lazyPrimMST.mstEdges();
        for( int i = 0 ; i < mst.size() ; i ++ ){
        	System.out.println(mst.get(i));
        }
        System.out.println("The MST weight is: " + lazyPrimMST.result());

        System.out.println();
        
        System.out.println("Test Prim MST:");
        PrimMST primMST = new PrimMST(graph);
        mst = primMST.mstEdges();
        for( int i = 0 ; i < mst.size() ; i ++ ){
        	System.out.println(mst.get(i));
        }
        System.out.println("The MST weight is: " + primMST.result());
	}
}
