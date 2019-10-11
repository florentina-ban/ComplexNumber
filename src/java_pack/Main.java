package java_pack;

import javax.crypto.AEADBadTagException;

public class Main {

    public static void main(String[] args) {
	    ExpressionParser expressionParser=new ExpressionParser(args);
        ComplexExpression expression=expressionParser.createExpression2();
        if (expression==null)
            System.out.println("fake expression");
        else
            try {
                System.out.println(expression.execute()); //divide by zero
            }catch (ArithmeticException e){
                System.out.println(e.getMessage());
            }
    }
}
