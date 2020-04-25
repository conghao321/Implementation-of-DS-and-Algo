import java.util.PriorityQueue;

public class HuffmanTree { // 构建霍夫曼编码树

    private int[] freqTable;
    private Node root; // Trie 的root。。。
    private int R=256;

    // 内部类
    private class Node implements Comparable<Node>{  // 构建霍夫曼内部节点类，频率越低的，越底层
        private int freq;
        private char ch;
        private Node left;
        private Node right;

        public Node(char ch,int freq,Node left,Node right) {  // 节点么，这几个参数
            this.ch=ch;
            this.freq=freq;
            this.left=left;
            this.right=right;
        }

        public boolean isLeaf() {  // 叶节点，主要看有没有孩子！
            return (left==null && right==null);
        }

        public int compareTo(Node that) {//根据频率要比大小
            return this.freq-that.freq;
        }
    }
    // 内部类

    public HuffmanTree(String s) {

        freqTable = new int[R];
        for(int i=0;i<s.length();i++) {  // 制作出现频率表
            freqTable[s.charAt(i)]++;
        }

        buildTrie();
    }

    private void buildTrie() {
        PriorityQueue<Node> pq=new PriorityQueue<>();
        for(int i=0;i<R;i++) {   // 先把单个节点列出来，比较舒服
            if(freqTable[i]>0)
                pq.add(new Node((char)i,freqTable[i],null,null));
        }
        // 不断地排队，合并,重新入队！
        while(pq.size()>1) { // 这个地方最难！如果只剩下一个节点，就说明成了一棵树了！！！！

            Node x=pq.remove();
            Node y=pq.remove();
            pq.add(new Node('\0',x.freq+y.freq,x,y)); //最关键点，这个地方中间的节点应该用\0来表示

        }
        root=pq.remove();
    }

}
