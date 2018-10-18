package main.ui;

import main.Exceptions.OutOfGivenOptionsException;
import main.model.AlphabeticalList;
import main.model.ChronologicalList;
import main.model.ToDoList;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class ToDoListApp {
    public static void main(String[] args) throws IOException, ParseException, OutOfGivenOptionsException {
        ToDoList theList;
        Scanner scanner = new Scanner(System.in);;
        String operation;
        while (true){
            System.out.println("Please choose an order: [1] for alphabetical list ; [2] for chronological lsit.");
            operation = scanner.nextLine();
            if(operation.equals("1")) {
                theList = new AlphabeticalList();
                break;
            }
            if(operation.equals("2")) {
                theList = new ChronologicalList();
                break;
            }
            try {
                throw new OutOfGivenOptionsException();
            } catch (OutOfGivenOptionsException e) {
                System.out.println("Error: please enter number 1 or 2.");
            }
        }

        try{
            theList.execute();
        }catch (IOException e){
            System.out.println("Error: cannot find the file for loading and saving.");
        }
    }
}

