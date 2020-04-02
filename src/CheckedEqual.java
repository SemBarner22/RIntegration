import Exceptions.EvaluatingException;

public class CheckedEqual extends AbstractBoolOperation1 {
    public CheckedEqual(TripleExpression firstExpression, TripleExpression secondExpression) throws EvaluatingException {
        super(firstExpression, secondExpression);
    }

    @Override
    protected int calculate(int x, int y) throws EvaluatingException {
        return 0;
    }
}
