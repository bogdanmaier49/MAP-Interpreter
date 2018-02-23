package Model.Expressions;


import Model.ProgramState;

public class ArithmeticExpression implements Expression {

     private Expression e1;
     private Expression e2;
     private char operator;


    public ArithmeticExpression (Expression e1, char operator, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.operator = operator;
    }

    @Override
    public int evaluate (ProgramState state) {

        int e1Val = e1.evaluate(state);
        int e2Val = e2.evaluate(state);

        switch (operator) {
            case '+':
                return e1Val + e2Val;
            case '-':
                return e1Val - e2Val;
            case '*':
                return e1Val * e2Val;
            case '/':
                if (e2Val == 0) {
                    throw new RuntimeException("Division by 0 error");
                }
                return e1Val / e2Val;
            case '%':
                if (e2Val == 0) {
                    throw new RuntimeException("Division by 0 error");
                }
                return e1Val % e2Val;

        }

        return 0;
    }

    @Override
    public String toString () {
        return e1.toString() + " " + operator + " " + e2.toString();
    }

}
