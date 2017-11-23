package repository;

public interface Repository<T> {

    void add (T elem);
    void remove (T elem);
    int size ();
    T get (int i);
    T[] toArray ();
    void logProgramState();
    String getLogFilePath();

}
