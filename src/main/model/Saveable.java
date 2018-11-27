package main.model;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;


public interface Saveable {
    void saveList(String FileName, SimpleDateFormat sdf) throws FileNotFoundException, UnsupportedEncodingException;
}
