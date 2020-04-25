public class HashbyChain<Key, Val> {
    Node[] map;
    int M;

    private class Node<Key, Val> {
        Node next;
        Key key;
        Val val;

        public Node(Key k, Val v, Node next) {
            this.key = k;
            this.val = v;
            this.next = next;
        }
    }

    public HashbyChain(int N) {
        M = N / 5;  // 桶的数量
        map = new Node[M]; //M的值约为N的五分之一
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;//确保返回的是一个正整数
    }


    public void put(Key key, Val val) {
        int i = hash(key);
        Node temp = map[i];
        while (temp != null) {
            if (temp.key.equals(key)) { // 用equals 是去比较对象的内容，而==是比较引用
                temp.val = val;
                return;
            }
            temp = temp.next;
        }
        map[i] = new Node(key, val, map[i]);
    }

    public Val get(Key key) {
        int i = hash(key);
        Node temp = map[i];
        Val val = null;
        while (temp != null) {
            if (temp.key.equals(key)) {
                val = (Val) temp.val;
                break;
            }// 用equals 是去比较对象的内容，而==是比较引用
            temp = temp.next;
        }

        return val;
    }

    public static void main(String args[]) {
        HashbyChain<String, String> map = new HashbyChain<>(50);
        map.put("sb1", "laoda");
        map.put("sb2", "louzi");
        map.put("sb3", "gejun");
        map.put("sb4", "sunzhan");
        map.put("sb5", "wudi");
        map.put("dad", "ch");
        System.out.println(map.get("dad"));
    }

}
