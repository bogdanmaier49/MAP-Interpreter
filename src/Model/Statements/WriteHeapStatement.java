package Model.Statements;

import Model.Expressions.Expression;
import Model.ProgramState;

public class WriteHeapStatement implements Statement {
    private String varName;
    private Expression expression;

    public WriteHeapStatement(String varName, Expression expression){
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        int memLocation = state.getSymTable().get(varName);
        state.getHeap().put(memLocation, expression.evaluate(state));
        return null;
    }

    @Override
    public String toString(){
        return "writeHeap(" + varName  + "," + expression.toString() + ")";
    }
}
