package controller;

import exceptions.ProgramStateException;
import exceptions.StatementException;
import model.ProgramState;
import model.utils.FileData;
import model.utils.Stack;
import model.statements.Statement;
import repository.Repository;
import model.utils.Heap;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {

    private Repository<ProgramState> repository;

    public Controller (Repository<ProgramState> repository) {
        this.repository = repository;
    }

    public ProgramState oneStep (ProgramState state) throws StatementException, ProgramStateException {
        Stack<Statement> executionStack = state.getExecutionStack();

        Statement current = executionStack.pop();

        try {
            return current.execute(state);
        } catch (StatementException e) {
            throw new ProgramStateException("Failed to execute.");
        }
    }


    Map<Integer,Integer> conservativeGarbageCollector(Collection<Integer> symTableValues,
                                                      Map<Integer,Integer> heap)
    {
        return heap.entrySet().stream().filter(e->symTableValues.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void executeAll () throws StatementException, ProgramStateException {
        ProgramState state = repository.get(0);
        Stack<Statement> executionStack = state.getExecutionStack();

        try {

            while (!executionStack.isEmpty()) {
                repository.logProgramState();


                Heap h = (Heap) state.getHeap();
                h.setContent(conservativeGarbageCollector(
                        state.getSymbolTable().getContent().values(),
                        state.getHeap().getContent()));


                oneStep(state);
            }

        } catch (ProgramStateException e ) {
            throw e;
        } catch (StatementException e) {
            throw e;
        } finally {

                try {
                    for (FileData f: state.getFileTable().getValues())
                        f.getFileDescriptor().close();

                    repository.logProgramState();

                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
    }


    public Repository<ProgramState> getRepository () {
        return repository;
    }

}
