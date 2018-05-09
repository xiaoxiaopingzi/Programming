package MinimumSpanTree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadWeightGraph {
	public ReadWeightGraph(WeightGraph graph, String fileName) throws IOException{
		BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));;
		String[] strs = reader.readLine().split(" ");
		 int V = Integer.parseInt(strs[0]);
         if (V < 0){
        	 throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
         }
         assert V == graph.V();

         int E = Integer.parseInt(strs[1]);
         if (E < 0){
        	 throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
         }
         
         for (int i = 0; i < E; i++) {
        	 strs = reader.readLine().split(" ");
             int v = Integer.parseInt(strs[0]);
             int w = Integer.parseInt(strs[1]);
             double weight = Double.parseDouble(strs[2]);
             assert v >= 0 && v < V;
             assert w >= 0 && w < V;
             graph.addEdge(v, w, weight);
         }
	}
}
