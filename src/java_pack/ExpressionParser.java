package java_pack;

import java.util.Stack;

/**
 * validates the expression given
 * transforms the array of strings in a ComplexExpression
 */
public class ExpressionParser {
    private String[] sir;
    private NrComplex[] numbers=new NrComplex[10];
    private char[] operations=new char[10];
    private int nrComplex=0;
    private int nrOperations=0;

    public ExpressionParser(String[] sir) {
        this.sir = sir;
    }

    /**
     * validates an operator
     * returns true if the operator in valid and false otherwise
     */
    private boolean validateOperation(String op){
        if (op.length()!=1)
            return false;
        if (op.charAt(0)=='+'){
            nrOperations++;
            operations[nrOperations-1]='+';
            return true;
        }
        if (op.charAt(0)=='-'){
            nrOperations++;
            operations[nrOperations-1]='-';
            return true;
        }
        if (op.charAt(0)=='*'){
            nrOperations++;
            operations[nrOperations-1]='*';
            return true;
        }
        if (op.charAt(0)=='/'){
            nrOperations++;
            operations[nrOperations-1]='/';
            return true;
        }
        return false;
    }

    /**
     * validates the expression
     * @return true if the expression is valid and false otherwise
     */
    private boolean validate(){
        if (sir.length%2==0)
                return false;
        int i=0;
        while (i<sir.length && validateComplex(sir[i])){
            i=i+2;
        }
        if (i<sir.length)
            return false;
        i=1;
        nrOperations++;
        operations[nrOperations-1]='+';
        while (i<sir.length && validateOperation(sir[i])){
            i=i+2;
        }
        if (i<sir.length)
            return false;
        return true;
    }

    /**
     * validates one string as a complex number
     * @param op - string
     * @returns
     *      *      true for a valid complex number
     *      *      false otherwise
     */
    private boolean validateComplex(String nr){
        if ((!nr.contains("*")) && nr.contains("i")){
            nr=nr.substring(0,nr.length()-1);
            nr=nr.concat("1*i");
        }
        if (nr.matches("[0-9]+\\+[0-9]+\\*i") || nr.matches("\\-[0-9]+\\+[0-9]+\\*i")){
            String[] parts=nr.split("\\+");
            parts[1]=parts[1].replace("i","");
            parts[1]=parts[1].replace("*","");
            NrComplex n;
            n=new NrComplex(Float.parseFloat(parts[0]),Float.parseFloat(parts[1]));
            nrComplex++;
            numbers[nrComplex-1]=n;
            return true;
        }
        if (nr.matches("[0-9]+\\-[0-9]*\\*i") || nr.matches("\\-[0-9]+\\-[0-9]*\\*i")){
            String[] parts=nr.split("\\-");
            int j=0;
            if (parts.length==3)
                j=1;
            parts[j+1]=parts[j+1].replace("i","");
            parts[j+1]=parts[j+1].replace("*","");
            NrComplex n;
            if (nr.charAt(0)=='-')
                n=new NrComplex(0-Float.parseFloat(parts[j]),0-Float.parseFloat(parts[j+1]));
            else
                n=new NrComplex(Float.parseFloat(parts[j]),0-Float.parseFloat(parts[j+1]));
            nrComplex++;
            numbers[nrComplex-1]=n;
            return true;
        }
        if (nr.matches("[0-9]+\\*i") || nr.matches("\\-[0-9]+\\*i")){
            nr=nr.replace("i","");
            nr=nr.replace("*","");
            NrComplex n=new NrComplex(0,Float.parseFloat(nr));
            nrComplex++;
            numbers[nrComplex-1]=n;
            return true;
        }
        if (nr.matches("[0-9]+") || nr.matches("\\-[0-9]+")){
            NrComplex n=new NrComplex(Float.parseFloat(nr),0);
            nrComplex++;
            numbers[nrComplex-1]=n;
            return true;
        }
          return false;
    }

    /**
     * creates the final expression disregarding the operation order
     * @return ComplexExpression
     */
    public ComplexExpression createExpression(){
        if (validate()){
            ExpressionFactory expressionFactory = ExpressionFactory.getExpressionFactory();
            NrComplex n1 = new NrComplex(0, 0);
            NrComplex n2 = new NrComplex(0, 0);
            ComplexExpression expression=expressionFactory.createExpresion('+',n1,n2);
            int i=0;
            while (i<nrComplex){
                NrComplex rez=expression.execute();
                expression=expressionFactory.createExpresion(operations[i],rez,numbers[i]);
                i++;
            }
            return expression;
        }
        return null;
    }
    private void removeSet(int i){
        for (int k=i; k<nrOperations-1; k++){
            operations[k]=operations[k+1];
        }
        nrOperations--;
        for (int k=i; k<nrComplex-1; k++){
            numbers[k]=numbers[k+1];
        }
        numbers[nrComplex-1]=null;
        nrComplex--;
    }

    /**
     * creates the final expression regarding the operation order
     * @return ComplexExpression
     */
    public ComplexExpression createExpression2() {
        if (validate()) {
            ExpressionFactory expressionFactory = ExpressionFactory.getExpressionFactory();
            boolean mulFound;
            do {
                mulFound = false;
                int i;
                for (i = 0; i <= nrOperations; i++) {
                    if (operations[i] == '*' || operations[i] == '/') {
                        ComplexExpression expression = expressionFactory.createExpresion(operations[i], numbers[i-1], numbers[i]);
                        NrComplex rez = expression.execute();
                        removeSet(i);
                        numbers[i-1] = rez;
                        mulFound = true;
                    }
                }
            } while (mulFound);
            NrComplex n1 = new NrComplex(0, 0);
            NrComplex n2 = new NrComplex(0, 0);
            ComplexExpression expression = expressionFactory.createExpresion('+', n1, n2);
            int i = 0;
            while (i < nrComplex) {
                NrComplex rez = expression.execute();
                expression = expressionFactory.createExpresion(operations[i], rez, numbers[i]);
                i++;
            }
            return expression;
        }
        return null;
    }
}
