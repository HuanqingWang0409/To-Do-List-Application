package main.model;

import java.util.ArrayList;
import main.tools.CustomComparator;

public class AlphabeticalList extends ToDoList{

    public AlphabeticalList(String name){
        super(name);
    }


    //Effect: insert the item in alphabetical order.
    @Override
    public void insert (Item modifyingItem) {
        ArrayList<Item> toDoList = getListOfItems();
        toDoList.add(modifyingItem);
        getListOfItems().sort(new CustomComparator());
    }
}
