import java.util.LinkedList;
import java.util.Queue;

public class GraphBFS {
    private boolean marked[];
    private QueueLinkedlist<Integer> q;
    private int edgeTo[];
    private Queue<Integer> path;

    public GraphBFS(Graph g, int s) {  // source=0;
        int size = g.V();
        marked = new boolean[size];
        edgeTo = new int[size];
        q = new QueueLinkedlist<>();
        path = new LinkedList<>();
        bfs(g, s);

    }

    public void bfs(Graph g, int s) {   // 跌代来写
        q.enqueue(s);
        path.add(s);
        marked[s] = true;

        while (!q.isEmpty()) {
            int v = q.dequeue();
            // 开始遍历
            for (int w : g.adj(v)) {
                if (!marked[w]) {  //只有这个点没被访问我们才做事
                    q.enqueue(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                    path.add(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];    // 从0号点开始，是否有路径能抵达V号点
    }

    public Iterable<Integer> pathTo(int v) {

        return path;
    }


    public static void main(String[] args) {
        Graph g = new Graph(14);
        g.addEdge(0, 2);
        g.addEdge(0, 1);
        g.addEdge(0, 5);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(3, 5);
        g.addEdge(2, 4);
        g.addEdge(0, 5);

        GraphBFS gbfs = new GraphBFS(g, 0);
        System.out.println(gbfs.hasPathTo(12));
        System.out.println(gbfs.pathTo(12));

    }


}
