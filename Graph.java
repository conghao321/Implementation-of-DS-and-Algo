public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private int E; // edges
    private int V; // vertices
    private Bag<Integer>[] adj;   // 每一个节点的邻接节点组成一个背包，一个由背包元素组成的背包数组

    public Graph(int V) {  // 手动建图，我们当然不能这么SB
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];

        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<Integer>();  // 给每一个背包元素新建为为一个空背包
        }

    }


    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public int V() {
        return this.V;
    }

    public int E() {
        return this.E;
    }


    //
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
    //


    public static void main(String[] args) {
        Graph g = new Graph(13);
        g.addEdge(0, 5);
        g.addEdge(4, 3);
        g.addEdge(0, 1);
        g.addEdge(9, 12);
        g.addEdge(4, 6);
        g.addEdge(5, 4);
        g.addEdge(0, 2);
        g.addEdge(12, 11);
        g.addEdge(8, 7);
        g.addEdge(9, 11);
        g.addEdge(9, 10);
        g.addEdge(0, 6);
        g.addEdge(5, 3);
        System.out.println(g.toString());
        System.out.println(g.V());
    }

}
