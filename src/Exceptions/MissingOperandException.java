package Exceptions;

public class MissingOperandException extends SyntaxException {
    public MissingOperandException(int index) {
        super("Missing operand on position " + index );
    }
}