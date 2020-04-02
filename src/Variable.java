import Exceptions.EvaluatingException;

public class Variable extends TripleExpression {
    private String name;

    public Variable(String name) {
        this.name = name;
        this.type = 0;
    }

    public int evaluate(int x, int y, int z) {
        if (name.equals("x")) {
            return x;
        }
        if (name.equals("y")) {
            return y;
        }
        if (name.equals("z")) {
            return z;
        }
        return 0;
    }

    @Override
    int typeCheck() throws EvaluatingException {
        return 0;
    }

}
