package main.model;

import java.text.SimpleDateFormat;

public class Item {
    private String itemName;
    private int dueDate;
    private boolean status;

    //Modifies:this
    //Effects: update the item name
    public void setItemName(String name){
        this.itemName = name;
    }

    //Modifies:this
    //Effects: update the due date of item
    public void setDueDate(int date){
        this.dueDate = date;
    }

    //Modifies:this
    //Effects: update the status of item
    public void setStatus(boolean status){
        this.status = status;
    }

    //Effects: return the item name
    public String getItemName(){
        return this.itemName;
    }

    //Effects: return the due date of the item
    public int getDueDate(){
        return this.dueDate;
    }

    //Effects: return the status of the item
    public boolean getStatus(){
        return this.status;
    }
}
