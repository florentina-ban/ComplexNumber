package java_pack;

public abstract class ComplexExpression {
    protected NrComplex a,b;
    protected char op;

    public ComplexExpression(NrComplex a, NrComplex b) {
        this.a = a;
        this.b = b;
    }

    public char getOp() {
        return op;
    }

    public abstract NrComplex execute();
}
