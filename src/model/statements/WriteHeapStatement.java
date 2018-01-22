package model.statements;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.ProgramState;
import model.expressions.Expression;
import model.utils.Dictionary;
import model.utils.IHeap;

public class WriteHeapStatement implements Statement {

    private String varName;
    private Expression expr;

    public WriteHeapStatement (String varName, Expression expr) {
        this.varName =varName;
        this.expr = expr;
    }

    @Override
    public ProgramState execute (ProgramState state) throws StatementException {

        IHeap<Integer> heap = state.getHeap();
        Dictionary<String, Integer> symbolTable = state.getSymbolTable();

        int result = 0;
        try {
            result = expr.evaluate(symbolTable, heap);
        } catch (ExpressionException e) {
            throw new StatementException(e.toString());
        }

        if (!symbolTable.containsKey(varName))
            throw new StatementException(varName + " does not exist in symbol table\n");

        int address = symbolTable.get(varName);

        if (!heap.containsKey(address)) {
            throw new StatementException(varName + " does not exist in heap\n");
        }

        heap.put(address, result);

        return null;
    }

    @Override
    public String toString () {
        return "writeHeap( " + varName + ", " + expr + ")";
    }
}
