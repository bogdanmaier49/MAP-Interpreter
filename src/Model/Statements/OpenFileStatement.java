package Model.Statements;

import Model.ProgramState;
import Util.Pair;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenFileStatement implements Statement {
    private String varName;
    private String fileName;
    private BufferedReader bufferedFileReader;

    public OpenFileStatement(String varName, String fileName) throws FileNotFoundException{
        this.varName = varName;
        this.fileName = fileName;

        bufferedFileReader = new BufferedReader(new FileReader(fileName));
    }

    @Override
    public ProgramState execute(ProgramState state) {
        int fileId = state.getFileTable().put(new Pair<>(fileName, bufferedFileReader));
        state.getSymTable().put(varName, fileId);

        return null;
    }

    @Override
    public String toString() {
        return "openRFile(" + varName + ", \"" + fileName + "\")";
    }
}
