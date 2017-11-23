package model.utils;

public interface Stack<T> {

    void push(T value);
    T pop ();
    T first ();
    boolean isEmpty ();

}
