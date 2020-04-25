import java.util.Comparator;
import java.util.Iterator;

public class Bag<T> implements Iterable<T> {
    private Node last;
    private Node first;
    private int size;

    private class Node { //内部节点类
        Node next;
        T item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T item) { // adding
        Node newLast = new Node();
        newLast.item = item;

        if (isEmpty()) {    //加入的时候空和非空链表做法不一样额兄弟！
            last = newLast;
            first = last;
        } else {
            last.next = newLast;
            last = last.next;
        }

        size++;
    }


    public Iterator<T> iterator() {
        return new BagIterator();
    }


    private class BagIterator implements Iterator<T> {
        private Node current = first;

        public boolean hasNext() { // 必须要确定第一个有没有，所以就是current=first！=null, 不是current.next
            return current != null;
        }

        public T next() {
            T item = current.item;
            current = current.next;   //在这里我们才实现迭代进入下一个
            return item;
        }

    }

    public static void main(String[] args) {

        Bag<Integer> bag = new Bag<>();
        bag.add(1);
        bag.add(2);
        bag.add(4);
        bag.add(3);
        bag.add(5);
        bag.add(6);
        for (int x : bag) {
            System.out.println(x);
        }

        Comparator<Integer> comp=new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        };
        compPP cca=new compPP();
        int []nums=new int[5];

    }

}
class compPP implements  Comparator<Integer>{

    @Override
    public int compare(Integer o1, Integer o2) {
        return 0;
    }
}
