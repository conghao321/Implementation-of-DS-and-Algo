public class ConnectedComponent {
    // 连通分量
    private boolean[] marked;  //节点是否被访问
    private int[] ccId;   //每一个节点属于第几个连通分量
    private int count;     //当前是第几个连通分量

    public ConnectedComponent(Graph g) {
        count = 0;
        ccId = new int[g.V()];
        marked = new boolean[g.V()];

        for (int i = 0; i < g.V(); i++) {
            if (!marked[i]) {
                dfs(g, i);
                count++;
            }
        }
    }

    public void dfs(Graph g, int v) {  //通过dfs来进行标记
        if (marked[v] == true) return;  //如果已经标记就返回咯！

        marked[v] = true;   //标记访问过没有
        ccId[v] = count;   //标记组

        for (int w : g.adj(v)) {
            dfs(g, w);
        }

    }

    public boolean connected(int v, int w) {
        return ccId[v] == ccId[w];
    }

    public int count() {

        return count;
    }

    public static void main(String[] args) {
        Graph g = new Graph(7);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(0, 4);
        g.addEdge(5, 6);

        ConnectedComponent cc = new ConnectedComponent(g);
        System.out.println(cc.connected(3, 5));

    }
}
