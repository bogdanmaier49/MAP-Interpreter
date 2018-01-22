package model.statements;

import exceptions.StatementException;
import model.ProgramState;
import model.utils.*;

public class ForkStatement implements Statement{

    private Statement stm;

    public ForkStatement (Statement stm)
    {
        this.stm = stm;
    }

    @Override
    public ProgramState execute (ProgramState p) throws StatementException {
        IStack<Statement> newStack = new ExecutionStack();
        newStack.push(stm);

        Dictionary newTable = p.getSymbolTable().copy();
        IFileTable<Integer, FileData> ft = p.getFileTable();
        IHeap<Integer> heap = p.getHeap();
        IList<Integer> out = p.getOutput();

        ProgramState prSt = new ProgramState(newStack,out,newTable,ft,heap);

        prSt.setId(p.getId()*10);

        return prSt;
    }


    @Override
    public String toString () {
        return "fork(\n" + stm.toString() + " \n)";
    }


}
