package model.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Heap implements IHeap<Integer> {

    private Map<Integer, Integer> heap;

    private int nextFreePosition;

    public Heap(){
        heap = new HashMap<>();
        nextFreePosition = 1;
    }

    private void getNextFreePosition(){
        while(heap.containsKey(nextFreePosition)){
            nextFreePosition++;
        }
    }

    @Override
    public int put(Integer value) {
        Integer index = nextFreePosition;
        heap.put(index, value);
        getNextFreePosition();
        return index;
    }

    @Override
    public int put(int key, Integer value) {
        heap.put(key, value);
        return key;
    }


    @Override
    public Integer get(int key) {
        return heap.get(key);
    }

    @Override
    public void remove(int key) {
        heap.remove(key);
    }

    @Override
    public boolean containsKey(int key) {
        return heap.containsKey(key);
    }

    @Override
    public boolean containsValue(Integer value) {
        return heap.containsValue(value);
    }

    @Override
    public void setContent(Map<Integer, Integer> content) {
        this.heap = content;
    }

    @Override
    public Map<Integer, Integer> getContent() {
        return heap;
    }

    @Override
    public Set<Integer> keySet() {
        return heap.keySet();
    }


    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for( int key : heap.keySet()){
            builder.append(String.valueOf(key) + "->" + String.valueOf(heap.get(key)) + "\n");
        }
        return builder.toString();
    }
}
