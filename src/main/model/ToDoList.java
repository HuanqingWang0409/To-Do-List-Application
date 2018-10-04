package main.model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

public class ToDoList implements Loadable, Saveable{

    private ArrayList<Item> todo;
    private ArrayList<Item> done;

    // Modifies: this.
    public ToDoList() {
       todo = new ArrayList<>();
       done = new ArrayList<>();
    }
    public ArrayList<Item> getTodo() {
        return todo;
    }

    public ArrayList<Item> getDone() {
        return done;
    }

    public void execute() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int numCrossed = 0;
        String operation;
        String name;
        int numItem;
        int date;
        Item modifyingItem;
        //todo = loadList();
        while (true) {
            System.out.println("what would you like to do [1] add a to do list item, [2] cross off an item [3] show all the items [4] quit");
            operation = scanner.nextLine();
            if(operation.equals("1")){
                modifyingItem = new Item();
                System.out.println("Enter the item text:");
                name = scanner.nextLine();
                if(!(sameItem (name, todo))){
                    todo.add(modifyingItem);
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
                printList(todo, "todo");
                System.out.println("Please select the number of element you want to cross off");
                numItem = scanner.nextInt();
                scanner.nextLine();
                if(numItem> todo.size()){
                    System.out.println("Error: The index of element is out of range.");
                }
                else{
                    modifyingItem = todo.get(numItem-1);
                    modifyingItem.setStatus(false);
                    todo.remove(numItem-1);
                    done.add(modifyingItem);
                    numCrossed ++;
                    System.out.println("Cross off is done.");
                }
            }
            else if (operation.equals("3")){
                printList(todo, "todo");
                System.out.println("");
                printList(done, "done");
                System.out.println("");
            }
            else if (operation.equals("4")){
                printList(todo, "todo");
                System.out.println("Number of tasks done this time:" +numCrossed);
                printList();
                break;
            }
        }
    }

    //Requires: String is either "todo" or "done"
    //Effects: print the whole list with item name, due date and status
    public void printList(ArrayList<Item> data , String listName) {

        if(listName.equals("todo")){
            System.out.println("The to-do list is:");
        }
        else{
            System.out.println("The done list is:");
        }

        if(data.size()==0){
            System.out.println("Empty list");
            return;
        }

        int numItem = 1;
        for(Item n: data){
            System.out.print(""+numItem);
            System.out.print("   Task:"+ n.getItemName());
            System.out.print("   Due date in YY/MM/DD: "+ n.getDueDate());
            if(n.getStatus()){
                System.out.println("   Status: Have not done yet.");
            }
            else{
                System.out.println("   Status: Already done.");
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


    @Override
    public ArrayList<Item> loadList() throws IOException {
        Scanner scanner = new Scanner(new File("File.txt"));
        Item i;
        String str = "";
        int date;
        int turn = 1;
        while(scanner.nextLine()!=null) {
            i = new Item();
            todo.add(i);
            i.setItemName(scanner.nextLine());
            //i.setDueDate(scanner.nextInt());
            //i.setStatus(scanner.nextBoolean());
        }
//        List<String> lines = Files.readAllLines(Paths.get("file.txt"));
//        for(String line : lines){
//            if(turn % 2 ==1){
//                i = new Item();
//                todo.add(i);
//                i.setItemName(line);
//            }
//            else{
//                i.setDueDate(scanner.nextInt());
//            }

//        for(int n = 0 ; !(lines.get(n)==null) ; n++){
//            str = lines.get(n);
//
//                i = new Item();
//                todo.add(i);
//                i.setItemName(str);
////                date = parseInt(scanner.nextLine());
////                i.setDueDate(date);
//                i.setStatus(parseBoolean(scanner.nextLine()));
//        }
        return todo;
    }

    @Override
    public void printList() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter("File.txt","UTF-8");
        String itemName;
        int dueDate;
        for(Item i : this.getTodo()){
            itemName = i.getItemName();
            dueDate = i.getDueDate();
            writer.println(itemName);
            writer.println(""+dueDate);
            writer.println(""+i.getStatus());
        }
        writer.close();
    }
}
