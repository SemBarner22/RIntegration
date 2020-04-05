import Exceptions.TypeException;

public class CheckedAnd extends AbstractBoolLogicalOperandsOperation {
    public CheckedAnd(TripleExpression firstExpression, TripleExpression secondExpression) throws TypeException {
        super(firstExpression, secondExpression);
    }

    @Override
    protected Integer count(Integer integer, Integer integer1) {
        return integer & integer1;
    }

}
