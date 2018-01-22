package model.statements;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.ProgramState;
import model.expressions.Expression;
import model.utils.Dictionary;
import model.utils.IHeap;

public class WhileStatement implements Statement {

    private Expression e;
    private Statement stm;


    public WhileStatement (Expression e, Statement stm) {
        this.e = e;
        this.stm = stm;
    }


    @Override
    public ProgramState execute (ProgramState state)throws StatementException {

        Dictionary<String, Integer> symbolTable = state.getSymbolTable();
        IHeap<Integer> heap = state.getHeap();

        try {

            if (e.evaluate(symbolTable, heap) != 0)
            {
                state.getExecutionStack().push(this);
                state.getExecutionStack().push(stm);
            }

        } catch (ExpressionException e1) {
            throw new StatementException(e1.toString());
        }

        return null;
    }

    @Override
    public String toString () {
        return "while( " + e + " ){\n" + stm + "\n}";
    }

}
