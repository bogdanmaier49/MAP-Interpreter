package Model.Statements;

import Model.Expressions.Expression;
import Model.ProgramState;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseFileStatement implements Statement {
    private Expression expFile;

    public CloseFileStatement(Expression expFile){
        this.expFile = expFile;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        int fileId = expFile.evaluate(state);
        BufferedReader r = state.getFileTable().get(fileId).getSecond();
        try{
            r.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        state.getFileTable().remove(fileId);

        return null;
    }

    @Override
    public String toString() {
        return "closeFile(\"" + expFile.toString() + "\")";
    }
}
