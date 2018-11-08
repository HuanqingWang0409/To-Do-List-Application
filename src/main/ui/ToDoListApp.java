package main.ui;

import main.Exceptions.OutOfGivenOptionsException;
import main.model.AlphabeticalList;
import main.model.ChronologicalList;
import main.model.ToDoList;
import main.model.ToDoListControlUnit;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class ToDoListApp {
    public static void main(String[] args) throws IOException, ParseException, OutOfGivenOptionsException {
        ToDoListControlUnit theList;
        Scanner scanner = new Scanner(System.in);

        String operation;
        while (true) {
            System.out.println("Please choose an order: [1] for alphabetical list ; [2] for chronological lsit.");
            operation = scanner.nextLine();
                if (operation.equals("1")) {
                    theList = new ToDoListControlUnit(1);
                    theList.execute();
                    break;
                }
                if (operation.equals("2")) {
                    theList = new ToDoListControlUnit(2);
                    theList.execute();
                    break;
                }
            System.out.println("Error: please enter number 1 or 2.");
        }
    }
}

