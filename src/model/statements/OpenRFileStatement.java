package model.statements;

import exceptions.ProgramStateException;
import exceptions.StatementException;
import model.ProgramState;
import model.utils.FileData;
import model.utils.IFileTable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenRFileStatement implements Statement {

    private String fileName;
    private String varName;

    public OpenRFileStatement (String fileName, String varName) {
        this.fileName = fileName;
        this.varName = varName;
    }

    private boolean exists (ProgramState programState, String fileName) {

        IFileTable<Integer, FileData> fileTable = programState.getFileTable();
        for (FileData key : fileTable.getValues())
            if (key.getFilename().equals(fileName))
                return true;

        return false;
    }

    @Override
    public ProgramState execute (ProgramState state) throws StatementException {

        if (exists(state, this.fileName)) {
            throw new StatementException("File: " + fileName + " already open");
        }

        try {

            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            FileData fd = new FileData(fileName, reader);
            int id = IDGenerator.generate();
            state.getFileTable().add(id,fd);

            state.getSymbolTable().put(varName, id);

        } catch (IOException e) {
            throw new StatementException(e.toString());
        }

        return  null;
    }

    @Override
    public String toString () {
        return "openFile(" + varName + ", " + fileName + ")";
    }

}
