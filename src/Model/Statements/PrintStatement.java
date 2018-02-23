package Model.Statements;

import Model.Expressions.Expression;
import Model.ProgramState;

public class PrintStatement implements Statement {
    private Expression exp;

    public PrintStatement(Expression exp){
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "print(" + exp.toString() + ")";
    }

    public ProgramState execute(ProgramState state){
        state.appendOutput(exp.evaluate(state) + "\n");
        return null;
    }
}
