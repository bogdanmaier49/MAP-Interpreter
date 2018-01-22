package model.statements;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.utils.Dictionary;
import model.ProgramState;
import model.expressions.Expression;
import model.utils.IHeap;

public class IfStatement implements Statement {


    private Expression expr;

    private Statement thenStatement;
    private Statement elseStatement;

    public IfStatement (Expression expr, Statement thenStatement, Statement elseStatement) {
        this.expr = expr;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public ProgramState execute (ProgramState state) throws StatementException {

        Dictionary<String, Integer> symbolTable = state.getSymbolTable();
        IHeap<Integer> heap = state.getHeap();


        try {

            int val = expr.evaluate(symbolTable, heap);

            if (val != 0)
                    thenStatement.execute(state);
            else
                    elseStatement.execute(state);

        } catch (ExpressionException e) {
            throw new StatementException("Invalid if statement (" + e + ")");
        }



        return null;
    }

    @Override
    public String toString () {
        return "if(" + expr + ") {\n" + thenStatement + "\n} else {\n" + elseStatement + "\n}";
    }

}
