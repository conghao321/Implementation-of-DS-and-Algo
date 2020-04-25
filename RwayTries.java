public class RwayTries<T> {

    private int R = 256;  // ascii code!
    Node root = new Node();

    private class Node<T> {
        // 内部节点
        Node[] next = new Node[R];
        T value;
    }


    public void put(String s, T item) {
        //string : abcd
        root = put(root, s, item, 0);

    }

    private Node put(Node x, String s, T value, int d) {

        if (x == null) x = new Node();

        if (d == s.length()) {
            x.value = value;
            return x;
        }

        char c = s.charAt(d);
        // 下一个字符在256个格子中的哪一个
        x.next[c] = put(x.next[c], s, value, d + 1);

        return x;

    }


    public T get(String s) {

        Node x = get(root, s, 0);

        if (x == null)
            return null;

        return (T) x.value;
    }

    private Node get(Node x, String s, int d) {

        while (d <= s.length()) {

            if (d == s.length()) return x;

            char c = s.charAt(d);
            if (x.next[c] == null) return null;

            x = x.next[c];
            d = d + 1;
        }

        return x;
    }

    public static void main(String args[]) {
        RwayTries<Integer> rwt = new RwayTries<>();

        rwt.put("laoda", 250);
        rwt.put("louzi", 666251);
        rwt.put("wudi", 252);
        rwt.put("conghao", 234253);
        rwt.put("sunzhan", 254);
        rwt.put("shouwangxianfeng", 255);
        rwt.put("jiligulu", 257);

        System.out.println(rwt.get("louzi"));
        System.out.println(rwt.get("sunzhan"));
        System.out.println(rwt.get("shouwangxianfeng"));


    }

}
