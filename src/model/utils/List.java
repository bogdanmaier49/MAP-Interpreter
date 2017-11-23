package model.utils;

public interface List<T> {

    void add (T value);
    void remove (T value);
    void update (T oldValue, T newValue);
    int size();
    T get (int i);

}
