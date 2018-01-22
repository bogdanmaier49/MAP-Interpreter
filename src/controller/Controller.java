package controller;

import exceptions.ControllerException;
import model.ProgramState;

import model.utils.FileData;
import repository.Repository;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.List;


public class Controller {

    private Repository<ProgramState> repository;
    private ExecutorService executor;







    public Controller (Repository<ProgramState> repository) {
        this.repository = repository;
    }








    Map<Integer,Integer> conservativeGarbageCollector(Collection<Integer> symTableValues, Map<Integer,Integer> heap){
        return heap.entrySet().stream().filter(e->symTableValues.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }









    List<ProgramState> removeCompletedPrg(List<ProgramState> inPrgList){
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }











    public void oneStepForAllPrg(List<ProgramState> prgList) throws ControllerException {
        List<Callable<ProgramState>> callList = prgList.stream()
                .map((ProgramState p)->(Callable<ProgramState>)(()->{return p.oneStep();}))
                .collect(Collectors.toList());

        try {
            List<ProgramState> newPrgList = executor.invokeAll(callList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            return null;
                            //throw new ControllerException("One Step Error: " + e.toString());
                        }
                    })
                    .filter(p -> p != null)
                    .collect(Collectors.toList());


            prgList.addAll(newPrgList);
            prgList.forEach(prg->repository.logProgramState(prg));

            repository.setProgramStateList(prgList);
        }catch(InterruptedException ie){
            throw new ControllerException("One Step Error: " + ie.toString());
        }

    }









    public void allStep() throws ControllerException {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> prgList = removeCompletedPrg(repository.getProgramStateList());

        while(prgList.size() > 0) {
            try {
                oneStepForAllPrg(prgList);
            } catch (ControllerException e) {
                throw new ControllerException("All Step Exception: " + e.toString());
            }
            prgList.forEach( p -> p.getHeap().setContent(conservativeGarbageCollector(p.getSymbolTable().getContent().values(),p.getHeap().getContent())));
            prgList = removeCompletedPrg(repository.getProgramStateList());
        }

        executor.shutdownNow();

        prgList.forEach(prg->repository.logProgramState(prg));

        try {
            List<ProgramState> tmpList = repository.getProgramStateList();
            for (ProgramState p : tmpList) {
                for (FileData fd : p.getFileTable().getValues()) {
                    fd.getFileDescriptor().close();
                }
            }
        }catch (IOException ie){
            System.out.println(ie.getMessage());
        }

        repository.setProgramStateList(prgList);
    }








    public Repository<ProgramState> getRepository() {
        return repository;
    }
}
