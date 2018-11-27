package test;

import main.model.AlphabeticalList;
import main.model.Item;
import main.model.Loadable;
import main.model.ToDoList;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertEquals;


public class LoadableTests {
    private static final String LOADANDSAVEFILE = "loadTest.txt";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    private ToDoList testList = new AlphabeticalList("todo list");
    private ArrayList<Item> todoList = testList.getListOfItems();
    private ArrayList<Item> overdueList = new ArrayList<>();


    @Test
    public void testLoadData () throws IOException, ParseException {
        overdueList = testLoad(testList);

        Item item = todoList.get(0);
        assertTrue(item.getItemName().equals("test1"));
        String s = sdf.format(item.getDueDate().getTime());
        assertTrue(s.equals("2019-10-03"));

        item = todoList.get(1);
        assertTrue(item.getItemName().equals("test2"));
        s = sdf.format(item.getDueDate().getTime());
        assertTrue(s.equals("2019-10-04"));

        item = todoList.get(2);
        assertTrue(item.getItemName().equals("test3"));
        s = sdf.format(item.getDueDate().getTime());
        assertTrue(s.equals("2019-10-05"));

        item = overdueList.get(0);
        assertTrue(item.getItemName().equals("test4"));
        s = sdf.format(item.getDueDate().getTime());
        assertTrue(s.equals("2017-09-01"));

        assertEquals(3,todoList.size());
        assertEquals(1,overdueList.size());
    }

    public ArrayList<Item> testLoad(Loadable loadable) throws IOException, ParseException {
        return loadable.loadList(LOADANDSAVEFILE,sdf);
    }

}
