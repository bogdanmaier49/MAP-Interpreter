package Model.Statements;

import Model.Expressions.Expression;
import Model.ProgramState;

public class ConditionalAssignStatement implements Statement{

    private String var;
    private Expression exp1, exp2, exp3;

    public ConditionalAssignStatement (String var, Expression exp1, Expression exp2, Expression exp3) {
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) {

        state.getExeStack().push(new IfStatement(
                    exp1,
                    new AssignStatement(var,exp2),
                    new AssignStatement(var,exp3)
                )
        );

        return null;
    }

    @Override
    public String toString () {
        return var + "=" + exp1 + "?" + exp2 + ":" + exp3;
    }
}
