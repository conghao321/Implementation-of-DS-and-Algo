public class Heap {
    private int size;
    int[] heap;

    public Heap() {
        size = 0;
        heap = new int[100]; // using a vector to construct the heap.
        // index from 1~to 99, we do not use 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void insert(int item) {  //insert item to tail, and swim it up
        int k = ++size;
        heap[size] = item;
        swim(k);
    }

    private void swim(int k) {
        while (heap[k] > heap[k / 2] && (k / 2) > 0) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    public int delMax() {   // return heap[1] is the max
        int item = heap[1];
        exch(1, size--);    // exchange heap[1] and tail , then sink it (re-build heap order)
        sink(1);
        heap[size + 1] = 0;  // for integers 0 means null.
        return item;
    }

    private void sink(int k) {   // sink the item to use its larger child to replace this item itself.
        while (2 * k <= size) {
            int j = 2 * k;
            if (j + 1 <= size && heap[j] < heap[j + 1]) j++;  //do not forget j+1 <= size;
            if (heap[k] > heap[j]) break;
            exch(k, j);
            k = j;
        }
    }

    private void exch(int a, int b) {
        int temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    public void print() {
        for (int i = 1; i <= size; i++)
            System.out.println(heap[i]);

    }

    public static void main(String[] args) {

        Heap heap = new Heap();
        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        heap.insert(4);
        heap.insert(5);
        heap.insert(6);
        heap.insert(7);
        //heap.print();

        heap.delMax();
        heap.print();
    }

}
