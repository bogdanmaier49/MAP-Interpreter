package model.statements;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.ProgramState;
import model.expressions.Expression;
import model.utils.Dictionary;
import model.utils.FileData;
import model.utils.IFileTable;
import model.utils.IHeap;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements Statement {

    private Expression fileID;
    private String varName;

    public ReadFileStatement (Expression fileID, String varName) {
        this.fileID = fileID;
        this.varName = varName;
    }

    private boolean exists(ProgramState p, int i)
    {
        IFileTable<Integer,FileData> ft = p.getFileTable();
        for(FileData key : ft.getValues())
        {
            if(key.getFileDescriptor() != null)
                return true;
        }
        return false;
    }

    @Override
    public ProgramState execute (ProgramState state) throws StatementException {

        Dictionary<String, Integer> symbolTable = state.getSymbolTable();
        IHeap<Integer> heap = state.getHeap();


        int value = 0;

        try {
            value = fileID.evaluate(symbolTable, heap);
        } catch (ExpressionException e) {
            e.printStackTrace();
        }

        if (! exists(state, value)) {
            throw new StatementException("File not found!");
        }

        BufferedReader buff = state.getFileTable().get(value).getFileDescriptor();
        String line = null;

        try {
            line = buff.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int i;

        if (line.equals(null))
            i = 0;
        else
            i = Integer.parseInt(line);

        symbolTable.put(varName,i);

        return null;
    }

    @Override
    public String toString () {
        return "readFile(" + fileID + ", " + varName + ")";
    }

}
