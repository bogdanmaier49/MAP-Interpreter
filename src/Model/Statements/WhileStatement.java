package Model.Statements;

import Model.Expressions.Expression;
import Model.ProgramState;

public class WhileStatement implements Statement {
   private Expression exp;
   private Statement stmt;

   public WhileStatement(Expression exp, Statement stmt){
       this.exp = exp;
       this.stmt = stmt;
   }

    @Override
    public ProgramState execute(ProgramState state) {
        if ( exp.evaluate(state) == 0){
            return null;
        }

        state.getExeStack().push(this);
        state.getExeStack().push(stmt);

        return null;
    }

    @Override
    public String toString(){
       return "while( " + exp.toString() + "){ " + stmt.toString() + "}";
    }
}
