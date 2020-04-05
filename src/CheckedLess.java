import Exceptions.TypeException;

public class CheckedLess extends AbstractBoolArithmeticOperandsOperation {
    public CheckedLess(TripleExpression firstExpression, TripleExpression secondExpression) throws TypeException {
        super(firstExpression, secondExpression);
    }

    @Override
    protected Integer count(Integer i, Integer i1) {
        return i < i1 ? 1 : 0;
    }
}
