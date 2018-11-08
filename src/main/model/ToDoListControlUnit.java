package main.model;

import main.Exceptions.PassedDueDateException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ToDoListControlUnit {
    private static final String LOADANDSAVEFILE = "File.txt";
    private ToDoList toDoList;
    private ToDoList doneList;
    private ToDoList overdueList;
    private Map<Item, ToDoList> listMap;

    public ToDoListControlUnit(int listType) {
        listMap = new HashMap<>();
        try{
            initializeUnit(listType);
        }catch(IOException e){
            printCannotFindFilePrompt(true);
        }
    }

    public void initializeUnit(int listType) throws IOException {
        if (listType == 1) {
            toDoList = new AlphabeticalList("toDoList");
            doneList = new AlphabeticalList("doneList");
            overdueList = new AlphabeticalList("overdueList");
        } else {
            toDoList = new ChronologicalList("toDoList");
            doneList = new AlphabeticalList("doneList");
            overdueList = new ChronologicalList("overdueList");
        }

        overdueList.setListOfItems(toDoList.loadList(LOADANDSAVEFILE));
        for (Item i : toDoList.getListOfItems()) {
            listMap.put(i, toDoList);
        }
        for (Item i : overdueList.getListOfItems()) {
            listMap.put(i, overdueList);
        }
    }

    public void execute(){
        Scanner scanner = new Scanner(System.in);
        int numCrossed = 0;
        String operation;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        while (true) {
            System.out.println("what would you like to do: [1] add a todo list item, " +
                    "[2] cross off an item in todo list [3] show all the items " +
                    "[4] check the status of a task [5] quit");
            operation = scanner.nextLine();

            if(operation.equals("1")){
                execute_addItem(scanner, sdf);
            }
            else if (operation.equals("2")){
                numCrossed = execute_crossOffItem(scanner, numCrossed);
            }
            else if (operation.equals("3")){
                execute_printAllLists();
            }
            else if(operation.equals("4")){
                execute_checkTaskStatus(scanner);
            }
            else if (operation.equals("5")){
                toDoList.printList(toDoList.getListName());
                System.out.println("Number of tasks done this time:" +numCrossed);
                try{
                    toDoList.saveList(LOADANDSAVEFILE);
                }catch(IOException e){
                    printCannotFindFilePrompt(false);
                }
                break;
            }
            else{
                System.out.println("Error: wrong user input. Please enter a whole number with [1,5]");
            }
        }
    }



    public void execute_addItem(Scanner scanner,SimpleDateFormat sdf){
        Item modifyingItem = new Item();
        Calendar date = Calendar.getInstance();
        printInputTaskNamePrompt();
        String itemName = scanner.nextLine();
        if(!toDoList.checkContain(itemName)){
            modifyingItem.setItemName(itemName);
            while(true) {
                System.out.println("Enter the due date of this item in YYYY-MM-DD");
                try {
                    date.setTime(sdf.parse(scanner.nextLine()));
                    modifyingItem.setDueDate(date);
                    break;
                } catch (ParseException p){
                    System.out.println("Wrong date format...");
                }catch(PassedDueDateException e){
                    System.out.println("Error: The due date is already passed.");
                }
            }
            toDoList.addItem(modifyingItem);
            listMap.put(modifyingItem,toDoList);
        }
        else{
            System.out.println("Error: This item is already in the list.");
        }
    }


    public int execute_crossOffItem(Scanner scanner, int numCrossed){
        Item modifyingItem;
        while(true){
            toDoList.printList(toDoList.getListName());
            System.out.println("Please select the number of element you want to cross off");
            int itemNumber = scanner.nextInt();
            scanner.nextLine();
                try{
                    modifyingItem = toDoList.getListOfItems().get(itemNumber-1);
                    toDoList.getListOfItems().remove(itemNumber-1);
                    listMap.remove(modifyingItem);
                    doneList.addItem(modifyingItem);
                    listMap.put(modifyingItem,doneList);
                    numCrossed ++;
                }catch(IndexOutOfBoundsException e){
                    System.out.println("Error: Out of index.");
                }finally{
                    System.out.println("Cross off is done.");
                }
                break;

        }
        return numCrossed;
    }


    public void execute_printAllLists() {
        toDoList.printList("todo list");
        System.out.println("");
        doneList.printList("done list");
        System.out.println("");
        overdueList.printList("overdue list");
        System.out.println("");
    }


    public void execute_checkTaskStatus(Scanner scanner){
        printInputTaskNamePrompt();
        String itemName = scanner.nextLine();
        Item checkingItem = new Item();
        checkingItem.setItemName(itemName);
        if(listMap.containsKey(checkingItem)){
            System.out.println("The task is in the "+listMap.get(checkingItem).getListName());
        }
        else{
            System.out.println("Sorry, the task is not present in any list.");
        }
    }


    public ToDoList getToDoList() {
        return toDoList;
    }

    public ToDoList getDoneList() {
        return doneList;
    }

    public ToDoList getOverdueList() {
        return overdueList;
    }

    public void printInputTaskNamePrompt(){
        System.out.println("Please enter the item text:");
    }

    //Effects: If purpose==true, file for loading is not found;
    //        if purpose==false, file for saving is not found.
    public void printCannotFindFilePrompt(boolean purpose){
        if(purpose)
            System.out.println("Cannot find the file for loading and saving. Loading step skipped.");
        else
            System.out.println("Cannot find the file for loading and saving. Saving step skipped.");
    }
}
