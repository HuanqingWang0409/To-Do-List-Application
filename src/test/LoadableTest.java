package test;

import main.model.Item;
import main.model.Loadable;
import main.model.ToDoList;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertEquals;

public class LoadableTest {
    private ToDoList testList = new ToDoList();
    ArrayList<Item> todo = testList.getTodo();

    @Test
    public void testLoadData () throws IOException {

        testLoad(testList);

        Item item = todo.get(0);
        String str = "test1";
        assertTrue(str.equals(item.getItemName()));
        assertEquals(20181003,item.getDueDate());
        assertTrue(item.getStatus());

        item = todo.get(1);
        str = "test2";
        assertTrue(str.equals(item.getItemName()));
        assertEquals(20181004,item.getDueDate());
        assertTrue(item.getStatus());

        item = todo.get(2);
        str = "test3";
        assertTrue(str.equals(item.getItemName()));
        assertEquals(20181005,item.getDueDate());
        assertTrue(!item.getStatus());

    }

    public void testLoad(Loadable loadable) throws IOException {
        loadable.loadList();
    }

}
