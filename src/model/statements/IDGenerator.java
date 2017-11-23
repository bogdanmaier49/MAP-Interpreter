package model.statements;

public class IDGenerator {
    private static int fileIDs;
    private static int heapIDs;

    public static int generate () {
        fileIDs++;
        return fileIDs;
    }

    public static int generateHeapID () {
        heapIDs++;
        return heapIDs;
    }
}
