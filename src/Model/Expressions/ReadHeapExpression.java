package Model.Expressions;

import Model.ProgramState;

public class ReadHeapExpression implements Expression {
    private String varName;

    public ReadHeapExpression(String varName){
        this.varName = varName;
    }

    @Override
    public int evaluate(ProgramState state) {
        int memLocation = state.getSymTable().get(varName);
        return state.getHeap().get(memLocation);
    }

    @Override
    public String toString(){
        return "readHeap(" + varName + ")";
    }
}
