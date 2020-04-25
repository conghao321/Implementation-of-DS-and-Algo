public class BinarySearchTreeRecursive {

    public Node root;
    QueueLinkedlist<Integer> q;

    private class Node { // inner class for a node
        Node left;
        Node right;
        int key;

        public Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }

    public void put(int key) {
        root = put(root, key);
    }

    // 递归中凡是节点返回类型为Node 说明一定是 x.left/right= fun（x.left）,return的是当前节点X
    private Node put(Node x, int key) {
        if (x == null) return new Node(key);    // we need put() to help us to build a new node!
        else if (key < x.key) x.left = put(x.left, key);   // x.left points to our new node
        else if (key > x.key) x.right = put(x.right, key);  // left=left right=right,
        else x.key = key;
        // we must return the up-level called
        return x;
    }

    public int get(int key) {  // get the key
        Node x = root;
        while (x != null) {
            if (key > x.key) x = x.right;
            else if (key < x.key) x = x.left;
            else if (x.key == key) return key;
        }
        System.out.print("No key found");
        return -1; //return 0 means did not find!
    }

    public int Max() {
        Node x = root;
        if (root == null) System.out.print("No root found"); // 0 means doesn't not exit;
        while (x.right != null) {
            x = x.right;
        }
        return x.key;
    }

    public Node min(Node x) {

        if (x == null) System.out.print("No min for Node x found"); // 0 means doesn't not exit;
        while (x.left != null) {
            x = x.left;
        }
        System.out.print("lailelaodi");
        return x;

    }

    public int floor(int key) {  // find the largest one which does not larger than key
        Node x = null;   // X means not the root but just a pointer!!!
        int floor = key;
        if (root == null) System.out.print("No root found");

        else x = floor(root, key); // use X to point the X key we can't ,x is just a pointer ,so use root to start;

        return x.key;
    }

    private Node floor(Node x, int key) { //! this is difficult!

        if (x == null) return null;
        if (key == x.key) return x;
        else if (key < x.key) x = floor(x.left, key);
        else {
            Node temp = x;  // we should remember this node before it turn right
            x = floor(x.right, key);   // go right
            if (x == null) x = temp;      // if no floor key found, we should return temp.
        }
        return x;
    }


    public int size(Node x) {  // return any node's subtree's size
        if (x == null) return 0;  // no this node
        else return 1 + size(x.right) + size(x.left);
    }

    public int rank(int key) { // how many elements less than key
        return rank(root, key);
    }

    private int rank(Node x, int key) {
        if (x == null) return -1;
        else if (key == x.key) return size(x.left);    // return left subtree's size
        else if (key < x.key) return rank(x.left, key);  // rank(x)==rank(x.left) due to x.key>key
        else return 1 + size(x.left) + rank(x.right, key);

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

    /* del _while version
     public int delMin() {
         if (root == null) return -1;
         Node temp;
         temp = delMin(root);
         return temp.key;
     }

     private Node delMin(Node x) {
         while (x.left.left != null) {
             x = x.left;
         }
         Node temp = x.left;
         x.left = x.left.right;
         temp.right = null;
         return temp;
     }
     */
    public void delMin() {
        root = delMin(root);
    }

    private Node delMin(Node x) {
        if (x.left != null) return x.left = delMin(x.left);  // x.left is a pointer, points to its left.
        else return x.right; // x.left--> Min_Node, return Node_min.right
    }

    public void delete(int key) {
        root = delete(root, key);

    }

    private Node delete(Node x, int key) {
        if (x == null) return null;

        if (key < x.key) x.left = delete(x.left, key);
        else if (key > x.key) x.right = delete(x.right, key);

        else { // found the key we want to delete
            if (x.left == null && x.right == null) return null;  // non-child or one-child case is simple
            else if (x.left == null) return x.right;
            else if (x.right == null) return x.left;

            else {
                Node temp_left = x.left;
                Node temp_right = x.right;
                x = min(temp_right);
                temp_right = delMin(temp_right); // 这个是依次用过节点回传回去,所以你必须设定起点等于delMin（起点）
                x.left = temp_left;
                x.right = temp_right;
            }
        }
        return x;
    }

    public static void main(String args[]) {

        BinarySearchTreeRecursive bst = new BinarySearchTreeRecursive();
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
        bst.delete(16);


        System.out.println("delmin: " + bst.delMin(bst.root));
        System.out.println("max " + bst.Max());
        System.out.println("get " + bst.get(6));
        System.out.println("root= " + bst.root.key);
        System.out.println("floor = " + bst.floor(15));

        System.out.println("size = " + bst.size(bst.root));
        System.out.println("rank = " + bst.rank(9));
        bst.inorder();

    }

}
