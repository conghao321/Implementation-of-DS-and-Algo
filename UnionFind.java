public class UnionFind {
    private int[] parent;
    private int[] size;

    public UnionFind(int n) { // 总共有n个点 union-find
        parent = new int[n];
        size = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int root(int v) {
        while (v != parent[v]) {
            v = parent[v];
        }
        return v;
    }

    public void union(int v, int w) {

        if (root(v) == root(w)) return;

        if (size[v] > size[w]) {     // 把小的往大的下面放
            parent[root(w)] = root(v); //这个地方必须用根来确定，目的是把w的根和v的根一样， w有一大群，共用一个根，只要找到这一个根，
            // 修改她自己的parent和v的根一样，w子节点还有w作为根就OK
            size[v] += size[w];

        } else {
            parent[root(v)] = root(w);
            size[w] += size[v];
        }

    }

    public boolean connected(int v, int w) {
        return root(v) == root(w);
    }

    public static void main(String args[]) {
        UnionFind uf = new UnionFind(10);
        uf.union(9, 4);
        uf.union(2, 9);
        uf.union(3, 4);
        uf.union(7, 8);
        uf.union(7, 6);
        uf.union(1, 6);

        System.out.println(uf.connected(9, 7));
        System.out.println(uf.connected(3, 2));
        System.out.println(uf.connected(1, 0));
        System.out.println(uf.connected(7, 2));
        uf.union(7, 3);
        System.out.println(uf.connected(7, 2));

    }


}
