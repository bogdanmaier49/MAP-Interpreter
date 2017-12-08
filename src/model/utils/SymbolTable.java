package model.utils;


import model.utils.Dictionary;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SymbolTable implements Dictionary<String,Integer> {

    private HashMap<String, Integer> table;

    public SymbolTable () {
        table = new HashMap<>();
    }

    @Override
    public void add (String key, Integer value) {
        table.put(key, value);
    }

    @Override
    public void remove (String key) {
        table.remove(key);
    }

    @Override
    public Integer get (String key) {
        return table.get(key);
    }

    @Override
    public boolean exists (String key) {
        return table.containsKey(key);
    }

    @Override
    public int size () {
        return table.size();
    }

    @Override
    public void update (String key, Integer value) {
        table.put(key, value);
    }

    @Override
    public String toString () {
        StringBuffer str = new StringBuffer();

        for(Map.Entry<String, Integer> e : table.entrySet()){
            str.append("    " + e.getKey() + ":" + e.getValue() + "\n");
        }

        return str.toString();
    }

    @Override
    public void setContent (Map<String, Integer> m) {
        table =  (HashMap<String, Integer>) m;
    }

    @Override
    public HashMap<String, Integer> getContent () {
        return table;
    }

}
