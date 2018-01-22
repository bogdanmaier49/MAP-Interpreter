package repository;

import model.ProgramState;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramStateRepository implements Repository<ProgramState> {

    private List<ProgramState> arr;
    private String logFilePath;

    public ProgramStateRepository (String logFilePath) {
        this.logFilePath = logFilePath;
        arr = new ArrayList<>();
    }

    @Override
    public void add (ProgramState elem) {
        arr.add(elem);
    }

    @Override
    public ProgramState get (int i){
        return arr.get(i);
    }

    @Override
    public void logProgramState (ProgramState ps) {

        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));

            pw.write("\n\n");
            pw.write(ps.toString());

            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<ProgramState> getProgramStateList (){
        return arr;
    }

    @Override
    public void setProgramStateList (List<ProgramState> programStates) {
        this.arr = programStates;
    }

}
