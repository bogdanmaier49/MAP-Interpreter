package repository;

import model.ProgramState;
import model.statements.Statement;

import java.io.*;
import java.util.ArrayList;

public class ProgramStateRepository implements Repository<ProgramState> {

    private ArrayList<ProgramState> arr;
    private String logFilePath;

    public ProgramStateRepository (String logFilePath) {
        this.logFilePath = logFilePath;
        arr = new ArrayList<ProgramState>();
    }

    @Override
    public void add (ProgramState elem) {
        arr.add(elem);
    }

    @Override
    public void remove(ProgramState elem) {
        arr.remove(elem);
    }

    @Override
    public int size (){
        return arr.size();
    }

    @Override
    public ProgramState get (int i){
        return arr.get(i);
    }

    @Override
    public ProgramState[] toArray (){
        return (ProgramState[]) arr.toArray();
    }

    @Override
    public void logProgramState () {

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));

            pw.write("\n\n");
            pw.write(arr.get(0).toString());

            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getLogFilePath () {
        return logFilePath;
    }

}
