import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MSTPrimLazy {  //简单版的Prim算法实现
    private boolean[] marked;  //是否在树上
    private PriorityQueue<Edge> pq;
    private Queue<Edge> mst;

    public MSTPrimLazy(EdgeWeightedGraph g) {
        marked = new boolean[g.V()];
        pq = new PriorityQueue<>();
        mst = new LinkedList<>();
        prim(g);
    }

    private void prim(EdgeWeightedGraph g) {

        visit(g, 0); //把零号点相邻的边加入

        while (!pq.isEmpty() && mst.size() < g.V() - 1) {

            Edge x = pq.remove(); //调出最小的边加入

            if (marked[x.from()] && marked[x.to()]) continue;  // 如果这两个点都已经在生成树里就不加入树了！！

            mst.add(x);

            if (!marked[x.from()])                //这条边的哪个点没访问过就访问哪个边。。。。
                visit(g, x.from());
            else if (!marked[x.to()])
                visit(g, x.to());

        }

    }

    private void visit(EdgeWeightedGraph g, int v) {   // 拜访某个点，把它四周的边加入pq，准备筛选最小的
        marked[v] = true;  // 只有visit过的，才会被marked
        for (Edge e : g.adj(v)) {
            int from = e.from();
            int to = e.to();
            pq.add(e);  // 访问这个点就把所有的边加入pq

        }
    }

    public Iterable<Edge> Mst() {

        return mst;
    }

    public static void main(String[] args) {
        EdgeWeightedGraph g = new EdgeWeightedGraph(7);
        g.addEdge(0, 1, 4);
        g.addEdge(0, 2, 3);
        g.addEdge(0, 3, 2);
        g.addEdge(0, 4, 1);
        g.addEdge(1, 3, 11);
        g.addEdge(3, 4, 12);
        g.addEdge(4, 1, 7);
        g.addEdge(2, 3, 5);
        g.addEdge(1, 5, 8);
        g.addEdge(3, 6, 19);

        MSTPrimLazy mst = new MSTPrimLazy(g);
        for (Edge x : mst.Mst()) {
            System.out.println("from " + x.from() + "To " + x.to() + "weight: " + x.weight());
        }
    }
}
