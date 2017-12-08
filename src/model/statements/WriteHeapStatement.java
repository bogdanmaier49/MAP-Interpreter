package model.statements;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.ProgramState;
import model.expressions.Expression;
import model.utils.Dictionary;

public class WriteHeapStatement implements Statement {

    private String varName;
    private Expression expr;

    public WriteHeapStatement (String varName, Expression expr) {
        this.varName =varName;
        this.expr = expr;
    }

    @Override
    public ProgramState execute (ProgramState state) throws StatementException {

        Dictionary<Integer, Integer> heap = state.getHeap();
        Dictionary<String, Integer> symbolTable = state.getSymbolTable();

        int result = 0;
        try {
            result = expr.evaluate(symbolTable, heap);
        } catch (ExpressionException e) {
            e.printStackTrace();
        }

        if (!symbolTable.exists(varName))
            throw new StatementException(varName + " does not exist in symbol table\n");

        int addr = symbolTable.get(varName);
        System.out.println("ADDR: " + addr);
        if (!heap.exists(addr)) {
            throw new StatementException(varName + " does not exist in heap\n");
        }

        heap.update(addr, result);

        return state;
    }

    @Override
    public String toString () {
        return "writeHeap( " + varName + ", " + expr + ");";
    }
}
