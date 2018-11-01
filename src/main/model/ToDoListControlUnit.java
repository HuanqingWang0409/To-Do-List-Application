package main.model;

import main.Exceptions.OutOfIndexException;
import main.Exceptions.PassedDueDateException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            System.out.println("Cannot find the file for loading and saving. Loading step skipped.");
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
        String name;
        int numItem;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        Item modifyingItem;

        while (true) {
            System.out.println("what would you like to do: [1] add a todo list item, " +
                    "[2] cross off an item in todo list [3] show all the items " +
                    "[4] check the status of a task [5] quit");
            operation = scanner.nextLine();

            if(operation.equals("1")){
                modifyingItem = new Item();
                System.out.println("Enter the item text:");
                name = scanner.nextLine();
                if(!toDoList.checkContain(name)){
                    modifyingItem.setItemName(name);
                    while(true) {
                        System.out.println("Enter the due date of this item in YYYY-MM-DD");
                        try {
                            date = sdf.parse(scanner.nextLine());
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

            else if (operation.equals("2")){
                while(true){
                    toDoList.printList(toDoList.getListName());
                    System.out.println("Please select the number of element you want to cross off");
                    numItem = scanner.nextInt();
                    scanner.nextLine();
                    try{
                        modifyingItem = getModifyingItem(numItem);
                        toDoList.getListOfItems().remove(numItem-1);
                        listMap.remove(modifyingItem);
                        doneList.addItem(modifyingItem);
                        listMap.put(modifyingItem,doneList);
                        numCrossed ++;
                        break;
                    }catch(OutOfIndexException e){
                        System.out.println("Error: The index of element is out of range.");
                    }finally {
                        System.out.println("Cross off is done.");
                    }
                }
            }
            else if (operation.equals("3")){
                toDoList.printList("todo list");
                System.out.println("");
                doneList.printList("done list");
                System.out.println("");
                overdueList.printList("overdue list");
                System.out.println("");
            }
            else if(operation.equals("4")){
                System.out.println("Please type the task name:");
                name = scanner.nextLine();
                Item checkingItem = new Item();
                checkingItem.setItemName(name);
                if(listMap.containsKey(checkingItem)){
                    System.out.println("The task is in the "+listMap.get(checkingItem).getListName());
                }
                else{
                    System.out.println("Sorry, the task is not present in any list.");
                }
            }
            else if (operation.equals("5")){
                toDoList.printList("listOfItems");
                System.out.println("Number of tasks done this time:" +numCrossed);
                try{
                    toDoList.saveList(LOADANDSAVEFILE);
                }catch(IOException e){
                    System.out.println("Cannot find the file for loading and saving. Saving step skipped.");
                }
                break;
            }
            else{
                System.out.println("Error: wrong user input. Please type a whole number with [1,5]");
            }
        }
    }


    public Item getModifyingItem(int numItem) throws OutOfIndexException {
        if(numItem> toDoList.getListOfItems().size()){
            throw new OutOfIndexException();
        }
        return  toDoList.getListOfItems().get(numItem-1);
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
}
