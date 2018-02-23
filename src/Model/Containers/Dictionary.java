package Model.Containers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Dictionary<K, V> implements IDictionary<K, V> {

    private Map<K, V> content;

    public Dictionary(){
        content = new HashMap<>();
    }

    @Override
    public boolean containsKey(K key) {
        return this.content.containsKey(key);
    }

    @Override
    public boolean containsValue(V value) {
        return this.content.containsValue(value);
    }

    @Override
    public V get(K key) {
        return this.content.get(key);
    }

    @Override
    public V put(K key, V value) {
        return this.content.put(key, value);
    }

    @Override
    public Map<K, V> getContent() {
        Map<K, V> contentCopy = new HashMap<>();
        for (K key : this.content.keySet()){
            contentCopy.put(key, this.content.get(key));
        }
        return contentCopy;
    }

    @Override
    public void setContent(Map<K, V> content){
        this.content = content;
    }

    @Override
    public Set<K> keySet() {
        return content.keySet();
    }

    @Override
    public String toString(){
        return content.toString();
    }

    @Override
    public Dictionary copy(){
        Dictionary newDictionary = new Dictionary();
        newDictionary.setContent(this.getContent());
        return newDictionary;
    }
}
