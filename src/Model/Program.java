package Model;

import Model.Exceptions.ProgramException;
import Model.Statements.Statement;

public class Program {
    private int id;
    private ProgramState state;
    private Statement statement;

    public Program(Statement statement){
        this.statement = statement;
        this.state = new ProgramState();
        state.getExeStack().push(statement);
        id = 1;
        state.setID(1);
    }

    private Program(ProgramState state){
        this.statement = state.getExeStack().peek();
        this.state = state;
    }

    public Program oneStep() throws ProgramException {
        if( this.completed() ){
            throw new ProgramException("Done");
        }
        Statement currentStatement = state.getExeStack().pop();
        ProgramState newState = currentStatement.execute(state);
        if (newState != null)
            return new Program(newState);
        return null;
    }

    public Boolean completed(){
        return state.getExeStack().empty();
    }

    public ProgramState getState(){ return state; }

    public String getOutput(){ return state.getOutput(); }

    public String toString(){ return statement.toString(); }

    public Integer getID() { return id; }

    public void setID(int id){
        this.id = id;
        this.state.setID(id);
    }
}
