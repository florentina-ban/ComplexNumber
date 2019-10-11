package java_pack;

/**
 * creates an adding expression of two complex numbers
 */
public class ComplexExpressionAdd extends ComplexExpression {

    public ComplexExpressionAdd(NrComplex a, NrComplex b) {
        super(a, b);
        this.op='+';
    }

    @Override
    public NrComplex execute() {
        return a.add(b);
    }
}
