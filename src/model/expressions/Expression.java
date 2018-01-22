package model.expressions;

import exceptions.ExpressionException;
import model.utils.Dictionary;
import model.utils.IHeap;

public interface Expression {

    int evaluate(Dictionary<String, Integer> symbolTable, IHeap<Integer> heap) throws ExpressionException;

}
