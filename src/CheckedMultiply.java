import Exceptions.EvaluatingException;
import Exceptions.OverflowException;

public class CheckedMultiply extends AbstractBinaryOperation {
    public CheckedMultiply(TripleExpression firstExpression, TripleExpression secondExpression) throws EvaluatingException {
        super(firstExpression, secondExpression);
    }

    protected int calculate(int x, int y) {
        return x * y;
    }

    protected void check(int x, int y) throws EvaluatingException {
        if (x > 0 && y > 0 && Integer.MAX_VALUE / x < y) {
            throw new OverflowException("Multiply");
        }
        if (x > 0 && y < 0 && Integer.MIN_VALUE / x > y) {
            throw new OverflowException("Multiply");
        }
        if (x < 0 && y > 0 && Integer.MIN_VALUE / y > x) {
            throw new OverflowException("Multiply");
        }
        if (x < 0 && y < 0 && Integer.MAX_VALUE / x > y) {
            throw new OverflowException("Multiply");
        }
    }
}
