package main.model;

import java.util.ArrayList;
import java.util.Date;

public class ChronologicalList extends ToDoList {
    public ChronologicalList() {
        super();
    }

    //Effect: insert the item in increasing due date order.
    public void insert (Item modifyingItem){
        ArrayList<Item> toDoList = getTodo();
        Date modifyingDate = (modifyingItem.getDueDate());
        int index = 0;
        for(Item i : toDoList){
            if((i.getDueDate()).before(modifyingDate)){
                toDoList.add(index,modifyingItem);
                return;
            }
            index++;
        }
        toDoList.add(modifyingItem);
    }
}
