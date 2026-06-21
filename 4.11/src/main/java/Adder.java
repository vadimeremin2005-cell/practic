import org.springframework.stereotype.Component;

@Component("adder")
public class Adder implements Calculator {
    @Override
    public double calculate(double a, double b) {
        return a + b;
    }
}
