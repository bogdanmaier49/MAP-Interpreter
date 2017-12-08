package model.expressions;

import exceptions.ExpressionException;
import model.utils.Dictionary;

public class BooleanExpression implements Expression {

    private Expression e1;
    private Expression e2;
    private String comparator;

    public BooleanExpression (Expression e1, String comparator, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.comparator = comparator;
    }

    @Override
    public int evaluate (Dictionary<String, Integer> symbolTable, Dictionary<Integer, Integer> heap) {


        try {

            int e1Val = e1.evaluate(symbolTable, heap);
            int e2Val = e1.evaluate(symbolTable, heap);

            switch (comparator){
                case "<":
                    if (e1Val < e2Val) return 1;
                case ">":
                    if (e1Val > e2Val) return 1;
                case "==":
                    if (e1Val == e2Val) return 1;
                case ">=":
                    if (e1Val >= e2Val) return 1;
                case "<=":
                    if (e1Val <= e2Val) return 1;
                case "!=":
                    if (e1Val != e2Val) return 1;

            }

        } catch (ExpressionException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public String toString () {
        return e1 + " " + comparator + " " + e2 + ";";
    }

}
