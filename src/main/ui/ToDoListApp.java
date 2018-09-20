package main.ui;

import main.model.ToDoList;

public class ToDoListApp {
    public static void main(String[] args) {
        ToDoList theList = new ToDoList();

        entireList(theList);
        toDoTasksNum(theList);
    }

    public static void entireList(ToDoList theList) {
        System.out.println("The to-do list is the following:" +theList);
    }

    public static void toDoTasksNum(ToDoList theList) {
        System.out.println("Well Done!!" );
    }
}

