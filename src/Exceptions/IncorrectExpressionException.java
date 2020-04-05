package Exceptions;

public class IncorrectExpressionException extends SyntaxException {
    public IncorrectExpressionException(String s) {
        super("Incorrect expression on posititon " + s);
    }
}
