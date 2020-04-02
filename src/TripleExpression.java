import Exceptions.EvaluatingException;

public abstract class TripleExpression {
    int type;
    abstract int evaluate(int x, int y, int z) throws EvaluatingException;
    abstract int typeCheck() throws EvaluatingException;
}