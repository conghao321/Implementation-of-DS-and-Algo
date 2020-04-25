import java.util.Stack;

public class SPDijkstra {
    //Dijkstra's Algorithm to find Shortest Path

    private int[] edgeTo; // parent vertex;
    private double[] distTo; //distance to source;
    private int V;
    private int E;

    private IndexMinPQ<Double> pq;

    public SPDijkstra(EdgeWeightedDigraph g) {
        this.V = g.V();
        this.E = g.E();

        int s = 0; // 起点为0;

        pq = new IndexMinPQ<>(g.V()); // 这个比较特殊，能够降低元素的key，保证能随时更新弹出最小边

        // 不能用Queue,我们必须确保每次都选出距离起点最短的那个点！！！
        // 不能用Queue,我们必须确保每次都选出距离起点最短的那个点！！！
        // 不能用Queue,我们必须确保每次都选出距离起点最短的那个点！！！
        // 不能用Queue,我们必须确保每次都选出距离起点最短的那个点！！！


        edgeTo = new int[g.V()];
        distTo = new double[g.V()];

        for (int i = 0; i < g.V(); i++) {
            edgeTo[i] = -1;
            distTo[i] = Double.POSITIVE_INFINITY;
        }

        distTo[0] = 0;

        pq.insert(s, 0.0);
        Dijkstra(g, s);

    }

    private void Dijkstra(EdgeWeightedDigraph g, int s) {

        while (!pq.isEmpty()) {
            int v = pq.delMin();

            for (Edge e : g.adj(v)) {

                relax(e);

            }
        }

    }

    private void relax(Edge e) {
        int v = e.from();
        int w = e.to();

        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = v;
        }

        if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
            // 如果pq里有这个点（之前被更新过，但还未弹出）
            // 那么我们就更新到这个点的距离后降key，让它能更早的弹出来
            //0-7 (10)大于0-1 (1)，但是1-2可能是100，而7-2可能只有1；所以 0-1-2可能是101； 而 0-7-2 则值有11,则2需要被更新
            // 点2 虽然在pq，未经过更新的话却很难被弹出来
        else pq.insert(w, distTo[w]);    // 否则 将其加入优先队列

    }

    public Iterable<Integer> pathTo(int v) {
        Stack<Integer> stack = new Stack<>();

        stack.push(v);
        while (edgeTo[v] != -1) {  // if v==-1，此时标明前一个点已经是0了,则停止，
            stack.push(edgeTo[v]);
            v = edgeTo[v];
        }

        return stack;
    }


    public static void main(String[] args) {
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(7);
        g.addEdge(0, 1, 4);
        g.addEdge(0, 2, 3);
        g.addEdge(0, 3, 85);
        g.addEdge(0, 4, 99);
        g.addEdge(1, 3, 11);
        g.addEdge(1, 4, 11);
        g.addEdge(4, 5, 11);


        SPDijkstra sp = new SPDijkstra(g);
        for (Integer x : sp.pathTo(5)) {
            System.out.println(x);
        }
    }
}
