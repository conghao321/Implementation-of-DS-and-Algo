public class QuickSort {

    private static void swap(int[] A,int i,int j){
        int temp=A[i];
        A[i]=A[j];
        A[j]=temp;
    }
    private static void  QuickSort(int[]A,int lo,int hi){

        if(lo>=hi) return;

        int i=lo,j=hi;
        int key=A[lo];
        while(i<j){
            /*
            * 我们必须从右边开始，也就是从基准数的对面开始。
            * 我们必须从右边开始，也就是从基准数的对面开始。
            * 我们必须从右边开始，也就是从基准数的对面开始。
            * 我们必须从右边开始，也就是从基准数的对面开始。
            * 我们必须从右边开始，也就是从基准数的对面开始。
            * 我们必须从右边开始，也就是从基准数的对面开始。
            * 我们必须从右边开始，也就是从基准数的对面开始。
            * */
            while(i<j&&A[j]>=key)
                j--;
            while(i<j&&A[i]<=key)
                i++;

            swap(A,i,j);
        }
        swap(A,lo,i);
        QuickSort(A,lo,i-1);
        QuickSort(A,i+1,hi);
    }


    public static void main(String[] args) {

        int [] arr=new int[]{3,45,78,64,52,11,64,55,99,11,18};
        QuickSort(arr,0,arr.length-1);

        for (int x:arr){
            System.out.print(x+" ");
        }
    }
}
