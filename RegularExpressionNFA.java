import java.util.Stack;

public class RegularExpressionNFA {
    int M; // pattern length
    Digraph g; // e-transions Digraph
    int position;

    private class epsilonTransitionDigraph {  // 先把所有e-transtions搞成Digraph，以后我们用dfs探索深入
        // 内部类
        public epsilonTransitionDigraph (String rep) {
            g= new Digraph(M+1); // 别忘了 accept state
            Stack<Integer> ops= new Stack<>(); // 暂存state节点用，state节点是数字，但是内容是字符

            for (int i =0; i<M;i++) {
                char c=rep.charAt(i);
                int lp=i;

                if(c=='|' || c=='(') {    //遇到或，或者左括号，要入栈，等着右括号出现
                    ops.push(lp);
                }
                else if (c==')') {    //遇到右括号就要准备从stack 中弹出了
                    int or=ops.pop();

                    if(rep.charAt(or)== '|') {    // 如果现出来 |， 那么后面一定还有一个 ‘（’
                        lp= ops.pop();
                        g.addEdge(lp,or);         //构建二选一双边
                        g.addEdge(or+1,i);
                    }
                    else lp=or; // 如果只出现‘（’，那就设定其为lp
                }

                if (rep.charAt(i+1)=='*' && i<M-1) {
                    // 如果下一个是*别忘了添加往返路
                    // 默认lp 是i ，但如果有括号，那么 lp 就是左括号，反正要回到这段的起点,构建往返路
                    g.addEdge(lp,i+1);
                    g.addEdge(i+1,lp);
                }
                if(rep.charAt(i)=='*' || rep.charAt(i)=='(' || rep.charAt(i)==')'  ) {
                    //不管是左括号右括号还是*，都要直接天下一个向下一个state前进的边
                   g.addEdge(i,i+1);
                }
            }
        }
    } //内部e-transions类

    public RegularExpressionNFA(String rep,String txt) {   //构造正则表达式
        M=rep.length();
        epsilonTransitionDigraph eTransions= new epsilonTransitionDigraph(rep);
        GraphDFS dfs= new GraphDFS(g,0); // 构建dfs

        Bag<Integer> pc = new Bag<>(); // 可以到达的所有state

        dfs.dfs(g,0);
        for(int i=0;i<g.V();i++) {
            if (dfs.marked[i]==true) pc.add(i);  // 先把一开始所有可以通过e-transitions到达的state揪出来
        }

        //开始逐个字符匹配对比了！！！
        for(int i=0;i<txt.length();i++) {
            Bag<Integer> match = new Bag<>();
            for(int v:pc) {  //看看这些我们能到的点里头，有哪一个能匹配上的
                if(v==M)  continue; //如果到头了 挺好的
                if(txt.charAt(i)==rep.charAt(v) || rep.charAt(v)=='.')
                    match.add(v+1);  // 表示match-transions，我们可以直达下一阶段，从新的阶段开始继续DFS
            }

            dfs=new GraphDFS(g,match);   // 从match后的点继续DFS，看看哪些state点是我们可以通过e-transitions新到的
            pc = new Bag<>();
            for(int v=0;v<M;v++) {
                if(dfs.marked[v]==true) pc.add(v);  // 如果能到达我们就加入pc，准备新的一轮比对
            }
            for(int v:pc)
                if(v==M) {
                    position=i;
                }
        }


    }

    public int getPosition() {
        return position;
    }

    public static void main(String [] args) {
        System.out.println("fuckkjl");
    }
}
