package Exceptions;

public class IllegalConstantException extends SyntaxException {
    public IllegalConstantException(String reason, int index) {
        super("Constant '" + reason + "' on position " + index + " is unsuitable for int");
    }
}