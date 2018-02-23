package Model.Statements;

import Model.Expressions.Expression;
import Model.ProgramState;

public class NewLatchStatement implements Statement {

    private String var;
    private Expression exp;

    public NewLatchStatement(String var, Expression exp) {
        this.var = var;
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) {

        int memLocation = state.getLatchTable().put(exp.evaluate(state));
        state.getSymTable().put(var, memLocation);

        return null;
    }

    @Override
    public String toString () {
        return "newLatchTable(" + var + "," + exp.toString() + ")";
    }

}
