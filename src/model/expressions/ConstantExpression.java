package model.expressions;

import model.utils.Dictionary;
import model.utils.IHeap;

public class ConstantExpression implements Expression {

    private int value;

    public ConstantExpression (int value) {
        this.value = value;
    }

    @Override
    public int evaluate (Dictionary<String, Integer> symbolTable, IHeap<Integer> heap) {
        return value;
    }

    @Override
    public String toString() {
        return "" + value;
    }

}
