package java_pack;

/**
 * implements a complex number with real and imaginary parts as floats
 */
public class NrComplex {
    private float real;
    private float imag;

    public NrComplex(float real, float imag) {
        this.real = real;
        this.imag = imag;
    }

    public float getReal() {
        return real;
    }

    public float getImag() {
        return imag;
    }

    public NrComplex add(NrComplex b){
        this.real+=b.getReal();
        this.imag+=b.getImag();
        return this;
    }
    public NrComplex substract(NrComplex b){
        this.real-=b.getReal();
        this.imag-=b.getImag();
        return this;
    }

    public NrComplex multiply(NrComplex b){
        float r=this.real;
        float i=this.imag;
        this.real=r*b.real-this.imag*b.imag;
        this.imag=r*b.imag+b.real*i;
        return this;
    }

    public  NrComplex divide(NrComplex b){
        float a1=this.real;
        float b1=this.imag;
        float a2=b.real;
        float b2=b.imag;
        if (a2 * a2 + b2 * b2==0)
            throw (new ArithmeticException("divide by zero"));
        this.real = (a1 * a2 + b1 * b2) / (a2 * a2 + b2 * b2);
        this.imag = (a2 * b1 - a1 * b2) / (a2 * a2 + b2 * b2);
        return this;
    }

    @Override
    public String toString() {
        if (imag>=0)
            return real +"+" + imag +"*i";
        else
            return  real +" "+ imag + "*i";
    }
}
