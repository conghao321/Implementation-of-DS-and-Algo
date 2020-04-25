import java.util.Stack;

public class GraphDFS {
    public boolean marked[];
    public int edgeTo[];
    private int source;

    public GraphDFS(Graph g, int s) {  // source=0;
        int size = g.V();
        marked = new boolean[size];
        edgeTo = new int[size];
        dfs(g, s);
    }
    public GraphDFS(Digraph g, int s) {  // source=0;
        int size = g.V();
        marked = new boolean[size];
        edgeTo = new int[size];

    }

    public GraphDFS(Digraph g, Bag<Integer> s) {  // source=0;
        int size = g.V();
        marked = new boolean[size];
        edgeTo = new int[size];

        for(int v:s) {
            dfs(g,v);
        }

    }


    public void dfs(Digraph g, int v) {  // digraph dfs

        if (marked[v] == true) return;
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {  // 只有这个点没被访问过我们才做事
                dfs(g, w);   // v ---> W ,现在W 返回：
                edgeTo[w] = v; // w 的父节点应该是V
            }
        }
    }


    public void dfs(Graph g, int v) { // graph dfs

        if (marked[v] == true) return;
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {  // 只有这个点没被访问过我们才做事
                dfs(g, w);   // v ---> W ,现在W 返回：
                edgeTo[w] = v; // w 的父节点应该是V
            }
        }
    }


    public boolean hasPathTo(int v) {
        return marked[v];    // 从0号点开始，是否有路径能抵达V号点
    }

    public Iterable<Integer> pathTo(int v) {
        if (marked[v] == false) return null;
        int x = v;
        Stack<Integer> path = new Stack<>();
        while (x != 0) {    // x如果等于0 说明当前节点已经是起点0了;
            path.push(x);    // 直接先把目的地放入stack中
            x = edgeTo[x];    //下一个要放的是edgeTo里的父节点
        }
        path.push(0);

        return path;
    }


    public static void main(String[] args) {
        Graph g = new Graph(14);
        g.addEdge(0, 2);
        g.addEdge(4, 3);
        g.addEdge(0, 1);
        g.addEdge(9, 12);
        g.addEdge(4, 6);
        g.addEdge(5, 4);
        g.addEdge(0, 2);
        g.addEdge(12, 11);
        g.addEdge(7, 8);
        g.addEdge(9, 11);
        g.addEdge(9, 10);
        g.addEdge(0, 6);
        g.addEdge(5, 3);
        g.addEdge(0, 9);
        g.addEdge(8, 9);

        GraphDFS gdfs = new GraphDFS(g, 0);
        System.out.println(gdfs.hasPathTo(7));
        System.out.println(gdfs.pathTo(7));

    }


}
