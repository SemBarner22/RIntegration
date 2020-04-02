import Exceptions.EvaluatingException;
import Exceptions.ParsingException;
import Exceptions.UnknownIdentifierException;

public class Parser1 {
    public static String filter = "filter{";
    public static String map = "%>%map{";
    public static String afterLastMap = "element";
    public static boolean wasFilter = false;
    // probably no filter or map;
    public static String parse(String string) throws ParsingException, EvaluatingException {
        int index = 0;
        Token token = Token.BEGIN;
        // string can be empty;
        while (index < string.length()) {
            if (token == Token.BEGIN && index + 4 < string.length() && string.substring(index, index + 4).equals("map{")) {
                index += 4;
                index = parseExpression(string, index, Token.ARITHMETIC) + 1;
                token = Token.END;
            } else if (token == Token.BEGIN && index + 7 < string.length() && string.substring(index, index + 7).equals("filter{")) {
                index += 7;
                index = parseExpression(string, index, Token.LOGICAL) + 1;
                token = Token.END;
            } else if (token == Token.END && index + 3 < string.length() && string.substring(index, index + 3).equals("%>%")) {
                index += 3;
                token = Token.BEGIN;
            } else throw new UnknownIdentifierException(index);
        }
        return filter + "}" + map + afterLastMap + "}";
    }

    private static int parseExpression(String string, int index, Token token) throws ParsingException, EvaluatingException {
        //new StringParser(string, index, arithmetic).next();
        ExpressionParser expressionParser = new ExpressionParser();
        TripleExpression expression = expressionParser.parse(string, index, token);
        if (expression.type == 0 && token != Token.ARITHMETIC || expression.type == 1 && token != Token.LOGICAL) {
            throw new ParsingException("pls help");
        }
        if (token == Token.LOGICAL) {
            if (wasFilter) {
                filter += "&" + expressionParser.res;
            } else {
                filter += expressionParser.res;
                wasFilter = true;
            }
        } else {
            afterLastMap = expressionParser.res;
        }
        return expressionParser.getIndex();
    }
}
