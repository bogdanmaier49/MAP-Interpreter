package Model.Expressions;

import Model.ProgramState;

public class ConstantExpression implements Expression {

    private Integer value;

    public ConstantExpression(int value){
        this.value = value;
    }

    @Override
    public int evaluate(ProgramState state) {
        return value;
    }

    public String toString(){
        return value.toString();
    }
}
