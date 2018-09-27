package test;

import main.model.ToDoList;
import main.model.Item;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;


public class ToDoListTest {
    private ArrayList<Item> list;

    @Before
    public void runBefore(){
    }

    @Test
    public void testSameItem(){
        list = new ArrayList<>();
        Item i1 = new Item();
        i1.setItemName("name1");
        i1.setDueDate(180927);
        i1.setStatus(true);
        Item i2 = new Item();
        i2.setItemName("name2");
        i2.setDueDate(180928);
        i2.setStatus(false);
        list.add(i1);
        list.add(i2);
        assertTrue(sameItem("name1",list));
    }

    @Test
    public void testNotSameItem(){
        list = new ArrayList<>();
        Item i1 = new Item();
        i1.setItemName("name1");
        i1.setDueDate(180927);
        i1.setStatus(true);
        Item i2 = new Item();
        i2.setItemName("name2");
        i2.setDueDate(180928);
        i2.setStatus(false);
        list.add(i1);
        list.add(i2);
        assertFalse(sameItem("name3",list));
    }




    //Effect: return whether the todo task is contained in the list.
    public boolean sameItem(String todo , ArrayList<Item> list){
        for(Item i:list){
            if(todo.equals(i.getItemName())){
                return true;
            }
        }
        return false;
    }
}
