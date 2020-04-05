import Exceptions.EvaluatingException;
import Exceptions.OverflowException;
import Exceptions.TypeException;

public class CheckedAdd extends AbstractBinaryOperation {
    public CheckedAdd(TripleExpression firstExpression, TripleExpression secondExpression)
            throws TypeException {
        super(firstExpression, secondExpression);
    }

    @Override
    protected Integer countThrow(Integer integer, Integer integer1) throws EvaluatingException {
        check(integer, integer1);
        return integer + integer1;
    }

    protected void check(int x, int y) throws EvaluatingException {
        if (x > 0 && Integer.MAX_VALUE - x < y) {
            throw new OverflowException("Add");
        }
        if (x < 0 && Integer.MIN_VALUE - x > y) {
            throw new OverflowException("Add");
        }
    }
}
