import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите число a");
        double a = scanner.nextDouble();

        System.out.println("Введите число b");
        double b = scanner.nextDouble();

        Multiplier multiplier = new Multiplier();
        Divider divider = new Divider();

        System.out.println("Результат сложения a и b: " + (a + b));
        System.out.println("Результат вычитания a и b: " + (a - b));
        System.out.println("Результат умножения a и b: " + multiplier.multiply(a, b));
        System.out.println("Результат деления a и b: " + divider.divide(a, b));
    }
}
