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
    private ToDoList testList = new AlphabeticalList("the list");
    ArrayList<Item> todo = testList.getListOfItems();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");

    @Test
    public void testLoadData () throws IOException, ParseException {

        testLoad(testList);

        Item item = todo.get(0);
        String str = "test1";
        assertTrue(str.equals(item.getItemName()));
        assertEquals(sdf.parse("2018-10-03"),item.getDueDate());

        item = todo.get(1);
        str = "test2";
        assertTrue(str.equals(item.getItemName()));
        assertEquals(sdf.parse("2018-10-04"),item.getDueDate());

        item = todo.get(2);
        str = "test3";
        assertTrue(str.equals(item.getItemName()));
        assertEquals(sdf.parse("2018-10-305"),item.getDueDate());
    }

    public void testLoad(Loadable loadable) throws IOException, ParseException {
        loadable.loadList(LOADANDSAVEFILE);
    }

}
