import Exceptions.EvaluatingException;
import Exceptions.OverflowException;

public class CheckedNegate extends AbstractUnaryOperation {
    public CheckedNegate(TripleExpression x) {
        super(x);
    }

    protected int calculate(int x) {
        return -x;
    }

    protected void check(int x) throws EvaluatingException {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException("Negate");
        }
    }

    @Override
    int typeCheck() throws EvaluatingException {
        return 0;
    }
}
