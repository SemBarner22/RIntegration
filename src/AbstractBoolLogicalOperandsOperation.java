import Exceptions.EvaluatingException;
import Exceptions.TypeException;

import java.util.List;

public abstract class AbstractBoolLogicalOperandsOperation extends AbstractBoolOperation {

    public AbstractBoolLogicalOperandsOperation(TripleExpression firstExpression, TripleExpression secondExpression)
            throws TypeException {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.type = 1;
        typeCheck(firstExpression, secondExpression, 1, 1);
    }

    //Not used method
    protected Integer countThrow(Integer integer, Integer integer1) {
        return 0;
    }


}
