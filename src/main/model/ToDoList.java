package main.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoList {

    private int numCrossed = 0;
    private Scanner scanner = new Scanner(System.in);

    // Modifies: this.
    public ToDoList() {

        ArrayList<Item> data = new ArrayList<>();
        String operation;
        String name;
        int numItem;
        int date;
        Item modifyingItem;
        while (true) {
            System.out.println("what would you like to do [1] add a to do list item, [2] cross off an item [3] show all the items [4] quit");
            operation = scanner.nextLine();
            if(operation.equals("1")){
                modifyingItem = new Item();
                System.out.println("Enter the item text:");
                name = scanner.nextLine();
               if(!(sameItem (name,data))){
                   data.add(modifyingItem);
                   modifyingItem.setItemName(name);
                    System.out.println("Enter the due date of this item in YYYY/MM/DD/ in terms of integer");
                    date = scanner.nextInt();
                    scanner.nextLine();
                    modifyingItem.setDueDate(date);
                   modifyingItem.setStatus(true);
                }
                else{
                    System.out.println("Error: This item is already in the list.");
                }
            }
            else if (operation.equals("2")){
                printList(data);
                System.out.println("Please select the number of element you want to cross off");
                numItem = scanner.nextInt();
                scanner.nextLine();
                if(numItem>data.size()){
                    System.out.println("Error: The index of element is out of range.");
                }
                else{
                    modifyingItem = data.get(numItem-1);
                    modifyingItem.setStatus(false);
                    numCrossed ++;
                    System.out.println("Cross off is done.");
                }
            }
            else if (operation.equals("3")){
                printList(data);
            }
            else if (operation.equals("4")){
                System.out.println("Number of tasks done:" +numCrossed);
                break;
            }
        }
    }


    //Effects: print the whole to-do list with item name, due date and status
    public void printList(ArrayList<Item> data) {
        int numItem = 1;
        System.out.println("The to-do list is:");
        for(Item n: data){
            System.out.print(""+numItem);
            System.out.print("   Task:"+ n.getItemName());
            System.out.print("   Due date in YY/MM/DD: "+ n.getDueDate());
            if(n.getStatus()){
                System.out.println("   Status: Have not done yet.");
            }
            else{
                System.out.println("   Already done.");
            }
            numItem++;
        }
    }


    //Effect: return whether the todo task is contained in the list.
    public boolean sameItem(String todo , ArrayList<Item> list){
        for(Item i:list){
           if(todo.equals(i.getItemName())){
               return true;
           }
        }
        return false;
    }
}
