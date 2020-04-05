import Exceptions.TypeException;

public class CheckedEqual extends AbstractBoolArithmeticOperandsOperation {
    public CheckedEqual(TripleExpression firstExpression, TripleExpression secondExpression) throws TypeException {
        super(firstExpression, secondExpression);
    }

    @Override
    protected Integer count(Integer i, Integer i1) {
        return i.equals(i1) ? 1 : 0;
    }
}
