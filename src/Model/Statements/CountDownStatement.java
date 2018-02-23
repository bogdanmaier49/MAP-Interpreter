package Model.Statements;

import Model.ProgramState;

public class CountDownStatement implements Statement {

    private String var;

    public CountDownStatement (String var) {
        this.var = var;
    }

    @Override
    public ProgramState execute(ProgramState state) {

        if (! state.getSymTable().containsKey(var))
            throw new RuntimeException(var + " does not exits in the symbol table");
        int foundIndex = state.getSymTable().get(var);

        if (state.getLatchTable().get(foundIndex) > 0) {
            state.getLatchTable().put(foundIndex, state.getLatchTable().get(foundIndex) - 1);
            state.appendOutput(state.getID() + "\n");
        }




        return null;
    }

    @Override
    public String toString () {
        return "countDown(" + var + ")";
    }
}
