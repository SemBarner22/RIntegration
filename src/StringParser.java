import Exceptions.*;

public class StringParser {
    private ExpressionParser expressionParser;
    private Token token;
    private int value;
    private int index;
    private String expression;
    private int balance;
    int endIndex;

    public StringParser(String expression, int index, Token token, ExpressionParser expressionParser) {
        this.expressionParser = expressionParser;
        this.token = token;
        this.index = index;
        balance = 0;
        this.expression = expression;
    }

    public StringParser(String expression, int index, ExpressionParser expressionParser) {
        this.expressionParser = expressionParser;
        this.index = index;
        balance = 0;
        this.expression = expression;
    }

    public Token getToken() {
        return token;
    }

    public int getIndex() {
        return index;
    }

    public int getValue() {
        return value;
    }

    protected void next() throws SyntaxException {
        while (index < expression.length() && Character.isWhitespace(expression.charAt(index))) {
            index++;
        }
        if (index == expression.length()) {
            if (balance > 0) {
                throw new ExtraOpenBracketException(index);
            }
            token = Token.END;
            return;
        }
        char symbol = expression.charAt(index);
        if (symbol == '}') {
            if (balance > 0) {
                throw new ExtraOpenBracketException(index);
            }
            endIndex = index;
            token = Token.END;
            return;
        }
        if (symbol == '+') {
            checkForOperand(index);
            token = Token.ADD;
            expressionParser.res += "+";
        } else if (symbol == '-') {
            if (token == Token.CONST || token == Token.VARIABLE || token == Token.CLOSEDBRACKET) {
                token = Token.SUBTRACT;
                expressionParser.res += "-";
            } else {
                index++;
                if (index == expression.length()) {
                    throw new MissingOperandException(index);
                }
                while (index < expression.length() && Character.isWhitespace(expression.charAt(index))) {
                    index++;
                }
                if (Character.isDigit(expression.charAt(index))) {
                    int beginOfNumber = index;
                    while (index < expression.length() && Character.isDigit(expression.charAt(index))) {
                        index++;
                    }
                    int endOfNumber = index;
                    String stringNumber = expression.substring(beginOfNumber, endOfNumber);
                    try {
                        value = Integer.parseInt("-" + stringNumber);
                        expressionParser.res += "-" + stringNumber;
                    } catch (NumberFormatException e) {
                        throw new IllegalConstantException("-" + stringNumber, index);
                    }
                    token = Token.CONST;
                } else {
                    token = Token.UNARYMINUS;
                    expressionParser.res += "-";
                }
                index--;
            }
        } else if (symbol == '*') {
            checkForOperand(index);
            token = Token.MULTIPLY;
            expressionParser.res += "*";
        } else if (Character.isDigit(symbol)) {
            checkForOperation(index);
            int left = index;
            while (index < expression.length() && Character.isDigit(expression.charAt(index))) {
                index++;
            }
            int right = index;
            value = Integer.parseInt(expression.substring(left, right));
            expressionParser.res += expression.substring(left, right);
            token = Token.CONST;
            index--;
        } else if (symbol == 'e' && index + 7 < expression.length()
                && expression.substring(index, index + 7).equals("element")) {
            token = Token.VARIABLE;
            index += 6;
            expressionParser.res += Parser.afterLastMap;
        } else if (symbol == '(') {
            checkForOperation(index);
            balance++;
            token = Token.OPENEDBRACKET;
            expressionParser.res += "(";
        } else if (symbol == '>') {
            expressionParser.res += ">";
            checkForOperand(index);
            token = Token.GREATER;
        } else if (symbol == '<') {
            expressionParser.res += "<";
            checkForOperand(index);
            token = Token.LESS;
        } else if (symbol == '=') {
            expressionParser.res += "=";
            checkForOperand(index);
            token = Token.EQUAL;
        } else if (symbol == '|') {
            expressionParser.res += "|";
            checkForOperand(index);
            token = Token.OR;
        } else if (symbol == '&') {
            expressionParser.res += "&";
            checkForOperand(index);
            token = Token.AND;
        } else if (symbol == ')') {
            expressionParser.res += ")";
            balance--;
            token = Token.CLOSEDBRACKET;
            if (balance < 0) {
                throw new ExtraCloseBracketException(index);
            }
        } else {
            throw new UnknownIdentifierException(index);
        }
        index++;
    }

    private void checkForOperand(int index) throws MissingOperandException {
        if (token != Token.CONST && token != Token.VARIABLE && token != Token.CLOSEDBRACKET) {
            throw new MissingOperandException(index);
        }
    }

    private void checkForOperation(int index) throws MissingOperationException {
        if (token == Token.CLOSEDBRACKET || token == Token.VARIABLE || token == Token.CONST) {
            throw new MissingOperationException(index);
        }
    }
}