package model.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Heap implements Dictionary<Integer, Integer> {
    private HashMap<Integer, Integer> table;

    public Heap () {
        table = new HashMap<>();
    }

    @Override
    public void add (Integer key, Integer value) {
        table.put(key, value);
    }

    @Override
    public void remove (Integer key) {
        table.remove(key);
    }

    @Override
    public Integer get (Integer key) {
        return table.get(key);
    }

    @Override
    public boolean exists (Integer key) {
        return table.containsKey(key);
    }

    @Override
    public int size () {
        return table.size();
    }

    @Override
    public void update (Integer key, Integer value) {
        table.put(key, value);
    }

    @Override
    public String toString () {
        StringBuffer str = new StringBuffer();

        for(Map.Entry<Integer, Integer> e : table.entrySet()){
            str.append("    " + e.getKey() + ":" + e.getValue() + "\n");
        }

        return str.toString();
    }

    public void setContent (Map<Integer, Integer> m) {
        table = (HashMap<Integer, Integer>)m;
    }

    public HashMap<Integer, Integer> getContent () {
        return table;
    }
}
