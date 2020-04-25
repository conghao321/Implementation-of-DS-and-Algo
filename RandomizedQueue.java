import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int head;// head out tail in   <<<<head - - - - - - - tail <<<<<<
    private int tail;
    private int size;
    private Item[] rq;
    private boolean full;
//  循环队列天下无敌

    public RandomizedQueue() {
        Item[] rq = (Item[]) new Object[1];
        head = 0;
        tail = 0;
        size = 0;
        full = false;
    }              // construct an empty randomized queue

    public boolean isEmpty() {
        return size == 0;
    }                 // is the randomized queue empty?

    public int size() {
        return size;
    }                      // return the number of items on the randomized queue

    public void enqueue(Item item) {
        size++;
        if (size == rq.length) resize(2 * rq.length);
        rq[tail % rq.length] = item;
        tail++;

    }          // add the item

    public Item dequeue() {
        Item item = rq[head % rq.length];
        size--;
        if (size > 0 && size == rq.length / 4) resize(rq.length / 2);
        rq[head % rq.length] = null;
        head++;
        return item;
    }

    //public Item sample()  {}                   // return a random item (but do not remove it)

    public Iterator<Item> iterator() {
        return iterator();
    }      // return an independent iterator over items in random order


    public void resize(int capacity) {
        //size 必须大于0；
        Item[] new_s = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            new_s[i] = rq[(head + i) % rq.length]; // (sum==7) - - tail(2) _ head(4) - -
        }
        rq = new_s;
    }

    public static void main(String[] args) {

    }   // unit testing (optional)
}
