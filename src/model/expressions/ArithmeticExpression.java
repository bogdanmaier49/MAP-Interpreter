package model.expressions;

import exceptions.ExpressionException;
import model.utils.Dictionary;

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
    public int evaluate (Dictionary<String, Integer> symbolTable, Dictionary<Integer, Integer> heap) throws ExpressionException {

        switch (operator) {
            case '+': return e1.evaluate(symbolTable, heap) + e2.evaluate(symbolTable, heap);
            case '-': return e1.evaluate(symbolTable, heap) - e2.evaluate(symbolTable, heap);
            case '*': return e1.evaluate(symbolTable, heap) * e2.evaluate(symbolTable, heap);
            case '/': return e1.evaluate(symbolTable, heap) / e2.evaluate(symbolTable, heap);
            case '%': return e1.evaluate(symbolTable, heap) % e2.evaluate(symbolTable, heap);
        }

        throw new ExpressionException("Invalid Operator!\n");

    }



}
