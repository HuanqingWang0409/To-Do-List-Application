package main.model;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public interface Saveable {
    void saveList(String FileName) throws FileNotFoundException, UnsupportedEncodingException;
}
