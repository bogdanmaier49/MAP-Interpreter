package model.statements;

import exceptions.ExpressionException;
import model.ProgramState;
import model.expressions.Expression;
import model.utils.Dictionary;

public class AllocateStatement implements Statement {

    private String varName;
    private Expression expr;

    public AllocateStatement (String varName, Expression expr) {
        this.varName = varName;
        this.expr = expr;
    }

    @Override
    public ProgramState execute (ProgramState state) {

        Dictionary<Integer, Integer> heap = state.getHeap();
        Dictionary<String, Integer> symbolTable = state.getSymbolTable();

        try {
            int exprRes = expr.evaluate(symbolTable, heap);

            int id=IDGenerator.generateHeapID();

            symbolTable.add(varName, id);
            heap.add(id, exprRes);

        } catch (ExpressionException e) {
            e.printStackTrace();
        }

        return state;
    }

    @Override
    public String toString () {
        return "new(" + varName + ", " + expr + " );";
    }

}
