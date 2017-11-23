package model.utils;

public interface Dictionary<K, T> {

    void add(K key, T value);
    void remove(K key);
    void update (K key, T value);
    T get (K key);
    boolean exists (K key);
    int size();


}
