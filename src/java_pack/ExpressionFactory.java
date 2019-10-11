package java_pack;

/**
 * creates un expression according to the operator sign
 */
public class ExpressionFactory {
    private static ExpressionFactory instance=null;

    private ExpressionFactory() {
    }

    public static ExpressionFactory getExpressionFactory(){
        if (instance==null)
           instance=new ExpressionFactory();
        return instance;
    }

    public ComplexExpression createExpresion(char op, NrComplex a, NrComplex b){
        if (op=='+') {
            ComplexExpressionAdd add = new ComplexExpressionAdd(a,b);
            return add;
        }
        if (op=='-') {
            ComplexExpressionSub sub = new ComplexExpressionSub(a, b);
            return sub;
        }
        if (op=='*'){
            ComplexExpressionMul mul = new ComplexExpressionMul(a, b);
            return mul;
        }
        ComplexExpressionDiv div = new ComplexExpressionDiv(a, b);
        return div;
    }
}