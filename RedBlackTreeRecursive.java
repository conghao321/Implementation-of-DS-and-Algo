public class RedBlackTreeRecursive {

    public Node root;
    QueueLinkedlist<Integer> q;

    private class Node { // inner class for a node
        Node left;
        Node right;
        int key;
        boolean isRed;

        public Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
            this.isRed = true;
        }

    }

    //判断颜色
    //因为刚插入会有空指针（root.left.left==null，所以isRed写成函数，判断node x，如果是null返回false就完事了）
    public boolean isRed(Node x) {
        if (x == null) return false;
        return x.isRed;
    }


    // 红黑树三个插入的三个操作，非常重要

    private Node rotateLeft(Node x) { //右倾变左倾
        Node temp = x.right;
        x.right = temp.left;
        temp.left = x;
        // 别忘了变色...把原本的右下变得颜色和左上一样，把新的左下节点设置为红色
        temp.isRed = x.isRed;
        x.isRed = true;
        return temp;
    }

    private Node rotateRight(Node x) { //左倾变右倾
        Node temp = x.left;
        x.left = temp.right;
        temp.right = x;
        // 别忘了变色...把原本的右下变得颜色和左上一样，把新的左下节点设置为红色
        temp.isRed = x.isRed;
        x.isRed = true;
        return temp;
    }

    private void flipColor(Node x) { // 当前节点左右都是红色，就需要把左右都变成黑色，而把当前节点染红
        x.left.isRed = false;
        x.right.isRed = false;
        x.isRed = true;
    }

    public void put(int key) {
        root = put(root, key);
        root.isRed = false;
    }

    // 递归中凡是节点返回类型为Node 说明一定是 x.left/right= fun（x.left）,return的是当前节点X
    private Node put(Node x, int key) {
        if (x == null) return new Node(key);    // we need put() to help us to build a new node!
        else if (key < x.key) x.left = put(x.left, key);   // x.left points to our new node
        else if (key > x.key) x.right = put(x.right, key);  // left=left right=right,
        else x.key = key;

        //必须要左右两个节点一起确定！！！才能判断！！
        //右黑左红才能进行左倾
        if (!isRed(x.left) && isRed(x.right)) x = rotateLeft(x);  //右倾变左倾
        if (isRed(x.left.left) && isRed(x.left)) x = rotateRight(x);  // ↙ ↙连续两个左倾，则左倾需要变右倾↙ ↘，目的是为了方便下一步变色
        if (isRed(x.left) && isRed(x.right)) flipColor(x); // 变色前一定会有左倾变右倾这个步骤

        return x;
    }

    public int get(int key) {  // get() is same with bst
        Node x = root;
        while (x != null) {
            if (key > x.key) x = x.right;
            else if (key < x.key) x = x.left;
            else if (x.key == key) return key;
        }
        System.out.print("No key found");
        return -1; //return 0 means did not find!
    }


    public void inorder() {
        q = new QueueLinkedlist<Integer>();
        inorder(root);
        q.print();
    }

    private void inorder(Node x) {
        if (x == null) return;
        inorder(x.left);
        q.enqueue(x.key);
        inorder(x.right);

    }

    public static void main(String args[]) {

        RedBlackTreeRecursive bst = new RedBlackTreeRecursive();
        bst.put(3);
        bst.put(8);
        bst.put(4);
        bst.put(5);
        bst.put(44);
        bst.put(6);
        bst.put(14);
        bst.put(23);
        bst.put(28);
        bst.put(7);
        bst.put(22);
        bst.put(9);
        bst.put(12);
        bst.put(11);
        bst.put(16);
        bst.put(99);

        bst.inorder();
        /*
        bst.delete(16);


        System.out.println("delmin: " + bst.delMin(bst.root));
        System.out.println("max " + bst.Max());
        System.out.println("get " + bst.get(6));
        System.out.println("root= " + bst.root.key);
        System.out.println("floor = " + bst.floor(15));

        System.out.println("size = " + bst.size(bst.root));
        System.out.println("rank = " + bst.rank(9));
        bst.inorder();*/

    }

}
