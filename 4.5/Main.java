import java.util.stream.IntStream;

public class Main {
    public static long getArithmeticProgressionSum(int a, int b) {
        return IntStream.range(a, b).asLongStream().sum();
    }

    public static void main(String[] args) {
        System.out.println(getArithmeticProgressionSum(10_000_000, 1_000_000_000));
    }
}
