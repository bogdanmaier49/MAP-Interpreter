package model.utils;

import java.util.ArrayList;
import java.util.stream.Stream;

public class OutputList<T> implements IList<T> {

    private ArrayList<T> arr;

    public OutputList () {
        arr = new ArrayList<>();
    }

    @Override
    public void add (T value) {
        arr.add(value);
    }

    @Override
    public void remove (T value) {
        arr.remove(value);
    }

    @Override
    public void update (T oldValue, T newValue) {
        arr.remove(oldValue);
        arr.add(newValue);
    }

    @Override
    public int size () {
        return arr.size();
    }

    @Override
    public T get (int i) {
        return arr.get(i);
    }

    @Override
    public String toString () {
        StringBuffer str = new StringBuffer();

        for (int i=0; i < arr.size(); i++)
             str.append(arr.get(i) + "\n");

        return str.toString();
    }

    @Override
    public Stream<T> stream (){
        return arr.stream();
    }

    @Override
    public ArrayList<T> getArrayList () {
        return this.arr;
    }

}
