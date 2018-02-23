package Model.Statements;

import Model.Expressions.Expression;
import Model.ProgramState;

public class AssignStatement implements Statement {
    private String var;
    private Expression exp;

    public AssignStatement(String var, Expression exp){
        this.var = var;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return var + "=" + exp.toString();
    }

    @Override
    public ProgramState execute(ProgramState state) {
        int value = exp.evaluate(state);
        state.getSymTable().put(var, value);
        return null;
    }
}
