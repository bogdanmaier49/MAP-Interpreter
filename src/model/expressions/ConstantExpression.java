package model.expressions;

import model.utils.Dictionary;

public class ConstantExpression implements Expression {

    private int value;

    public ConstantExpression (int value) {
        this.value = value;
    }

    @Override
    public int evaluate (Dictionary<String, Integer> symbolTable, Dictionary<Integer, Integer> heap) {
        return value;
    }

    @Override
    public String toString() {
        return "" + value;
    }

}
