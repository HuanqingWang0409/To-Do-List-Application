package main.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;


public abstract class ToDoList implements Loadable, Saveable{

    private static final String LOADANDSAVEFILE = "File.txt";
    private ArrayList<Item> todo;
    private ArrayList<Item> done;

    // Modifies: this.
    protected ToDoList() {
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
        todo = loadList(LOADANDSAVEFILE);
        while (true) {
            System.out.println("what would you like to do [1] add a to do list item, [2] cross off an item [3] show all the items [4] quit");
            operation = scanner.nextLine();
            if(operation.equals("1")){
                modifyingItem = new Item();
                System.out.println("Enter the item text:");
                name = scanner.nextLine();
                if(!(sameItem (name, todo))){
                    modifyingItem.setItemName(name);
                    System.out.println("Enter the due date of this item in YYYY/MM/DD/ in terms of integer");
                    date = scanner.nextInt();
                    scanner.nextLine();
                    modifyingItem.setDueDate(date);
                    modifyingItem.setStatus(true);
                    insert(modifyingItem);
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
                saveList(LOADANDSAVEFILE);
                break;
            }
        }
    }

    //Requires: String is either "todo" or "done"
    //Effects: print the whole list with item name, due date and status on user interface
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


    //Effects: load the whole todo list with item name, due date and status
    @Override
    public ArrayList<Item> loadList(String fileName) throws IOException {
        Scanner scanner = new Scanner(new File(fileName));
        Item i;
        int date;
        boolean status;
        String str = scanner.nextLine();
        while(str!=null) {
            i = new Item();
            i.setItemName(str);
            date = Integer.parseInt(scanner.nextLine());
            i.setDueDate(date);
            status = Boolean.parseBoolean(scanner.nextLine());
            i.setStatus(status);
            insert(i);
            if(scanner.hasNext())
                str = scanner.nextLine();
            else
                break;
        }

        return todo;
    }


    //Effects: print the whole list with item name, due date and status to the file
    @Override
    public void saveList(String FileName) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(FileName,"UTF-8");
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

    abstract void insert(Item modifyingItem);


}
