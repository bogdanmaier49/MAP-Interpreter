package Model.Statements;

import Model.Expressions.Expression;
import Model.ProgramState;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements Statement {
    private Expression expFile;
    private String varName;

    public ReadFileStatement(Expression expFile, String varName){
        this.expFile = expFile;
        this.varName = varName;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        int fileID = expFile.evaluate(state);
        BufferedReader w = state.getFileTable().get(fileID).getSecond();

        String line;
        try{
            line = w.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Error while reading file " + e.getMessage());
        }

        int value = 0;
        if (line != null){
            value = Integer.valueOf(line);
        }

        state.getSymTable().put(varName, value);
        return null;
    }

    @Override
    public String toString() {
        return "readFile(\"" + expFile.toString() + "\", \"" + varName + ")";
    }
}
