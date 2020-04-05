import Exceptions.EvaluatingException;
import Exceptions.TypeException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractBinaryOperation extends TripleExpression {

    private TripleExpression firstExpression, secondExpression;

    public AbstractBinaryOperation(TripleExpression firstExpression,
                                   TripleExpression secondExpression) throws TypeException {
        this.firstExpression = firstExpression;
        this.secondExpression = secondExpression;
        this.type = 0;
        typeCheck(firstExpression, secondExpression, 0, 0);
    }

    protected List<Integer> calculateThrow(List<Integer> numbersFirst, List<Integer> numbersSecond)
            throws EvaluatingException{
        List<Integer> result = new ArrayList<>();
        Iterator<Integer> iter1 = numbersFirst.listIterator(0);
        Iterator<Integer> iter2 = numbersSecond.listIterator(0);
        while (iter1.hasNext()) {
                result.add(countThrow(iter1.next(), iter2.next()));
        }
        return result;
    }

    protected abstract Integer countThrow(Integer integer, Integer integer1) throws EvaluatingException;

    public List<Integer> evaluate(List<Integer> numbers) throws EvaluatingException {
        List<Integer> first = firstExpression.evaluate(numbers);
        List<Integer> second = secondExpression.evaluate(numbers);
        return calculateThrow(first, second);
    }
}
