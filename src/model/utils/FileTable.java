package model.utils;

import java.util.HashMap;

public class FileTable<K,V> implements IFileTable<K,V> {

    private HashMap<K,V> fileTable;


    public FileTable () {
        fileTable = new HashMap<>();
    }


    @Override
    public V get(K key){
        return fileTable.get(key);
    }

    @Override
    public boolean contains(K key) {
        return fileTable.containsKey(key);
    }

    @Override
    public void remove(K key) {
        fileTable.remove(key);
    }

    @Override
    public void add(K key,V value) {
        fileTable.put(key,value);
    }

    @Override
    public Iterable<K> getAll() {
        return fileTable.keySet();
    }

    @Override
    public Iterable<V> getValues() {
        return fileTable.values();
    }

    @Override
    public String toString(){
        return fileTable.toString();
    }

}
