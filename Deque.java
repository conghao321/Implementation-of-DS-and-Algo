import java.util.Iterator;


// what is fucking Deque: you can add/delete in two sides.
public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int size;

    private class Node<Item> {
        Node prev;
        Node next;
        Item item;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> current;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

    }

    public Deque() {
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }                       // return the number of items on the deque

    public void addFirst(Item item) {
        if (item == null) throw new java.lang.IllegalArgumentException();
        if (isEmpty()) {
            size++;
            first = new Node<Item>();
            first.item = item;
            last = first;
        } else {
            size++;
            Node<Item> temp = new Node<Item>();
            temp.item = item;
            first.prev = temp;
            temp.next = first;
            first = first.prev;
        }

    }

    public void addLast(Item item) {
        if (item == null) throw new java.lang.IllegalArgumentException();
        if (isEmpty()) {
            size++;
            first = new Node<Item>();
            first.item = item;
            last = first;
        } else {
            size++;
            Node<Item> temp = new Node<Item>();
            temp.item = item;
            last.next = temp;
            temp.prev = last;
            last = last.next;
        }

    }

    public Item removeFirst() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        if (size == 1) {
            size--;
            Node<Item> temp = first;
            first.item = null;
            first.next = null;
            first.prev = null;
            last.next = null;
            last.prev = null;
            last.item = null;
            return temp.item;
        } else {
            size--;
            Node<Item> temp = first;
            first = first.next;
            first.prev = null;
            return temp.item;
        }
    }              // remove and return the item from the front

    public Item removeLast() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        if (size == 1) {
            size--;
            Node<Item> temp = last;
            first.item = null;
            first.next = null;
            first.prev = null;
            last.next = null;
            last.prev = null;
            last.item = null;
            return temp.item;
        } else {
            size--;
            Node<Item> temp = last;
            last = last.prev;
            last.next = null;
            return temp.item;
        }

    }                // remove and return the item from the end

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    public static void main(String[] args) {
        Deque<Integer> d = new Deque<>();

        d.addLast(1);
        System.out.println(d.size());
        System.out.println(d.isEmpty());
        d.removeFirst();
        System.out.println(d.size());
        System.out.println(d.isEmpty());

    }
}
