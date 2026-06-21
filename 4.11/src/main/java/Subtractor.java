import org.springframework.stereotype.Component;

@Component("subtractor")
public class Subtractor implements Calculator {
    @Override
    public double calculate(double a, double b) {
        return a - b;
    }
}
