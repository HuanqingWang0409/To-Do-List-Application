package main.ui;

import main.model.ToDoListControlUnit;

import java.io.IOException;
import java.util.Scanner;

public class ToDoListApp {
    public static void main(String[] args) {
        try{
            ReadWebPageEx.main(args);
        }catch(IOException e){
            System.out.println("Error: Cannot find the web-page!");
        }

        ToDoListControlUnit theList;
        Scanner scanner = new Scanner(System.in);

        String operation;
        while (true) {
            System.out.println("Please choose the order of your list: [1] for alphabetical list ; [2] for chronological lsit.");
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

