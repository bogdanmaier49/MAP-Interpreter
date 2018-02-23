package Model.Statements;

import Model.ProgramState;

public class AwaitStatement implements Statement {

    private String var;

    public AwaitStatement (String var){
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) {

        if (! state.getSymTable().containsKey(var))
            throw new RuntimeException(var + " does not exits in the symbol table");

        int foundIndex = state.getSymTable().get(var);

        if (! state.getLatchTable().containsKey(foundIndex))
            throw  new RuntimeException("index not found in the latch table");
        else if (state.getLatchTable().get(foundIndex) != 0)
            state.getExeStack().push(this);

        return null;
    }

    @Override
    public String toString () {
        return "await(" + var + ")";
    }
}
