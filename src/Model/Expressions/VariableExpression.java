package Model.Expressions;

import Model.ProgramState;

public class VariableExpression implements Expression {

    private String id;

    public VariableExpression(String id){
        this.id = id;
    }

    @Override
    public int evaluate(ProgramState state) {
        return state.getSymTable().get(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
