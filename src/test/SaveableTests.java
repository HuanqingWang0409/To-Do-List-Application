package test;

import main.Exceptions.PassedDueDateException;
import main.model.AlphabeticalList;
import main.model.Item;
import main.model.ToDoList;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SaveableTests {
    private static final String LOADANDSAVEFILE = "saveTest.txt";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    private ToDoList saveList = new AlphabeticalList("the list");
    private ArrayList<Item> todo = saveList.getListOfItems();


    @Test
    public void testSaveData() throws FileNotFoundException, UnsupportedEncodingException, ParseException, PassedDueDateException {
        Calendar date;

        Item item1 = new Item();
        item1.setItemName("test1");
        date = Calendar.getInstance();
        date.setTime(sdf.parse("2019-10-03"));
        item1.setDueDate(date);

        Item item2 = new Item();
        item2.setItemName("test2");
        date = Calendar.getInstance();
        date.setTime(sdf.parse("2019-10-04"));
        item2.setDueDate(date);

        Item item3 = new Item();
        item3.setItemName("test3");
        date = Calendar.getInstance();
        date.setTime(sdf.parse("2019-10-05"));
        item3.setDueDate(date);

        todo.add(item1);
        todo.add(item2);
        todo.add(item3);

        saveList.saveList(LOADANDSAVEFILE,sdf);
        assertTest(todo);
    }


    private void assertTest(ArrayList<Item> todo) throws FileNotFoundException, ParseException {
        Scanner scanner = new Scanner(new File("saveTest.txt"));
        String s;

        Item item1 = todo.get(0);
        Item item2 = todo.get(1);
        Item item3 = todo.get(2);

        assertTrue(item1.getItemName().equals(scanner.nextLine()));
        s = sdf.format(item1.getDueDate().getTime());
        assertTrue(s.equals(scanner.nextLine()));

        assertTrue(item2.getItemName().equals(scanner.nextLine()));
        s = sdf.format(item2.getDueDate().getTime());
        assertTrue(s.equals(scanner.nextLine()));

        assertTrue(item3.getItemName().equals(scanner.nextLine()));
        s = sdf.format(item3.getDueDate().getTime());
        assertTrue(s.equals(scanner.nextLine()));

    }
}
