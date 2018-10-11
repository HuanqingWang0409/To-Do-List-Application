package main.ui;

import main.model.AlphabeticalList;
import main.model.ChronologicalList;
import main.model.ToDoList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class ToDoListApp {
    public static void main(String[] args) throws IOException {
        ToDoList theList;
        Scanner scanner = new Scanner(System.in);;
        String operation;
        System.out.println("Please choose an order: [1] for alphabetical list ; any other character for chronological lsit.");
        operation = scanner.nextLine();
        if(operation.equals("1"))
            theList = new AlphabeticalList();
        else
            theList = new ChronologicalList();
        theList.execute();
    }
}

