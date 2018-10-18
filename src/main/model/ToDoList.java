package main.model;

import main.Exceptions.OutOfIndexException;
import main.Exceptions.PassedDueDateException;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


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

    public void execute() throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);
        int numCrossed = 0;
        String operation;
        String name;
        int numItem;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
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
                    modifyingItem.setStatus(true);
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
                    insert(modifyingItem);
                }
                else{
                    System.out.println("Error: This item is already in the list.");
                }
            }
            else if (operation.equals("2")){
                while(true){
                    printList(todo, "todo");
                    System.out.println("Please select the number of element you want to cross off");
                    numItem = scanner.nextInt();
                    scanner.nextLine();
                    try{
                        modifyingItem = getModifyingItem(numItem);
                        modifyingItem.setStatus(false);
                        todo.remove(numItem-1);
                        done.add(modifyingItem);
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


    public Item getModifyingItem(int numItem) throws OutOfIndexException {
        if(numItem>todo.size()){
            throw new OutOfIndexException();
        }
        return todo.get(numItem-1);
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
    public ArrayList<Item> loadList(String fileName) throws IOException, ParseException {
        Scanner scanner = new Scanner(new File(fileName));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Item i;
        Date date;
        boolean status;
        String str = scanner.nextLine();
        while(str!=null) {
            i = new Item();
            i.setItemName(str);
            date = sdf.parse(scanner.nextLine());
            try{
                i.setDueDate(date);
            }catch(PassedDueDateException e){

            }
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
        Date dueDate;
        for(Item i : this.getTodo()){
            itemName = i.getItemName();
            dueDate = i.getDueDate();
            writer.println(itemName);
            writer.println(""+dueDate);
            writer.println(""+i.getStatus());
        }
        writer.close();
    }

    public abstract void insert(Item modifyingItem);


}
