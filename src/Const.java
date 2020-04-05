import Exceptions.EvaluatingException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Const extends TripleExpression {
    private Number value;

    public Const(int value) {
        this.value = value;
        this.type = 0;
    }

    public List<Integer> evaluate(List<Integer> numbers) {
        return new ArrayList<>(Collections.nCopies(numbers.size(), value.intValue()));
    }


}
