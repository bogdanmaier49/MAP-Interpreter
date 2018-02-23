package Model.Statements;

import Model.ProgramState;

public interface Statement {

     ProgramState execute(ProgramState state);
     String toString();

}
