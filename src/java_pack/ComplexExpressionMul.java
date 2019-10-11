package java_pack;

/**
 * creates expression of multiplication of two complex numbers
 */
public class ComplexExpressionMul extends ComplexExpression {

    public ComplexExpressionMul(NrComplex a, NrComplex b) {
        super(a, b);
        this.op='*';
    }

    @Override
    public NrComplex execute() {
        return a.multiply(b);
    }
}
