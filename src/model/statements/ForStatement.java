package model.statements;

import exceptions.StatementException;
import model.ProgramState;
import model.expressions.Expression;

public class ForStatement implements Statement {

    private Statement init, step, body;
    private Expression cond;

    public ForStatement (Statement init, Statement step, Statement body, Expression cond) {
        this.init = init;
        this.step = step;
        this.body = body;
        this.cond = cond;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementException {



        return state;
    }
}
