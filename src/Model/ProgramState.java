package Model;

import Model.Containers.*;
import Model.Statements.Statement;
import Util.Pair;

import java.io.BufferedReader;

public class ProgramState {
    private int id;
    private IStack<Statement> exeStack;
    private IDictionary<String, Integer> symTable;
    private IFileTable<Pair<String, BufferedReader>> fileTable;
    private IHeap<Integer> heap;
    private StringBuffer output;
    private ILatchTable latchTable;

    public ProgramState(){
        exeStack = new ExecutionStack<>();
        symTable = new Dictionary<>();
        fileTable = new FileTable<>();
        heap = new Heap<>();
        output = new StringBuffer();
        latchTable = new LatchTable<>();
    }

    public int getID(){
        return id;
    }

    public void setID(int id){
        this.id = id;
    }

    public IHeap<Integer> getHeap() {
        return heap;
    }

    public void setHeap( IHeap<Integer> heap ){
        this.heap = heap;
    }

    public IStack<Statement> getExeStack() {
        return exeStack;
    }

    public void setSymTable( IDictionary<String, Integer> symTable ){
        this.symTable = symTable;
    }

    public void setExeStack( IStack<Statement> exeStack ){
        this.exeStack = exeStack;
    }

    public void setExeStack( Statement statement ) {
        IStack<Statement> newStack = new ExecutionStack<>();
        newStack.push(statement);
        this.exeStack = newStack;
    }

    public IDictionary<String, Integer> getSymTable() {
        return symTable;
    }

    public StringBuffer getOutputBuffer(){
        return output;
    }

    public IFileTable<Pair<String, BufferedReader>> getFileTable() {
        return fileTable;
    }

    public void setFileTable(IFileTable<Pair<String, BufferedReader>> fileTable){
        this.fileTable = fileTable;
    }

    public void appendOutput(String s){
        this.output.append(s);
    }

    public String getOutput() {
        return output.toString();
    }

    public void setOutputBuffer(StringBuffer stringBuffer){
        this.output = stringBuffer;
    }

    public ILatchTable<Integer> getLatchTable() {
        return latchTable;
    }

    public void setLatchTable (ILatchTable<Integer> newLatchTable) {
        this.latchTable = newLatchTable;
    }



    public String toString(){
        return  "\n================================================\n" +
                "ExeStack:\n" + exeStack.toString() +
                "\n================================================\n" +
                "SymLink:\n" + symTable.toString() +
                "\n================================================\n" +
                "FileTable:\n" + fileTable.toString() +
                "\n================================================\n" +
                "Heap:\n" + heap.toString() +
                "\n================================================\n" +
                "Output:\n" + output;
    }

}
