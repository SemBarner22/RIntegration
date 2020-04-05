import Exceptions.EvaluatingException;

import java.util.List;

public abstract class AbstractUnaryOperation extends TripleExpression {
    private TripleExpression expression;

    public AbstractUnaryOperation(TripleExpression expression) {
        this.expression = expression;
        this.type = 0;
    }

    protected abstract List<Integer> calculate(List<Integer> numbersFirst) throws EvaluatingException;

    public List<Integer> evaluate(List<Integer> numbers) throws EvaluatingException {
        List<Integer> evaluated = expression.evaluate(numbers);
        return calculate(evaluated);
    }

    protected abstract void check(int x) throws EvaluatingException;

}
