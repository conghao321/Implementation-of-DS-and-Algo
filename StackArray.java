//重点是要resize！直接传入resize所需要的容量就好。。。。
public class StackArray {
    private int capacity; //stack 's capacity
    private int size;    //contents' size;
    private int[] s;

    public StackArray() {
        size = 0;  //一开始没有内容
        capacity = 1; //但一开始得有容量
        s = new int[capacity];

    }

    public void push(int num) {
        if (size == capacity) resize(2 * capacity);//如果发现：新的的size和容量相等必须扩容了
        System.out.println("size" + size);
        System.out.println("cap" + capacity);
        s[size] = num;
        size++;
    }

    public int pop() {
        int num = s[--size]; // size from 1 to 0
        if (size > 0 && size == capacity / 4) resize(capacity / 2); //如果发现 新的size变小后等于容量的四分之一也不行，必须缩绒，所小到二分之一
        // size 必须大于0；，对吧
        return num;
    }

    public int size() {

        return this.size;
    }

    public void print() {

    }

    public void resize(int capacity) { //拷贝容量就好，把 0~size的内容都拷贝到新的array里 就完事了。。。
        //size 必须大于0；
        int[] new_s = new int[capacity];
        for (int i = 0; i < size; i++) {
            new_s[i] = s[i];
        }
        s = new_s;
        System.out.println("bianhuan: " + capacity);
        this.capacity = capacity; //原本的容量替换为新的容量
    }

    public static void main(String[] args) {
        StackArray s = new StackArray();
        s.push(1);
        s.pop();
        s.push(2);
        s.push(3);
        s.push(3);
        s.pop();

        System.out.println(s.size);
        s.print();
    }

}
