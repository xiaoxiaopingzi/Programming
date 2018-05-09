package shortestPath;

// 图的接口
public interface WeightGraph {

    public int V();
    public int E();
    public void addEdge(int v, int w, double weight);
    boolean hasEdge(int v, int w);
    void show();
    public Iterable<Edge<Double>> adj(int v);
}
