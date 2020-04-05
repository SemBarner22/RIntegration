package Exceptions;

public class MissingOperationException extends SyntaxException {
    public MissingOperationException(int index) {
        super("Missing operation " + index);
    }
}