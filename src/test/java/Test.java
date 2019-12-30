import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        func(10);
        System.out.println(func(10));
    }

    public static ArrayList func(Integer x) {
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 1; i < x + 1; i++) {
            if (x % i == 0) {
                array.add(i);
            }
        }
        return array;
    }
}