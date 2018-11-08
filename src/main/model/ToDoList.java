package main.model;

import main.Exceptions.PassedDueDateException;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;


public abstract class ToDoList implements Loadable, Saveable{

    private String listName;
    private ArrayList<Item> listOfItems;

    // Modifies: this.
    public ToDoList(String name) {
       listOfItems = new ArrayList<>();
       listName = name;
    }

    public void setListOfItems(ArrayList<Item> list){
        this.listOfItems=list;
    }

    public ArrayList<Item> getListOfItems() {
        return listOfItems;
    }

    public String getListName(){
        return listName;
    }

    public void removeItem(Item i){
        if(!listOfItems.contains(i)){
            listOfItems.remove(i);
            i.removeList(null);
        }
    }

    public void addItem(Item i){
        if(!listOfItems.contains(i)){
            listOfItems.add(i);
            i.addList(this);
        }
    }

    public boolean checkContain(String itemName){
        ArrayList<Item> items = listOfItems;
        for(Item i:items){
            if(itemName.equals(i.getItemName()))
                return true;
        }
        return false;
    }


    //Effects: print the whole list with item name, due date and status on user interface
    public void printList(String listName) {
        System.out.println("The " + listName + " is:");

        if(listOfItems.size()==0){
            System.out.println("An empty list");
            return;
        }

        int numItem = 1;
        for(Item n: listOfItems){
            System.out.print(""+numItem);
            System.out.print("   Task:"+ n.getItemName());
            System.out.println("   Due date: "+ n.getDueDate().toString());
            numItem++;
        }
    }


    //Effects: load the whole listOfItems list with item name, due date and status
    @Override
    public ArrayList<Item> loadList(String fileName) throws IOException {
        Scanner scanner = new Scanner(new File(fileName));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Item i;
        Calendar date = Calendar.getInstance();
        ArrayList<Item> overdueList = new ArrayList<>();
        String str = scanner.nextLine();
        while(str!=null) {
            i = new Item();
            i.setItemName(str);
            try{
                date.setTime(sdf.parse(scanner.nextLine()));
                i.setDueDate(date);
                insert(i);
            }catch(ParseException e){
                System.out.println("Error: The ["+((this.getListOfItems().size())+1)+"] item has wrong date format.");
                break;
            }catch(PassedDueDateException e){
                overdueList.add(i);
            }
            if(scanner.hasNext())
                str = scanner.nextLine();
            else
                break;
        }
        return overdueList;
    }


    //Effects: print the whole list with item name, due date and status to the file
    @Override
    public void saveList(String FileName) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(FileName,"UTF-8");
        String itemName;
        Calendar dueDate;
        for(Item i : this.getListOfItems()){
            itemName = i.getItemName();
            dueDate = i.getDueDate();
//            int year = dueDate.getYear();
//            String s = dueDate.toString();
//            String year = s.substring(s.length()-4);
            writer.println(itemName);
            writer.println(""+dueDate);
        }
        writer.close();
    }

    public abstract void insert(Item modifyingItem);

}
