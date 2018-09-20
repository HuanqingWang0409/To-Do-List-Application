package main.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoList {

    private int numCrossed = 0;
    Scanner scanner = new Scanner(System.in);

    // EFFECTS: set is empty
    public ToDoList() {

        ArrayList<String> data = new ArrayList<>();
        String operation;
        String newList;
        int numItem;
        while (true) {
            System.out.println("what would you like to do [1] add a to do list item, [2] cross off an item [3] show all the items [4] quit");
            operation = scanner.nextLine();
            if(operation.equals("1")){
                System.out.println("Enter the item text:");
                newList = scanner.nextLine();
                if(!(sameItem (newList,data))){
                    data.add(newList);
                }
            }
            else if (operation.equals("2")){
                System.out.println("to-do list: "+data);
                System.out.println("Please select the number of element you want to cross off");
                numItem = scanner.nextInt();
                data.remove(numItem -1);
            }
            else if (operation.equals("3")){
                System.out.println("to-do list: "+data);
            }
            else if (operation.equals("4")){
                break;
            }
        }
    }
    public boolean sameItem(String todo , ArrayList list){
        return list.contains(todo);
    }
}
