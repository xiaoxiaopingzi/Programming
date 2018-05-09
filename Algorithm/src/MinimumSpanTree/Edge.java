package MinimumSpanTree;

@SuppressWarnings("unchecked")
public class Edge<Weight extends Comparable> implements Comparable<Edge<Weight>> {
	
	private int a, b;
	private Weight weight;
	
	public Edge(int a, int b, Weight weight) {
		this.a = a;
		this.b = b;
		this.weight = weight;
	}
	
	// 返回边的一些信息
	public int v(){ return a;}
	public int w(){ return b;}
	public Weight wt(){ return weight;}
	
	// 根据一个顶点的值，返回另一个顶点的值
	public int other(int x){
		if (x == a || x == b) {
			return x == a ? b : a;
		}else {
			return 0;
		}
	}

	  // 输出边的信息
    public String toString(){
        return "" + a + "-" + b + ": " + weight;
    }

    // 边之间的比较
    public int compareTo(Edge that)
    {
        if( weight.compareTo(that.wt()) < 0 )
            return -1;
        else if ( weight.compareTo(that.wt()) > 0 )
            return +1;
        else
            return  0;
    }
}
