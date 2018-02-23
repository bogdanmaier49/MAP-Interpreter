package Model.Expressions;

import Model.ProgramState;

public interface Expression {
    int evaluate(ProgramState state);
}
