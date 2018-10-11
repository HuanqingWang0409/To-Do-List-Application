package main.model;

import java.util.ArrayList;

public class ChronologicalList extends ToDoList {
    public ChronologicalList() {
        super();
    }

    //Effect: insert the item in increasing due date order.
    public void insert (Item modifyingItem){
        ArrayList<Item> toDoList = getTodo();
        int modifyingDate = (modifyingItem.getDueDate());
        int index = 0;
        for(Item i : toDoList){
            if(i.getDueDate() >= modifyingDate){
                toDoList.add(index,modifyingItem);
                return;
            }
            index++;
        }
        toDoList.add(modifyingItem);
    }


}
