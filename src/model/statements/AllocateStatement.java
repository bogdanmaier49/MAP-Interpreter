package model.statements;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.ProgramState;
import model.expressions.Expression;
import model.utils.Dictionary;
import model.utils.IHeap;

public class AllocateStatement implements Statement {

    private String varName;
    private Expression expr;

    public AllocateStatement (String varName, Expression expr) {
        this.varName = varName;
        this.expr = expr;
    }

    @Override
    public ProgramState execute (ProgramState state)throws StatementException {

        IHeap<Integer> heap = state.getHeap();
        Dictionary<String, Integer> symbolTable = state.getSymbolTable();

        try {
            int exprRes = expr.evaluate(symbolTable, heap);

            int id=IDGenerator.generateHeapID();

            symbolTable.put(varName, id);
            heap.put(id, exprRes);

        } catch (ExpressionException e) {
            throw new StatementException(e.toString());
        }

        return null;
    }

    @Override
    public String toString () {
        return "new(" + varName + ", " + expr + " )";
    }

}
