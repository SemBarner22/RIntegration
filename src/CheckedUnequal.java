import Exceptions.EvaluatingException;

public class CheckedUnequal extends AbstractBoolOperation {
    public CheckedUnequal(TripleExpression firstExpression, TripleExpression secondExpression) throws EvaluatingException {
        super(firstExpression, secondExpression);
    }

    @Override
    protected int calculate(int x, int y) throws EvaluatingException {
        return 0;
    }
}
