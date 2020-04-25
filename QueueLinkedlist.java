public class QueueLinkedlist<T> {
    Node first, last;

    private class Node<T> { //内部节点类
        Node next;
        T item;
    }

    QueueLinkedlist() {
        first = last = new Node();
    }

    public boolean isEmpty() {
        return (first == last);
    }

    public void enqueue(T item) {

        Node<T> temp = new Node<>();
        temp.item = item;

        if (isEmpty()) {
            //System.out.println("enqueue");
            last = temp;
            first.next = temp;
        } else {
            last.next = temp;
            last = last.next;
            //System.out.println("enqueue");
        }
    }


    public T dequeue() {

        if (first.next == last) {
            T item = (T) last.item;
            first = first.next;
            return item;
        } else {

            Node<T> temp = first.next;
            first.next = temp.next;
            T item = temp.item;
            return item;
        }

    }


    public void print() {
        Node ptr = first;
        while (ptr.next != null) {
            ptr = ptr.next;
            System.out.println(ptr.item);
        }
    }

    public static void main(String[] args) {

        QueueLinkedlist<Integer> q = new QueueLinkedlist<>();
        System.out.println(1 / 2);

    }

}
