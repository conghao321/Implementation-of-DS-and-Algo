public class StackLinkedlist {

    private Node prev = null;
    private Node tail;
    private int size = 0;

    private class Node { //内部节点类
        Node next;
        int num;
    }

    public void push(int num) {//push的时候再新建个物件放进去
        size++;
        tail = new Node();
        tail.num = num;
        tail.next = prev;
        prev = tail;
    }

    public int pop() {
        size--;
        int num = tail.num;
        tail = tail.next;
        return num;
    }

    public int peek() {
        return tail.num;
    }

    public int size() {
        return size;
    }

    public void print() {
        while (tail != null) {
            System.out.println(tail.num);
            tail = tail.next;
        }
    }

    public static void main(String[] args) {

        StackLinkedlist s = new StackLinkedlist();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        s.push(6);
        s.pop();
        s.print();
        System.out.println(s.size());
    }

}
