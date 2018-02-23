package Model.Statements;

import Model.Expressions.Expression;
import Model.ProgramState;

public class IfStatement implements Statement {
    private Expression exp;
    private Statement thenS;
    private Statement elseS;

    public IfStatement(Expression e, Statement t){
        exp = e;
        thenS = t;
        elseS = null;
    }
    public IfStatement(Expression e, Statement t, Statement el){
        exp = e;
        thenS = t;
        elseS = el;
    }

    @Override
    public ProgramState execute(ProgramState state){
        if (exp.evaluate(state) > 0){
            return thenS.execute(state);
        }
        if (elseS == null){
            return null;
        }
        return elseS.execute(state);
    }

    @Override
    public String toString() {
        return "if(" + exp.toString() + ") { " + thenS.toString() + " } else {" + elseS.toString() + "}";
    }

}
