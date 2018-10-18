package main.model;

import main.Exceptions.PassedDueDateException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Item {
    private String itemName;
    private Date dueDate;
    private boolean status;

    public void Item(String name, Date date, boolean status){
        this.itemName = name;
        this.dueDate = date;
        this.status = status;
    }
    //Modifies:this
    //Effects: update the item name
    public void setItemName(String name){
        this.itemName = name;
    }

    //Modifies:this
    //Effects: update the due date of item
    public void setDueDate(Date date) throws PassedDueDateException {
        this.dueDate = date;
        Date now = new Date();
        if(date.before(now))
            throw new PassedDueDateException();
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
    public Date getDueDate(){
        return this.dueDate;
    }

    //Effects: return the status of the item
    public boolean getStatus(){
        return this.status;
    }
}
