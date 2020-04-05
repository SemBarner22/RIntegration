import Exceptions.*;

import java.util.List;

public class ExpressionParser {
    StringParser stringParser;
    Token token;
    public String res;

    public int getIndex() {
        return stringParser != null ? stringParser.endIndex : -1;
    }

    public TripleExpression parse(String s, int index, Token token)
            throws SyntaxException, EvaluatingException, TypeException {
        stringParser = new StringParser(s, index, token, this);
        this.token = token;
        res = "";
        return orOperations();
    }

    public List<Integer> parse(String s, int index, List<Integer> n)
            throws SyntaxException, EvaluatingException, TypeException {
        stringParser = new StringParser(s, index,this);
        res = "";
        return orOperations().evaluate(n);
    }

    private TripleExpression orOperations() throws SyntaxException, EvaluatingException, TypeException {
        TripleExpression current = andOperations();
        while (true) {
            if (stringParser.getToken() == Token.OR) {
                current = new CheckedOr(current, andOperations());
            }
            return current;
        }
    }

    private TripleExpression andOperations() throws SyntaxException, EvaluatingException, TypeException {
        TripleExpression current = equalityOperations();
        while (true) {
            if (stringParser.getToken() == Token.AND) {
                current = new CheckedAnd(current, equalityOperations());
            } else {
                return current;
            }
        }
    }

    private TripleExpression equalityOperations() throws SyntaxException, EvaluatingException, TypeException {
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

    private TripleExpression addOrSubtractOperations() throws SyntaxException, EvaluatingException, TypeException {
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

    private TripleExpression multiplyOperations() throws SyntaxException, EvaluatingException, TypeException {
        TripleExpression current = otherOperations();
        while (true) {
            if (stringParser.getToken() == Token.MULTIPLY) {
                current = new CheckedMultiply(current, otherOperations());
            } else {
                return current;
            }
        }
    }

    private TripleExpression otherOperations() throws SyntaxException, EvaluatingException, TypeException {
        stringParser.next();
        TripleExpression current;
        if (stringParser.getToken() == Token.CONST) {
            current = new Const(stringParser.getValue());
            stringParser.next();
        } else if (stringParser.getToken() == Token.VARIABLE) {
            current = new Variable();
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
