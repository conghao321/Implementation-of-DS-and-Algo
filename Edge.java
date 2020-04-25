public class Edge implements Comparable<Edge> {  // 带权重的无向图的边API
    private int v;
    private int w;
    private double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public double weight() {
        return weight;
    }


    public int compareTo(Edge that) {

        if (this.weight < that.weight) return -1;
        else if (this.weight > that.weight) return 1;
        else return 0;

    }

}
