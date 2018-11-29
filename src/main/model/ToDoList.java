package main.model;

import main.Exceptions.PassedDueDateException;
import main.ObserverPattern.Observer;
import main.ObserverPattern.TaskMonitor;

import java.awt.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;


public class ToDoList extends Subject implements Loadable, Saveable{
    private String listName;
    private ArrayList<Item> listOfItems;

    // Modifies: this.
    public ToDoList(String name) {
        super();
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
            insert(i);
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

    public Calendar findTheItem(String itemName){
        Item findingName = new Item();
        findingName.setItemName(itemName);
        for(Item i: listOfItems){
            if(i.equals(findingName)){
                return i.getDueDate();
            }
        }
        return null;
    }


    @Override
    public void addObserver(Observer o){
        super.addObserver(o);
        if(o.getClass() == TaskMonitor.class){
            TaskMonitor addedTM = (TaskMonitor) o;
            TaskMonitor updatedTM;

            for(Observer updatedObserver: observers){
                if(updatedObserver.getClass() == TaskMonitor.class){
                    updatedTM = (TaskMonitor) updatedObserver;
                    if(updatedTM.getItem().getDueDate().before(addedTM.getItem().getDueDate()))
                        addedTM.setNumTasksBefore(addedTM.getNumTasksBefore() + 1);
                    else if(addedTM.getItem().getDueDate().before(updatedTM.getItem().getDueDate())) {
                        updatedTM.setNumTasksBefore(updatedTM.getNumTasksBefore() + 1);
                    }
                }
            }
        }
    }




    //Effects: print the whole list with item name, due date and status on user interface
    public void printListToGUI(String listName, SimpleDateFormat sdf, TextArea displayedMessage) {
        Calendar dueDate;
        displayedMessage.append("The " + listName + " is:\n");

        if(listOfItems.size()==0){
            displayedMessage.append("An empty list.\n");
            return;
        }

        int numItem = 1;
        for(Item n: listOfItems){
            displayedMessage.append("#"+numItem);
            displayedMessage.append("   Task name: "+ n.getItemName());
            dueDate = n.getDueDate();
            displayedMessage.append("     Due date: "+ sdf.format(dueDate.getTime()));
            displayedMessage.append("\n");
            numItem++;
        }
    }


    //Effects: load the whole listOfItems list with item name, due date and status
    //         return the list of overdue items
    @Override
    public ArrayList<Item> loadList(String fileName, SimpleDateFormat sdf) throws IOException {
        Scanner scanner = new Scanner(new File(fileName));
        Item i;
        Calendar date;
        ArrayList<Item> overdueList = new ArrayList<>();
        String str;
        if(scanner.hasNext()) {
            str = scanner.nextLine();
            while (str != null) {
                i = new Item();
                date = Calendar.getInstance();
                i.setItemName(str);
                try {
                    date.setTime(sdf.parse(scanner.nextLine()));
                    i.setDueDate(date);
                    insert(i);
                    addObserver(new TaskMonitor(i));
                } catch (ParseException e) {
                    System.out.println("Error: The [" + ((this.getListOfItems().size()) + 1) + "] item has wrong date format.");
                } catch (PassedDueDateException e) {
                    overdueList.add(i);
                }
                if (scanner.hasNext())
                    str = scanner.nextLine();
                else
                    break;
            }
        }
        return overdueList;
    }


    //Effects: print the whole list with item name, due date and status to the file
    @Override
    public void saveList(String FileName, SimpleDateFormat sdf) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(FileName,"UTF-8");
        String itemName;
        Calendar dueDate;
        for(Item i : this.getListOfItems()){
            itemName = i.getItemName();
            dueDate = i.getDueDate();
            writer.println(itemName);
            writer.println(sdf.format(dueDate.getTime()));
        }
        writer.close();
    }

    public void insert(Item modifyingItem){
        listOfItems.add(modifyingItem);
    }

}
