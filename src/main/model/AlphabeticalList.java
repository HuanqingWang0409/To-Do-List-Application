package main.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlphabeticalList extends ToDoList {

    public AlphabeticalList(){
        super();
    }

    @Override
    public void execute() throws IOException {
        super.execute();
    }

    //Effect: insert the item in increasing length of name order.
    @Override
    public void insert (Item modifyingItem){
        ArrayList<Item> toDoList = getTodo();
        int modifyingLength = (modifyingItem.getItemName()).length();
        int index = 0;
        for(Item i : toDoList){
            if((i.getItemName()).length() >= modifyingLength){
                toDoList.add(index,modifyingItem);
                return;
            }
            index++;
        }
        toDoList.add(modifyingItem);
        }

}
