import java.util.Stack;

public class TopologicalSort {
    // 有向图的拓扑排序
    private boolean marked[];
    private int edgeTo[];
    Stack<Integer> stack;


    public TopologicalSort(Digraph g) {
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        stack = new Stack<>();

        for (int i = 0; i < g.V(); i++) { //从0开始
            if (!marked[i])
                dfs(g, i);
        }

    }

    public void dfs(Digraph g, int v) {

        if (marked[v] == true) return;
        marked[v] = true;

        for (int w : g.adj(v)) {  //如果没有邻接的节点了或者都访问过了，就跳过了
            dfs(g, w);   // v ---> W ,现在W 返回：
            edgeTo[w] = v; // w 的父节点应该是V
        }
        stack.push(v);
    }


    public void topologicalSort() {
        while (!stack.isEmpty())
            System.out.println(stack.pop());
    }

    public static void main(String[] args) {
        Digraph g = new Digraph(7);
        g.addEdge(0, 1);
        g.addEdge(0, 5);
        g.addEdge(0, 2);
        g.addEdge(3, 2);
        g.addEdge(3, 5);
        g.addEdge(5, 2);
        g.addEdge(3, 6);
        g.addEdge(3, 4);
        g.addEdge(1, 4);
        g.addEdge(6, 4);
        g.addEdge(6, 0);

        TopologicalSort top = new TopologicalSort(g);
        top.topologicalSort();


    }


}
