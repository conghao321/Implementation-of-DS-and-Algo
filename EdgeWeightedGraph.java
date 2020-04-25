import java.util.LinkedList;
import java.util.Queue;

public class EdgeWeightedGraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private int E; // edges
    private int V; // vertices
    private Bag<Edge>[] adj;   // 每一个节点的邻接节点组成一个背包，一个由背包元素组成的背包数组
    private Queue<Edge> edges;

    public EdgeWeightedGraph(int V) {          // 手动建图，我们当然不能这么SB
        this.V = V;
        adj = (Bag<Edge>[]) new Bag[V];

        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<Edge>();  // 给每一个背包元素新建为为一个空背包
        }
        edges = new LinkedList<>();

    }


    public void addEdge(int v, int w, double weight) {
        Edge e = new Edge(v, w, weight);
        edges.add(e);
        adj[v].add(e);
        adj[w].add(e);
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public int V() {
        return this.V;
    }

    public int E() {
        return this.E;
    }

    public Iterable<Edge> edges() {
        return edges;
    }

    //
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);

        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (Edge w : adj[v]) {
                s.append("from: " + w.from() + " " + "To: " + w.to() + " " + "Weight: " + w.weight());
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
    //


    public static void main(String[] args) {
        EdgeWeightedGraph g = new EdgeWeightedGraph(13);
        g.addEdge(0, 1, 0.4);

        System.out.println(g.toString());
        System.out.println(g.V());
        System.out.println(g.edges());
    }
}
