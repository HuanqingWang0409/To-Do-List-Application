package main.model;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import main.tools.CustomComparator;

public class AlphabeticalList extends ToDoList {

    public AlphabeticalList(){
        super();
    }

    @Override
    public void execute() throws IOException, ParseException {
        super.execute();
    }

    //Effect: insert the item in alphabetical order.
    @Override
    public void insert (Item modifyingItem) {
        ArrayList<Item> toDoList = getTodo();
        toDoList.add(modifyingItem);
        toDoList.sort(new CustomComparator());
    }
}
