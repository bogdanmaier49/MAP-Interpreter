package model.expressions;

import exceptions.ExpressionException;
import model.utils.Dictionary;
import model.utils.IHeap;

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
    public int evaluate (Dictionary<String, Integer> symbolTable, IHeap<Integer> heap) throws ExpressionException {

        switch (operator) {
            case '+':
                return e1.evaluate(symbolTable, heap) + e2.evaluate(symbolTable, heap);
            case '-':
                return e1.evaluate(symbolTable, heap) - e2.evaluate(symbolTable, heap);
            case '*':
                return e1.evaluate(symbolTable, heap) * e2.evaluate(symbolTable, heap);
            case '/':
                if (e2.evaluate(symbolTable, heap) == 0)
                    throw new ExpressionException("Division by 0 exception.");
                return e1.evaluate(symbolTable, heap) / e2.evaluate(symbolTable, heap);
            case '%':
                if (e2.evaluate(symbolTable, heap) == 0)
                    throw new ExpressionException("Division by 0 exception.");
                return e1.evaluate(symbolTable, heap) % e2.evaluate(symbolTable, heap);
        }

        throw new ExpressionException("Invalid Operator!\n");
    }


    @Override
    public String toString () {
        return e1 + " " + operator + " " + e2;
    }




}
