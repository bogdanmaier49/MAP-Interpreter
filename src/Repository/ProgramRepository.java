package Repository;

import Model.Program;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramRepository {
    private List<Program> toyProgramList;
    private String logFilePath;

    public ProgramRepository(Program prg, String logFilePath) throws IOException {
        this.logFilePath = logFilePath;
        toyProgramList = new ArrayList<>();
        toyProgramList.add(prg);
    }

    public List<Program> getPrgList(){ return toyProgramList; }

    public void setPrgList( List<Program> prgList){ this.toyProgramList = prgList;}

    public void logPrgState(Program prg)  {
        try(PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)))){
            logFile.println(prg.getID().toString() + "\n");
            logFile.println(prg.getState().toString()+"\n");
            logFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Program first(){
        return this.toyProgramList.get(0);
    }

    @Override
    public String toString() {
        return toyProgramList.toString();
    }
}
