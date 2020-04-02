import Exceptions.EvaluatingException;
import Exceptions.ExtraOpenBracketException;
import Exceptions.ParsingException;
import Exceptions.UnknownIdentifierException;

public class ExpressionParser {
    StringParser stringParser;
    Token token;
    public String res;

    public int getIndex() {
        return stringParser != null ? stringParser.endIndex : -1;
    }

    public TripleExpression parse(String s, int index, Token token) throws ParsingException, EvaluatingException {
        stringParser = new StringParser(s, index, token, this);
        this.token = token;
        res = "";
        return orOperations();
    }

    private TripleExpression orOperations() throws ParsingException, EvaluatingException {
        TripleExpression current = andOperations();
        while (true) {
            if (stringParser.getToken() == Token.OR) {
//                if (token != Token.LOGICAL) {
//                    throw new ParsingException("Not Logical");
//                }
                current = new CheckedOr(current, andOperations());
            }
            return current;
        }
    }

    private TripleExpression andOperations() throws ParsingException, EvaluatingException {
        TripleExpression current = equalityOperations();
        while (true) {
            if (stringParser.getToken() == Token.AND) {
                current = new CheckedAnd(current, equalityOperations());
            } else {
                return current;
            }
        }
    }

    private TripleExpression equalityOperations() throws ParsingException, EvaluatingException {
        TripleExpression current = addOrSubtractOperations();
        while (true) {
            if (stringParser.getToken() == Token.GREATER) {
                current = new CheckedGreater(current, addOrSubtractOperations());
            } else if (stringParser.getToken() == Token.LESS) {
                current = new CheckedLess(current, addOrSubtractOperations());
            } else if (stringParser.getToken() == Token.EQUAL) {
                current = new CheckedEqual(current, addOrSubtractOperations());
            } else {
                return current;
            }
        }
    }

    private TripleExpression addOrSubtractOperations() throws ParsingException, EvaluatingException {
        TripleExpression current = multiplyOperations();
        while (true) {
            if (stringParser.getToken() == Token.ADD) {
                current = new CheckedAdd(current, multiplyOperations());
            } else if (stringParser.getToken() == Token.SUBTRACT) {
                current = new CheckedSubtract(current, multiplyOperations());
            } else {
                return current;
            }
        }
    }

    private TripleExpression multiplyOperations() throws ParsingException, EvaluatingException {
        TripleExpression current = otherOperations();
        while (true) {
            if (stringParser.getToken() == Token.MULTIPLY) {
                current = new CheckedMultiply(current, otherOperations());
            } else {
                return current;
            }
        }
    }

    private TripleExpression otherOperations() throws ParsingException, EvaluatingException {
        stringParser.next();
        TripleExpression current;
        if (stringParser.getToken() == Token.CONST) {
            current = new Const(stringParser.getValue());
            stringParser.next();
        } else if (stringParser.getToken() == Token.VARIABLE) {
            current = new Variable(stringParser.getName());
            stringParser.next();
        } else if (stringParser.getToken() == Token.OPENEDBRACKET) {
            current = orOperations();
            if (stringParser.getToken() != Token.CLOSEDBRACKET) {
                throw new ExtraOpenBracketException(stringParser.getIndex());
            }
            stringParser.next();
        } else if (stringParser.getToken() == Token.UNARYMINUS) {
            current = new CheckedNegate(otherOperations());
        } else {
            throw new UnknownIdentifierException(stringParser.getIndex());
        }
        return current;
    }

}
