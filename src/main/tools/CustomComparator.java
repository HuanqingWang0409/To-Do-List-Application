package main.tools;

import main.model.Item;

import java.util.Comparator;

public class CustomComparator implements Comparator<Item> {
    public int compare(Item object1, Item object2) {
        return object1.getItemName().compareTo(object2.getItemName());
    }
}

