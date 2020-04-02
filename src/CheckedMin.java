import Exceptions.EvaluatingException;

public class CheckedMin extends AbstractBinaryOperation {
    public CheckedMin(TripleExpression firstExpression, TripleExpression secondExpression) throws EvaluatingException {
        super(firstExpression, secondExpression);
    }

    protected int calculate(int x, int y) {
        if (x < y) {
            return x;
        } else {
            return y;
        }
    }

    @Override
    protected void check(int x, int y) {

    }

}
