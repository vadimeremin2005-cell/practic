import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private final Map<String, Calculator> calculators;

    public Application(Map<String, Calculator> calculators) {
        this.calculators = calculators;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите число a");
        double a = scanner.nextDouble();

        System.out.println("Введите число b");
        double b = scanner.nextDouble();

        System.out.println("Введите тип операции: [adder, divider, multiplier, subtractor]");
        String type = scanner.next();

        System.out.println(calculators.get(type).calculate(a, b));
    }
}
