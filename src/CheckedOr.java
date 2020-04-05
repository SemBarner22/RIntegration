import Exceptions.TypeException;


public class CheckedOr extends AbstractBoolLogicalOperandsOperation {
    public CheckedOr(TripleExpression firstExpression, TripleExpression secondExpression) throws TypeException {
        super(firstExpression, secondExpression);
    }

    @Override
    protected Integer count(Integer integer, Integer integer1) {
        return integer | integer1;
    }
}
