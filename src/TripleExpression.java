import Exceptions.EvaluatingException;
import Exceptions.TypeException;

import java.util.ArrayList;
import java.util.List;

public abstract class TripleExpression {
    int type;
    abstract List<Integer> evaluate(List<Integer> numbers) throws EvaluatingException;
    int typeCheck(TripleExpression firstExpression, TripleExpression secondExpression,
                  int type, int resultType) throws TypeException {
        if (firstExpression.type == type && secondExpression.type == type) {
            return resultType;
        } else {
            throw new TypeException("TYPE ERROR");
        }
    }

}