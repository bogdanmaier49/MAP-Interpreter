package Model.Statements;

import Model.Expressions.BooleanExpression;
import Model.Expressions.Expression;
import Model.Expressions.VariableExpression;
import Model.ProgramState;

public class ForStatement implements Statement {

    private Expression init, cond, step;
    private Statement body;

    public ForStatement(Expression init, Expression cond, Expression step, Statement body) {
        this.init = init;
        this.step = step;
        this.body = body;
        this.cond = cond;
    }

    @Override
    public ProgramState execute(ProgramState state) {

        Statement newStatement = new CompStatement(
                new AssignStatement("v", init),
                new WhileStatement(
                        new BooleanExpression(new VariableExpression("v"), "<", cond),
                        new CompStatement(
                                body,
                                new AssignStatement("v", step)
                        )
                )
        );

        state.getExeStack().push(newStatement);

        return null;
    }

    @Override
    public String toString () {
        return "for(v = " + init.toString() + "; v < " + cond.toString() + "; v = " + step + ") {\n" + body.toString() + "\n}";
    }
}
