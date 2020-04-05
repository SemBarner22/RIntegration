import Exceptions.EvaluatingException;
import Exceptions.OverflowException;

import java.util.ArrayList;
import java.util.List;

public class CheckedNegate extends AbstractUnaryOperation {
    public CheckedNegate(TripleExpression x) {
        super(x);
    }

    protected List<Integer> calculate(List<Integer> numbersFirst) throws EvaluatingException {
        List<Integer> result = new ArrayList<>();
        for (Integer integer : numbersFirst) {
            check(integer);
            result.add(integer * (-1));
        }
        return result;
    }

    protected void check(int x) throws EvaluatingException {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException("Negate");
        }
    }

}
