import Exceptions.EvaluatingException;
import Exceptions.SyntaxException;
import Exceptions.TypeException;
import Exceptions.UnknownIdentifierException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Parser {
    public static String filter = "filter{";
    public static String map = "%>%map{";
    public static String afterLastMap = "element";
    public static boolean wasFilter = false;
    private static int mapFrom;

    public static List<Integer> parseAndApply(String string, List<Integer> numbers)
            throws SyntaxException, EvaluatingException, TypeException {
        List<Integer> result = new ArrayList<>();
        string = parse(string);
        ExpressionParser expressionParser = new ExpressionParser();
        List<Integer> res = filter(string, numbers, expressionParser);
        Iterator<Integer> iter1 = res.listIterator(0);
        Iterator<Integer> iter2 = numbers.listIterator(0);
        while (iter1.hasNext()) {
            Integer next = iter2.next();
            if (iter1.next() == 1) {
                result.add(next);
            }
        }
        return map(string, result, expressionParser);
    }

    private static List<Integer> map(String s, List<Integer> n, ExpressionParser expressionParser)
            throws SyntaxException, EvaluatingException, TypeException {
        return expressionParser.parse(s, mapFrom, n);
    }

    private static List<Integer> filter(String s, List<Integer> n, ExpressionParser expressionParser)
            throws SyntaxException, EvaluatingException, TypeException {
        return expressionParser.parse(s, 7, n);
    }

    public static String parse(String string) {
        filter = "filter{";
        map = "%>%map{";
        afterLastMap = "element";
        wasFilter = false;
        int index = 0;
        Token token = Token.BEGIN;
        try {
            while (index < string.length()) {
                if (token == Token.BEGIN && index + 4 < string.length()
                        && string.substring(index, index + 4).equals("map{")) {
                    index += 4;
                    index = parseExpression(string, index, Token.ARITHMETIC) + 1;
                    token = Token.END;
                } else if (token == Token.BEGIN && index + 7 < string.length()
                        && string.substring(index, index + 7).equals("filter{")) {
                    index += 7;
                    index = parseExpression(string, index, Token.LOGICAL) + 1;
                    token = Token.END;
                } else if (token == Token.END && index + 3 < string.length()
                        && string.substring(index, index + 3).equals("%>%")) {
                    index += 3;
                    token = Token.BEGIN;
                } else throw new UnknownIdentifierException(index);
            }
        } catch (TypeException | SyntaxException | EvaluatingException e) {
            return e.getMessage();
        }
        if (filter.equals("filter{")) {
            filter += "element=element";
        }
        mapFrom = filter.length() + 1 + map.length();
        return filter + "}" + map + afterLastMap + "}";
    }

    private static int parseExpression(String string, int index, Token token)
            throws SyntaxException, TypeException, EvaluatingException {
        ExpressionParser expressionParser = new ExpressionParser();
        TripleExpression expression = expressionParser.parse(string, index, token);
        if (expression.type == 0 && token != Token.ARITHMETIC || expression.type == 1 && token != Token.LOGICAL) {
            throw new TypeException("TYPE ERROR");
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
