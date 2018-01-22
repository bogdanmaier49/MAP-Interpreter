package model.utils;

import java.util.HashMap;

public class FileTable<K,V> implements IFileTable<K,V> {

    private HashMap<K,V> fileTable = new HashMap<>();
    public FileTable(){};
    public V get(K key ){
        return fileTable.get(key);
    }

    public boolean contains(K key) {
        return fileTable.containsKey(key);
    }

    public void remove(K key){
        fileTable.remove(key);
    }

    public void add(K key,V value){
        fileTable.put(key,value);
    }

    public Iterable<K> getAll(){
        return fileTable.keySet();
    }

    public Iterable<V> getValues(){
        return fileTable.values();
    }

    @Override
    public String toString(){
        return fileTable.toString();
    }

}
