package model.utils;

import java.io.BufferedReader;

public class FileData {
    private String filename;
    private BufferedReader fileDescriptor;

    public String getFilename() {return filename;}
    public BufferedReader getFileDescriptor() {return fileDescriptor;}

    public FileData(String fn,BufferedReader fd){
        filename = fn;
        fileDescriptor = fd;
    }

    @Override
    public String toString(){
        return filename;
    }
}
