public class HeapSort {
    private int size; // size of elements when building a new heap.
    int[] heap;
    private int n; // number of elements to compare

    public HeapSort() {
        size = 0;
        n = 0;
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
        for (int i = 1; i <= n; i++)
            System.out.println(heap[i]);

    }

    public void sort() { // instead of delMax!
        n = size;
        while (size > 0) {
            exch(size--, 1); // after an exchange, size should --, but n do not change
            sink(1);
        }
    }

    public static void main(String[] args) {

        HeapSort heapsort = new HeapSort();
        heapsort.insert(3);
        heapsort.insert(5);
        heapsort.insert(6);
        heapsort.insert(4);
        heapsort.insert(7);
        heapsort.insert(2);
        heapsort.insert(1);
        //heap.print();

        System.out.println("size = " + heapsort.size);
        heapsort.sort();
        System.out.println("n = " + heapsort.n);
        heapsort.print();
    }

}
