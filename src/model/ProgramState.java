package model;

import model.statements.Statement;
import model.utils.*;

import java.io.File;

public class ProgramState {

    private Dictionary<String, Integer> symbolTable;
    private Stack<Statement> executionStack;
    private List<Integer> output;
    private IFileTable<Integer,FileData> fileTable;
    private Dictionary<Integer, Integer> heap;

    public ProgramState (
            Dictionary<String, Integer> symbolTable,
            Stack<Statement> executionStack,
            List<Integer> output,
            IFileTable<Integer,FileData> fileTable,
            Dictionary<Integer, Integer> heap
    ) {
        this.symbolTable = symbolTable;
        this.executionStack = executionStack;
        this.output = output;
        this.fileTable = fileTable;
        this.heap = heap;
    }

    public Dictionary<String, Integer> getSymbolTable () {
        return this.symbolTable;
    }

    public Stack<Statement> getExecutionStack () {
        return this.executionStack;
    }

    public List<Integer> getOutput () {
        return this.output;
    }

    public IFileTable<Integer,FileData> getFileTable () {
        return this.fileTable;
    }

    public Dictionary<Integer, Integer> getHeap() {
        return heap;
    }

    @Override
    public String toString () {
        return  "======= Program State =======\n" +
                "Symbol Table:\n" + symbolTable + "\n" +
                "Execution Stack:\n" + executionStack + "\n" +
                "File Table:\n" + fileTable + "\n" +
                "Heap:\n" + heap + "\n" +
                "Output:\n" + output;
    }


}
