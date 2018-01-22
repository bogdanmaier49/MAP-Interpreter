package model.statements;

import model.ProgramState;
import model.utils.IStack;

public class CompStatement implements Statement {


    private Statement e1;
    private Statement e2;

    public CompStatement (Statement e1, Statement e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public ProgramState execute (ProgramState state){
        IStack<Statement> execStack = state.getExecutionStack();

        execStack.push(e2);
        execStack.push(e1);

        return null;
    }

    @Override
    public String toString () {
        return e1 + "\n" + e2;
    }

}
