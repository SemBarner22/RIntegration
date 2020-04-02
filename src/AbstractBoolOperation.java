import Exceptions.EvaluatingException;

public abstract class AbstractBoolOperation extends TripleExpression {
    private TripleExpression firstExpression, secondExpression;

    public AbstractBoolOperation(TripleExpression firstExpression, TripleExpression secondExpression) throws EvaluatingException {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.type = 1;
        typeCheck();
    }

    @Override
    int typeCheck() throws EvaluatingException {
        if (firstExpression.type == 1 && secondExpression.type == 1) {
            return 1;
        } else {
            throw new EvaluatingException("type wrong");
        }
    }

    protected abstract int calculate(int x, int y) throws EvaluatingException;

    public int evaluate(int x, int y, int z) throws EvaluatingException {
        int first = firstExpression.evaluate(x, y, z);
        int second = secondExpression.evaluate(x, y, z);
        check(first, second);
        return calculate(first, second);
    }

    protected void check(int x, int y) throws EvaluatingException {

    }
}
