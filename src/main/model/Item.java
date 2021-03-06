package main.model;

import main.Exceptions.PassedDueDateException;
import java.util.Calendar;
import java.util.Objects;

public class Item {
    private String itemName;
    private Calendar dueDate;
    private ToDoList belongingList;


    public void Item(String name, Calendar date){
        this.itemName = name;
        this.dueDate = date;
        this.belongingList = null;
    }

    //Modifies:this
    //Effects: update the item name
    public void setItemName(String name){
        this.itemName = name;
    }

    //Modifies:this
    //Effects: update the due date of item
    public void setDueDate(Calendar date) throws PassedDueDateException {
        this.dueDate = date;
        Calendar now = Calendar.getInstance();
        if(date.before(now))
            throw new PassedDueDateException();
    }



    //Effects: return the item name
    public String getItemName(){
        return this.itemName;
    }

    //Effects: return the due date of the item
    public Calendar getDueDate(){
        return this.dueDate;
    }


    public ToDoList getBelongingList() {
        return belongingList;
    }


    public void removeList(ToDoList toDoList){
        if(belongingList!=toDoList){
            belongingList.removeItem(this);
            belongingList=toDoList;
        }
    }

    public void addList(ToDoList toDoList){
        if(belongingList!=toDoList){
            belongingList=toDoList;
            belongingList.addItem(this);

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(itemName, item.itemName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(itemName);
    }
}
