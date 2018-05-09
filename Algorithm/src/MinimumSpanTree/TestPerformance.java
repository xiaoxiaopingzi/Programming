package MinimumSpanTree;

import java.io.IOException;

/**
 * 最小生成树中算法的复杂度：
 * 		Lazy Prim	o(ElogE)
 * 		Prim 		o(ElogV)
 * 		Kruskal		o(ElogE)
 * @author Administrator
 * @date   2017-9-2
 */
@SuppressWarnings("unused")
public class TestPerformance {
    // 比较Lazy Prim, Prim和Kruskal的时间性能
    public static void main(String[] args) throws IOException {

        String filename1 = "weightGraph/testG1.txt";
        int V1 = 8;

        String filename2 = "weightGraph/testG2.txt";
        int V2 = 250;

        String filename3 = "weightGraph/testG3.txt";
        int V3 = 1000;

        String filename4 = "weightGraph/testG4.txt";
        int V4 = 10000;

        //String filename5 = "testG5.txt";
        //int V5 = 1000000;


        // 文件读取
        SparseWeightGraph g1 = new SparseWeightGraph(V1, false);
       
		ReadWeightGraph readGraph1 = new ReadWeightGraph(g1, filename1);
        System.out.println( filename1 + " load successfully.");

        SparseWeightGraph g2 = new SparseWeightGraph(V2, false);
        ReadWeightGraph readGraph2 = new ReadWeightGraph(g2, filename2);
        System.out.println( filename2 + " load successfully.");

        SparseWeightGraph g3 = new SparseWeightGraph(V3, false);
        ReadWeightGraph readGraph3 = new ReadWeightGraph(g3, filename3);
        System.out.println( filename3 + " load successfully.");

        SparseWeightGraph g4 = new SparseWeightGraph(V4, false);
        ReadWeightGraph readGraph4 = new ReadWeightGraph(g4, filename4);
        System.out.println( filename4 + " load successfully.");

//        SparseWeightGraph g5 = new SparseWeightGraph(V5, false);
//        ReadWeightGraph readGraph5 = new ReadWeightGraph(g5, filename5);
//        System.out.println( filename5 + " load successfully.");

        System.out.println();


        long startTime, endTime;

        // Test Lazy Prim MST
        System.out.println("Test Lazy Prim MST:");

        startTime = System.currentTimeMillis();
        LazyPrimMST lazyPrimMST1 = new LazyPrimMST(g1);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G1: " + (endTime-startTime) + "ms.");

        startTime = System.currentTimeMillis();
        LazyPrimMST lazyPrimMST2 = new LazyPrimMST(g2);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G2: " + (endTime-startTime) + "ms.");

        startTime = System.currentTimeMillis();
        LazyPrimMST lazyPrimMST3 = new LazyPrimMST(g3);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G3: " + (endTime-startTime) + "ms.");

        startTime = System.currentTimeMillis();
        LazyPrimMST lazyPrimMST4 = new LazyPrimMST(g4);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G4: " + (endTime-startTime) + "ms.");

//        startTime = System.currentTimeMillis();
//        LazyPrimMST lazyPrimMST5 = new LazyPrimMST(g5);
//        endTime = System.currentTimeMillis();
//        System.out.println("Test for G5: " + (endTime-startTime) + "ms.");

        System.out.println();


        // Test Prim MST
        System.out.println("Test Prim MST:");

        startTime = System.currentTimeMillis();
        PrimMST primMST1 = new PrimMST(g1);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G1: " + (endTime-startTime) + "ms.");

        startTime = System.currentTimeMillis();
        PrimMST primMST2 = new PrimMST(g2);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G2: " + (endTime-startTime) + "ms.");

        startTime = System.currentTimeMillis();
        PrimMST primMST3 = new PrimMST(g3);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G3: " + (endTime-startTime) + "ms.");

        startTime = System.currentTimeMillis();
        PrimMST primMST4 = new PrimMST(g4);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G4: " + (endTime-startTime) + "ms.");

//        startTime = System.currentTimeMillis();
//        PrimMST primMST5 = new PrimMST(g5);
//        endTime = System.currentTimeMillis();
//        System.out.println("Test for G5: " + (endTime-startTime) + "ms.");

        System.out.println();


        // Test Kruskal MST
        System.out.println("Test Kruskal MST:");

        startTime = System.currentTimeMillis();
        KruskalMST kruskalMST1 = new KruskalMST(g1);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G1: " + (endTime-startTime) + "ms.");

        startTime = System.currentTimeMillis();
        KruskalMST kruskalMST2 = new KruskalMST(g2);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G2: " + (endTime-startTime) + "ms.");

        startTime = System.currentTimeMillis();
        KruskalMST kruskalMST3 = new KruskalMST(g3);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G3: " + (endTime-startTime) + "ms.");

        startTime = System.currentTimeMillis();
        KruskalMST kruskalMST4 = new KruskalMST(g4);
        endTime = System.currentTimeMillis();
        System.out.println("Test for G4: " + (endTime-startTime) + "ms.");

//        startTime = System.currentTimeMillis();
//        KruskalMST kruskalMST5 = new KruskalMST(g5);
//        endTime = System.currentTimeMillis();
//        System.out.println("Test for G5: " + (endTime-startTime) + "ms.");

        System.out.println();
    }
}
