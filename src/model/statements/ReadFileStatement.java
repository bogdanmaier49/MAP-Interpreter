package model.statements;

import exceptions.ExpressionException;
import model.ProgramState;
import model.expressions.Expression;
import model.utils.Dictionary;
import model.utils.FileData;
import model.utils.IFileTable;

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
    public ProgramState execute (ProgramState state) {

        Dictionary<String, Integer> symbolTable = state.getSymbolTable();
        Dictionary<Integer, Integer> heap = state.getHeap();

        try {
            int value = fileID.evaluate(symbolTable, heap);

             if (! exists(state, value)) {
                 // TODO throw file not found exception
                 System.out.println("File not found");
             }

            try {
                BufferedReader buff = state.getFileTable().get(value).getFileDescriptor();
                String line = buff.readLine();

                int i;

                if (line.equals(null))
                    i = 0;
                else
                    i = Integer.parseInt(line);

                state.getSymbolTable().add(varName,i);

            }catch(IOException ioe){
                // TODO throw new Program State Exception (ioe.getMessage());
                System.out.println("Aci ");
            }

        } catch (ExpressionException e) {
            e.printStackTrace();
        }

        return state;
    }

    @Override
    public String toString () {
        return "readFile(" + fileID + ", " + varName + ")";
    }

}
