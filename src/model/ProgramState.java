package model;

import exceptions.StatementException;
import model.statements.IDGenerator;
import model.statements.Statement;
import model.utils.*;

public class ProgramState {
    private int id;
    private IStack<Statement> exec;
    private IList<Integer> messages;
    private Dictionary<String,Integer> symbolT;
    private IFileTable<Integer, FileData> fileTable;
    private IHeap<Integer> heap;

    public ProgramState(
            IStack<Statement> exec,
            IList<Integer> messages,
            Dictionary<String,Integer> symbolT,
            IFileTable<Integer, FileData> fileTable,
            IHeap<Integer> heap
    ){
        id = IDGenerator.generate();
        this.exec = exec;
        this.messages = messages;
        this.symbolT = symbolT;
        this.fileTable = fileTable;
        this.heap = heap;
    }

    public int getId(){ return id; }

    public void setId(int ID){ id = ID; }

    public IStack<Statement> getExecutionStack(){
        return exec;
    }

    public IList<Integer> getOutput(){
        return messages;
    }

    public Dictionary<String,Integer> getSymbolTable(){
        return symbolT;
    }

    public IFileTable<Integer,FileData> getFileTable() {return fileTable;}

    public IHeap<Integer> getHeap() {return heap;}

    @Override
    public String toString(){
        StringBuffer buff = new StringBuffer();

        buff.append("================= Program State =================\n");
        buff.append("ID: " + id + "\n\n");
        buff.append("ExecutionStack:\n" + exec.toString() + "\n");
        buff.append("Heap:\n" + heap.toString() + "\n");
        buff.append("SymbolTable:\n" + symbolT.toString() + "\n");
        buff.append("FileTable:\n" + fileTable.toString() + "\n\n");
        buff.append("Output:\n" + messages.toString() + "\n");

        return buff.toString();
    }

    public Boolean isNotCompleted(){
        if (exec.empty())
            return false;
        return true;
    }

    public ProgramState oneStep() {
        if(getExecutionStack().empty()) {
            System.out.println("Empty stack");
            return null;
        }

        Statement stmt = getExecutionStack().pop();

        try {
            return stmt.execute(this);
        } catch (StatementException e) {
            e.printStackTrace();
        }

        return  null;
    }
}
