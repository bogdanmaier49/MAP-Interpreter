package model.utils;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.stream.Stream;
import java.util.ArrayList;


public interface IList<T> {

    void add (T value);
    void remove (T value);
    void update (T oldValue, T newValue);
    int size();
    T get (int i);
    Stream<T> stream();
    ArrayList<T> getArrayList ();


}
