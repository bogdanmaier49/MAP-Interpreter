package model.statements;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.utils.Dictionary;
import model.ProgramState;
import model.expressions.Expression;
import model.utils.IHeap;

public class AssignStatement implements Statement{

    private String variable;
    private Expression e1;

    public AssignStatement (String variable, Expression e1) {
        this.variable = variable;
        this.e1 = e1;
    }

    @Override
    public ProgramState execute (ProgramState state) throws StatementException {
        Dictionary<String, Integer> symbolTable = state.getSymbolTable();
        IHeap<Integer> heap = state.getHeap();

        try {

            int val = e1.evaluate(symbolTable, heap);
            symbolTable.put(variable, val);

        } catch (ExpressionException e) {
            throw new StatementException("Could not assign: " + e1 + " to " + variable + " (" + e + ")");
        }

        return null;
    }

    @Override
    public String toString () {
        return variable + " = " + e1;
    }
}
