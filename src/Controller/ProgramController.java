package Controller;

import Model.Containers.IStack;
import Model.Statements.Statement;
import Model.Program;
import Repository.ProgramRepository;
import Util.Pair;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class ProgramController {
    private ProgramRepository repo;
    private ExecutorService executor;
    private String output;
    private int globalID;

    public ProgramController(ProgramRepository repo){
        this.repo = repo;
        this.globalID = 1;
        this.output = "";
        executor = Executors.newFixedThreadPool(2);
    }

    public void oneStepFOrAllPrograms(List<Program> toyPrograms) throws InterruptedException {
        List<Callable<Program>> callList = toyPrograms.stream()
                .map((Program p) -> (Callable<Program>)(p::oneStep))
                .collect(Collectors.toList());

        List<Program> newToyPrograms = executor.invokeAll(callList).stream()
                .map(future -> {
                    try{
                        return future.get();
                    }
                    catch( Exception e ){
                        return null;
                    }
                })

                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        newToyPrograms.forEach(prg -> prg.setID(++globalID));
        toyPrograms.addAll(newToyPrograms);

        toyPrograms.forEach(prg -> repo.logPrgState(prg));

        repo.setPrgList(toyPrograms);
        this.output = repo.first().getOutput();
    }

    public void allStep() throws RuntimeException {
        List<Program> programs = removeCompletedPrograms(repo.getPrgList());

        while(programs.size() > 0 ){
            try {
                oneStepFOrAllPrograms(programs);
                programs = removeCompletedPrograms(repo.getPrgList());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        executor.shutdownNow();
        repo.setPrgList(programs);

    }

    private List<Program> removeCompletedPrograms(List<Program> programs){
        return programs.stream()
                .filter(p -> !p.completed())
                .collect(Collectors.toList());
    }

    public String getOutput(){
        return this.output;
    }

    public String toString(){
        return repo.first().toString();
    }

    public String getPrgStatesNr() {
        return String.valueOf(repo.getPrgList().size());
    }

    public List<Integer> getThreadIds(){
        List<Integer> result = new ArrayList<>();
        for (Program p : repo.getPrgList()){
            result.add(p.getID());
        }
        return result;
    }

    public Integer getAnyThreadId(){
        return repo.getPrgList().stream().map(Program::getID).findFirst().get();
    }

    public Program getThread(int threadID){
        return repo.getPrgList().stream().filter(e -> e.getID() == threadID).findFirst().get();
    }

    public List<String> getStack(int currentThread) {
        IStack<Statement> stack = getThread(currentThread).getState().getExeStack();
        return stack.values().stream().map(Statement::toString).collect(Collectors.toList());
    }

    public List<Pair<Integer, Integer>> getHeap(int currentThread) {
        List<Pair<Integer, Integer>> result = new ArrayList<>();
        Program thread = getThread(currentThread);
        for (Integer key : thread.getState().getHeap().keySet()){
            result.add(new Pair<>(key, thread.getState().getHeap().get(key)));
        }

        return result;
    }

    public List<Pair<String, Integer>> getSymTable(int currentThread) {
        List<Pair<String, Integer>> result = new ArrayList<>();
        Program thread = getThread(currentThread);
        for(String key : thread.getState().getSymTable().keySet()){
            result.add(new Pair<>(key, thread.getState().getSymTable().get(key)));
        }

        return result;
    }

    public List<Pair<Integer, String>> getFileTable(int currentThread) {
        List<Pair<Integer, String>> result = new ArrayList<>();
        Program thread = getThread(currentThread);
        for (Integer key : thread.getState().getFileTable().keySet()){
            result.add(new Pair<>(key, thread.getState().getFileTable().get(key).toString()));
        }

        return result;
    }

    public List<Pair<Integer, Integer>> getLatchTable (int currentThread) {
        List<Pair<Integer, Integer>> result = new ArrayList<>();
        Program thread = getThread(currentThread);
        for (Integer key : thread.getState().getHeap().keySet()){
            result.add(new Pair<>(key, thread.getState().getHeap().get(key)));
        }

        return result;
    }

    public List<Program> getPrograms(){
        return repo.getPrgList();
    }
}