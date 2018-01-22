package repository;

import model.ProgramState;

import java.util.List;

public interface Repository<T> {

    void add (T elem);
    T get (int i);
    void logProgramState(ProgramState ps);
    java.util.List<ProgramState> getProgramStateList ();
    void setProgramStateList (List<ProgramState> programStates);

}
