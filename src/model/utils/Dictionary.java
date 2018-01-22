package model.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public interface Dictionary<K, V> {

    boolean containsKey(K key);
    boolean containsValue(V value);

    V get(K key);
    V put(K key, V value);

    Map<K, V> getContent();
    void setContent(Map<K, V> content);

    Set<K> keySet();
    Dictionary copy();

}
