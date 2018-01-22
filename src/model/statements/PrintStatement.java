package model.statements;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.utils.Dictionary;
import model.utils.IHeap;
import model.utils.IList;
import model.ProgramState;
import model.expressions.Expression;

public class PrintStatement implements Statement {

    private Expression e1;

    public PrintStatement (Expression e1) {
        this.e1 = e1;
    }

    @Override
    public ProgramState execute (ProgramState state) throws StatementException {
        IList<Integer> output = state.getOutput();
        Dictionary<String, Integer> symbolTable = state.getSymbolTable();
        IHeap<Integer> heap = state.getHeap();

        try {
            output.add(e1.evaluate(symbolTable, heap));
        } catch (ExpressionException e) {
            throw new StatementException("Could not print: " + e1 + " (" + e + ")");
        }

        return null;
    }

    @Override
    public String toString () {
        return "Print("+e1+")";
    }
}
