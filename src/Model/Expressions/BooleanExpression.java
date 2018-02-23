package Model.Expressions;

import Model.ProgramState;

public class BooleanExpression implements  Expression{

    private Expression e1;
    private Expression e2;
    private String comparator;

    public BooleanExpression (Expression e1, String comparator, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.comparator = comparator;
    }

    @Override
    public int evaluate(ProgramState state) {

        int e1Val = e1.evaluate(state);
        int e2Val = e2.evaluate(state);

        if (comparator.equals("==") && e1Val == e2Val)
            return 1;
        if (comparator.equals(">=") && e1Val >= e2Val)
            return 1;
        if (comparator.equals("<=") && e1Val <= e2Val)
            return 1;
        if (comparator.equals(">") && e1Val > e2Val)
            return 1;
        if (comparator.equals("<") && e1Val < e2Val)
            return 1;
        if (comparator.equals("!=") && e1Val != e2Val)
            return 1;

        return 0;
    }
}
