import Exceptions.EvaluatingException;

public class CheckedLess extends AbstractBoolOperation1 {
    public CheckedLess(TripleExpression firstExpression, TripleExpression secondExpression) throws EvaluatingException {
        super(firstExpression, secondExpression);
    }

    @Override
    protected int calculate(int x, int y) throws EvaluatingException {
        return 0;
    }
}
