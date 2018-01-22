package model.statements;

import exceptions.ExpressionException;
import exceptions.StatementException;
import model.ProgramState;
import model.expressions.Expression;
import model.utils.FileData;
import model.utils.IFileTable;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseFileStatement implements Statement{

    private Expression expFile;

    public CloseFileStatement (Expression expFile) {
        this.expFile = expFile;
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
    public ProgramState execute (ProgramState state)throws StatementException {

        try {
            int value = expFile.evaluate(state.getSymbolTable(), state.getHeap());

            if(!exists(state,value)) {
                throw new StatementException ("File not found\n");
            }

            try {
                BufferedReader buff = state.getFileTable().get(value).getFileDescriptor();
                buff.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            state.getFileTable().remove(value);

        } catch (ExpressionException e) {
            throw new StatementException ("Could not close the file\n");
        }


        return null;
    }

    @Override
    public String toString () {
        return "closeFile("+expFile+")";
    }

}
