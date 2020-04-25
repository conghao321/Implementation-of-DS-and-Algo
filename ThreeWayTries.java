public class ThreeWayTries<T> {

    private int R = 256;  // ascii code!
    Node root = new Node();

    private class Node<T> {
        // 内部节点
        Node left;
        Node mid;
        Node right;
        char c;
        T value;
    }


    public void put(String s, T item) {
        //string : abcd


        root = put(root, s, item, 0);

    }

    private Node put(Node x, String s, T value, int d) {
        char c = s.charAt(d);

        if (x == null) {
            x = new Node();
            x.c = c;
        }

        if (c < x.c) x.left = put(x.left, s, value, d);
        else if (c > x.c) x.right = put(x.right, s, value, d);
        else if (d < s.length() - 1) x.mid = put(x.mid, s, value, d + 1);
        else {
            x.value = value;
        }

        return x;
    }


    public T get(String s) {

        Node x = get(root, s, 0);

        if (x == null)
            return null;

        return (T) x.value;
    }

    private Node get(Node x, String s, int d) {

        while (x != null) {
            char c = s.charAt(d);
            if (d == s.length() - 1) return x;

            if (c < x.c) x = x.left;
            else if (c > x.c) x = x.right;
            else if (d < s.length() - 1) {
                x = x.mid;
                d = d + 1;
            }
        }
        return x;
    }

    public static void main(String args[]) {
        ThreeWayTries<Integer> rwt = new ThreeWayTries<>();

        rwt.put("laoda", 250);
        rwt.put("louzi", 666251);
        rwt.put("wudi", 252);
        rwt.put("conghao", 234253);
        rwt.put("sunzhan", 254);
        rwt.put("shouwangxianfeng", 255);
        rwt.put("jiligulu", 257);

        System.out.println(rwt.get("louzi"));
        System.out.println(rwt.get("sunzhanhuol"));
        System.out.println(rwt.get("shouwangxianfeng"));
        System.out.println(rwt.get("jiligulu"));
        System.out.println(rwt.get("conghao"));
        System.out.println(rwt.get("wudi"));


    }

}
