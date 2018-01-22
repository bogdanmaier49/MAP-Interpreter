package model.expressions;

import exceptions.ExpressionException;
import model.utils.Dictionary;
import model.utils.IHeap;

public class ReadHeapExpression implements Expression {

    private String varName;

    public ReadHeapExpression (String varName) {
        this.varName = varName;
    }

    @Override
    public int evaluate (Dictionary<String, Integer> symbolTable, IHeap<Integer> heap) throws ExpressionException {

        if (! symbolTable.containsKey(varName)) {
            throw new ExpressionException("The variable "+ varName +" does not exist\n");
        }

        int address = symbolTable.get(varName);

        if (! heap.containsKey(address))
            throw new ExpressionException("The address " + address + " does not exist\n");

        return heap.get(address);
    }

    @Override
    public String toString () {
        return "readHeap( " + varName + " )";
    }

}
