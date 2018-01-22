package model.utils;

import model.statements.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ExecutionStack implements IStack<Statement> {
    private Stack<Statement> stack;

    public ExecutionStack(){
        stack = new Stack<>();
    }

    @Override
    public boolean empty() {
        return this.stack.empty();
    }

    @Override
    public Statement peek() {
        return this.stack.peek();
    }

    @Override
    public Statement pop() {
        return this.stack.pop();
    }

    @Override
    public Statement push(Statement item) {
        return this.stack.push(item);
    }

    @Override
    public int search(Object o) {
        return this.stack.search(o);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Stack<Statement> aux = (Stack<Statement>) stack.clone();

        while(!aux.empty()){
            result.append(aux.pop().toString())
                    .append("\n");
        }

        return result.toString();
    }

    @Override
    public List<Statement> values(){
        List<Statement> result = new ArrayList<>();
        Stack<Statement> aux = (Stack<Statement>) stack.clone();

        while(!aux.empty()){
            result.add(aux.pop());
        }

        return result;
    }
}
