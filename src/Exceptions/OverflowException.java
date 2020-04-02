package Exceptions;

public class OverflowException extends EvaluatingException {
    public OverflowException(String operation) {
        super("Overflow exception on " + operation);
    }
}