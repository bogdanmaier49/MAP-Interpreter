package controller;

import exceptions.StatementException;
import model.ProgramState;
import model.utils.Stack;
import model.statements.Statement;
import repository.Repository;

import java.io.FileOutputStream;

public class Controller {

    private Repository<ProgramState> repository;

    public Controller (Repository<ProgramState> repository) {
        this.repository = repository;
    }

    public ProgramState oneStep (ProgramState state) throws StatementException {
        Stack<Statement> executionStack = state.getExecutionStack();

        Statement current = executionStack.pop();

        try {
            return current.execute(state);
        } catch (StatementException e) {
            e.printStackTrace();
        }

        return state;
    }

    public void executeAll () throws StatementException {
        ProgramState state = repository.get(0);
        Stack<Statement> executionStack = state.getExecutionStack();

        while (! executionStack.isEmpty())
        {
            repository.logProgramState();

            /*
            state.getHeap().setContent(conservativeGarbageCollector(
                    state.getSymbolTable().getContent().values(),
                    state.getHeap().getContent()));
            */

            oneStep(state);
        }
        repository.logProgramState();
    }

    public Repository<ProgramState> getRepository () {
        return repository;
    }

}
