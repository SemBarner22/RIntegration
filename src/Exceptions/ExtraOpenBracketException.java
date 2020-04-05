package Exceptions;

public class ExtraOpenBracketException extends SyntaxException {
    public ExtraOpenBracketException(int index) {
        super("Extra opening bracket on position " + index);
    }
}