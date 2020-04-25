import java.util.Comparator;

public class Problem {

    public static void main(String[] args) {
        Comparator<String> comp=new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        };
    }
}
