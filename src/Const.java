import Exceptions.EvaluatingException;

public class Const extends TripleExpression {
    private Number value;

    public Const(int value) {
        this.value = value;
        this.type = 0;
    }

    public int evaluate(int x, int y, int z) {
        return value.intValue();
    }

    @Override
    int typeCheck() throws EvaluatingException {
        return 0;
    }

}
