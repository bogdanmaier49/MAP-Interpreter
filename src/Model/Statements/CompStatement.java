package Model.Statements;

import Model.Containers.IStack;
import Model.ProgramState;

public class CompStatement implements Statement {
    private Statement first;
    private Statement second;

    public CompStatement(Statement first, Statement second){
        this.first = first;
        this.second = second;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IStack<Statement> stk = state.getExeStack();
        stk.push(second);
        stk.push(first);
        return null;
    }

    @Override
    public String toString() {
        return first.toString() + ";\n" + second.toString();
    }
}
