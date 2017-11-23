package model.utils;

import model.statements.Statement;

import java.util.ArrayDeque;
import java.util.Deque;

public class ExecutionStack implements Stack<Statement> {

    private Deque<Statement> stack;

    public ExecutionStack () {
        stack = new ArrayDeque<>();
    }

    @Override
    public void push (Statement t) {
        stack.push(t);
    }

    @Override
    public Statement pop () {
        return stack.pop();
    }

    @Override
    public Statement first () {
        return stack.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString () {
        StringBuffer str = new StringBuffer();
        for(Statement i : stack){
            str.append("    " + i+"\n");
        }
        return str.toString();
    }
}
