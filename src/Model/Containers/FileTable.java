package Model.Containers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class FileTable<V> implements IFileTable<V> {
    private int cnt;
    private Map<Integer, V> dict;

    public FileTable(){
        cnt = 0;
        dict = new HashMap<>();
    }

    @Override
    public int put(V value) {
        dict.put(cnt, value);
        return cnt++;
    }

    @Override
    public V get(int key) {
        return dict.get(key);
    }

    @Override
    public void remove(int key) {
        dict.remove(key);
    }

    @Override
    public Stream stream(){
        return dict.keySet().stream();
    }

    @Override
    public Set<Integer> keySet() {
        return dict.keySet();
    }

    @Override
    public String toString(){
        return dict.toString();
    }

}
