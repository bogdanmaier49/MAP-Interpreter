package model.expressions;

import exceptions.ExpressionException;
import model.utils.Dictionary;

public interface Expression {

    int evaluate(Dictionary<String, Integer> symbolTable, Dictionary<Integer, Integer> heap) throws ExpressionException;

}
