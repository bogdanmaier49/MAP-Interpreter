package Model.Statements;

import Model.Expressions.Expression;
import Model.ProgramState;

public class NewVariableStatement implements Statement {
    private String varName;
    private Expression expression;

    public NewVariableStatement(String varName, Expression expression){
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        int memLocation = state.getHeap().put(expression.evaluate(state));
        state.getSymTable().put(varName, memLocation);
        return null;
    }

    @Override
    public String toString(){
        return "new(" + varName + ", " + expression.toString() + ")";

    }
}
