package java_pack;

/**
 * creates a substracting expression of two complex numbers
 */
public class ComplexExpressionSub extends ComplexExpression {

    public ComplexExpressionSub(NrComplex a, NrComplex b) {
        super(a, b);
        this.op='-';
    }

    @Override
    public NrComplex execute() {
        return a.substract(b);
    }
}
