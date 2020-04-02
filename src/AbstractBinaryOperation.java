import Exceptions.EvaluatingException;

public abstract class AbstractBinaryOperation extends TripleExpression {

    private TripleExpression firstExpression, secondExpression;

    public AbstractBinaryOperation(TripleExpression firstExpression, TripleExpression secondExpression) throws EvaluatingException {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.type = 0;
        typeCheck();
    }

    @Override
    int typeCheck() throws EvaluatingException {
        if (firstExpression.type == 0 && secondExpression.type == 0) {
            return 0;
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

    protected abstract void check(int x, int y) throws EvaluatingException;

}
