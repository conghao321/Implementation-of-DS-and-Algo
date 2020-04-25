import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MSTKruskal {
    private Queue<Edge> mst;
    private PriorityQueue<Edge> pq;
    private UnionFind uf;


    public MSTKruskal(EdgeWeightedGraph g) {
        mst = new LinkedList<>();

        pq = new PriorityQueue<>();   //把边们来排个序，再弹出最小的
        uf = new UnionFind(g.V()); // uf 来判断是否有环（已经联通过，不然加进去就有环了）

        for (Edge e : g.edges()) {
            pq.add(e);
        }
        Kruskal(g);
    }

    private void Kruskal(EdgeWeightedGraph g) {
        int v, w;
        while (!pq.isEmpty() && mst.size() < g.V() - 1) { //10个点只有9个边，小于9才进入循环等于9了还搞个屁

            Edge e = pq.remove();
            v = e.from();
            w = e.to();
            if (uf.connected(v, w)) continue;  //
            uf.union(v, w);
            mst.add(e);
        }
    }

    public Iterable<Edge> Mst() {

        return mst;
    }

    public static void main(String[] args) {
        EdgeWeightedGraph g = new EdgeWeightedGraph(6);
        g.addEdge(0, 1, 4);
        g.addEdge(0, 2, 3);
        g.addEdge(0, 3, 2);
        g.addEdge(0, 4, 1);
        g.addEdge(4, 1, 7);
        g.addEdge(2, 3, 5);
        g.addEdge(1, 5, 8);

        MSTKruskal mst = new MSTKruskal(g);
        for (Edge x : mst.Mst()) {
            System.out.println("from " + x.from() + "To " + x.to() + "weight: " + x.weight());
        }

    }
}
