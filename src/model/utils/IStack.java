package model.utils;

import java.util.List;

public interface IStack<T> {

    boolean empty();
    T peek();
    T pop();
    T push(T item);
    int search(Object o);
    List<T> values();

}
