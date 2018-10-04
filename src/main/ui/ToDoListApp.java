package main.ui;

import main.model.ToDoList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ToDoListApp {
    public static void main(String[] args) throws IOException {
        ToDoList theList = new ToDoList();
        theList.execute();

    }
}

