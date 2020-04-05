package Exceptions;

public class UnknownIdentifierException extends SyntaxException {
    public UnknownIdentifierException(int index) {
        super("Unknown identifier on position " + index);
    }
}