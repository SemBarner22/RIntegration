package Exceptions;

public class ExtraCloseBracketException extends SyntaxException {
    public ExtraCloseBracketException(int index) {
        super("Extra closing bracket on position " + index);
    }
}