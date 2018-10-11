package test;

import main.model.AlphabeticalList;
import main.model.ToDoList;
import main.model.Item;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;


public class ToDoListTest {

    private ToDoList t = new AlphabeticalList();
    ArrayList<Item> todo;

    @BeforeEach
    public void runBefore(){
        todo = t.getTodo();
        Item i1 = new Item();
        i1.setItemName("name1");
        i1.setDueDate(180927);
        i1.setStatus(true);
        Item i2 = new Item();
        i2.setItemName("name2");
        i2.setDueDate(180928);
        i2.setStatus(false);
        todo.add(i1);
        todo.add(i2);
    }

    @Test
    public void testSameItem() {
        assertTrue(t.sameItem("name1", todo));
    }

    @Test
    public void testNotSameItem(){
        assertFalse(t.sameItem("name3",todo));
    }

}
