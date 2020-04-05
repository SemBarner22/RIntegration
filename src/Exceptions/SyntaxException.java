package Exceptions;

public class SyntaxException extends Exception {
    public SyntaxException(String s) {
        super("SYNTAX ERROR");
    }
}