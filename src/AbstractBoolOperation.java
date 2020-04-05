import Exceptions.EvaluatingException;
import Exceptions.TypeException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractBoolOperation extends TripleExpression {
    protected TripleExpression firstExpression, secondExpression;

    public List<Integer> evaluate(List<Integer> numbers) throws EvaluatingException {
        List<Integer> first = firstExpression.evaluate(numbers);
        List<Integer> second = secondExpression.evaluate(numbers);
        return calculate(first, second);
    }

    protected List<Integer> calculate(List<Integer> numbersFirst, List<Integer> numbersSecond) {
        List<Integer> result = new ArrayList<>();
        Iterator<Integer> iter1 = numbersFirst.listIterator(0);
        Iterator<Integer> iter2 = numbersSecond.listIterator(0);
        while (iter1.hasNext()) {
            result.add(count(iter1.next(), iter2.next()));
        }
        return result;
    }

    protected abstract Integer count(Integer integer, Integer integer1);
}