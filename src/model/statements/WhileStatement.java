package model.statements;

import exceptions.ExpressionException;
import model.ProgramState;
import model.expressions.Expression;
import model.utils.Dictionary;

public class WhileStatement implements Statement {

    private Expression e;
    private Statement stm;


    public WhileStatement (Expression e, Statement stm) {
        this.e = e;
        this.stm = stm;
    }


    @Override
    public ProgramState execute (ProgramState state) {

        Dictionary<String, Integer> symbolTable = state.getSymbolTable();
        Dictionary<Integer, Integer> heap = state.getHeap();

        try {

            if (e.evaluate(symbolTable, heap) != 0)
            {
                state.getExecutionStack().push(this);
                state.getExecutionStack().push(stm);
            }

        } catch (ExpressionException e1) {
            e1.printStackTrace();
        }

        return state;
    }

    @Override
    public String toString () {
        return "while( " + e + " ){\n" + stm + "\n}";
    }

}
