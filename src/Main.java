import Exceptions.EvaluatingException;
import Exceptions.SyntaxException;
import Exceptions.TypeException;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws EvaluatingException, TypeException, SyntaxException {
        String string = "filter{((element>15)|(element<0))}";
        List<Integer> a = new ArrayList<>();
        a.add(3);
        a.add(-4);
        a.add(10);
        a.add(20);
        a.add(30);
//        System.out.println(Parser.parse(string));
        System.out.println(Parser.parseAndApply(string, a));
    }
}
