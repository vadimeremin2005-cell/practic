import org.springframework.stereotype.Component;

@Component("multiplier")
public class Multiplier implements Calculator {
    @Override
    public double calculate(double a, double b) {
        return a * b;
    }
}
