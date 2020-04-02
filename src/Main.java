import Exceptions.EvaluatingException;
import Exceptions.ParsingException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParsingException, EvaluatingException {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.next();
        System.out.println(Parser1.parse(string));
    }
}
