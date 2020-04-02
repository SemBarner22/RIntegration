import Exceptions.EvaluatingException;

public class CheckedGreater extends AbstractBoolOperation1 {
    public CheckedGreater(TripleExpression firstExpression, TripleExpression secondExpression) throws EvaluatingException {
        super(firstExpression, secondExpression);
    }

    @Override
    protected int calculate(int x, int y) throws EvaluatingException {
        return 0;
    }
}
