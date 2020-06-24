/*This is a vector array in Java version
However, in java, you should pick ArrayList in most cases
*/
public class VectorArray<T> {
    int size;
    int capacity;
    T[] vec;

    public VectorArray() {
        size = 0;
        capacity = 1;
        vec = (T[]) new Object[capacity];
    }

    public VectorArray(T[] A) {
        size = A.length;
        while (capacity < size) capacity = 2 * capacity; // 别忘了扩容
        vec = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) vec[i] = A[i];
    }


    public T[] array() {
        return vec;
    }

}
