package model.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface Dictionary<K, T> {

    void add(K key, T value);
    void remove(K key);
    void update (K key, T value);
    T get (K key);
    boolean exists (K key);
    int size();

    public void setContent (Map<K, T> m);

    public HashMap<K, T> getContent ();

}
