package Model.Statements;

import Model.ProgramState;

public class ForkStatement implements Statement {
    private Statement statement;

    public ForkStatement(Statement statement){
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        ProgramState newPrgState = new ProgramState();
        newPrgState.setExeStack(statement);
        newPrgState.setFileTable(state.getFileTable());
        newPrgState.setHeap(state.getHeap());
        newPrgState.setOutputBuffer(state.getOutputBuffer());
        newPrgState.setSymTable(state.getSymTable().copy());
        return newPrgState;
    }

    @Override
    public String toString(){
        return "fork( " + statement.toString() + " ~)";
    }
}
