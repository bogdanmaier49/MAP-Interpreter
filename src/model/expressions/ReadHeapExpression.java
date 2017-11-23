package model.expressions;

import exceptions.ExpressionException;
import model.utils.Dictionary;

public class ReadHeapExpression implements Expression {

    private String varName;

    public ReadHeapExpression (String varName) {
        this.varName = varName;
    }

    @Override
    public int evaluate (Dictionary<String, Integer> symbolTable, Dictionary<Integer, Integer> heap) throws ExpressionException {

        if (! symbolTable.exists(varName)) {
            throw new ExpressionException("The variable "+ varName +" does not exist\n");
        }

        int addr = symbolTable.get(varName);

        if (! heap.exists(addr))
            throw new ExpressionException("The address " + addr + " does not exist\n");

        return heap.get(addr);
    }

    @Override
    public String toString () {
        return "readHeap( " + varName + " );";
    }

}
