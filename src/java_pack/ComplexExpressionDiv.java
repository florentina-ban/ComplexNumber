package java_pack;
/**
 * creates an expression of division of two complex numbers
 */
public class ComplexExpressionDiv extends ComplexExpression {

    public ComplexExpressionDiv(NrComplex a, NrComplex b) {
        super(a, b);
        this.op='/';
    }

    @Override
    public NrComplex execute() {
        return a.divide(b);
    }
}
