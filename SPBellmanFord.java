import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class SPBellmanFord {

    private int[] edgeTo; // parent vertex;
    private double[] distTo; //distance to source;
    private int V;
    private int E;
    private Queue<Integer> q;
    private boolean[] onQ;


    public SPBellmanFord(EdgeWeightedDigraph g) {
        this.V = g.V();
        this.E = g.E();

        edgeTo = new int[g.V()];
        distTo = new double[g.V()];
        q = new LinkedList<>();
        onQ = new boolean[g.V()];
        // 判断点是否已经在准备relax的队列里了
        // 如果2在队列里则表示早晚会更新从2出发的边，我们只可能更新指向2的边，改变到2的距离就可以了
        // 如果点x不在队列里，则加入队列，表示这个点我们早晚都要relax它

        for (int i = 0; i < g.V(); i++) {
            edgeTo[i] = -1;
            distTo[i] = Double.POSITIVE_INFINITY;
        }

        distTo[0] = 0;

        q.add(0);
        onQ[0] = true;
        // 开始算法

        while (!q.isEmpty()) {

            int v = q.remove();
            onQ[v] = false;   //这个点从onqueue里出来，这样可以被后来的新点更新！
            relax(g, v);
        }
    }

    private void relax(EdgeWeightedDigraph g, int v) {
        for (Edge e : g.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {  //如果这个点v需要放松，就把它指出的点都入队

                distTo[w] = distTo[v] + e.weight();    // 放松
                edgeTo[w] = v;

                if (!onQ[w]) {                        // 不管这个点v指出的点在不在队列里，他一定都是需要放松的
                    q.add(w);
                    onQ[w] = true;
                }
            }
        }
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


}
