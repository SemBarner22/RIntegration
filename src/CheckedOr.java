import Exceptions.EvaluatingException;

public class CheckedOr extends AbstractBoolOperation {
    public CheckedOr(TripleExpression firstExpression, TripleExpression secondExpression) throws EvaluatingException {
        super(firstExpression, secondExpression);
    }

    @Override
    protected int calculate(int x, int y) throws EvaluatingException {
        return 0;
    }
}
