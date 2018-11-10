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
import java.util.Date;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SaveableTests {
    private static final String LOADANDSAVEFILE = "saveTest.txt";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private ToDoList saveList = new AlphabeticalList("the list");
    private ArrayList<Item> todo = saveList.getListOfItems();
    private SimpleDateFormat sdf;
    @Test
    public void testSaveData() throws FileNotFoundException, UnsupportedEncodingException, ParseException, PassedDueDateException {
        Item item1 = new Item();
        Calendar date = Calendar.getInstance();
        sdf = new SimpleDateFormat(DATE_FORMAT);
        item1.setItemName("test1");
        date.setTime(sdf.parse("2019-10-03"));
        item1.setDueDate(date);
        Item item2 = new Item();
        item2.setItemName("test2");
        date.setTime(sdf.parse("2019-10-04"));
        item2.setDueDate(date);
        Item item3 = new Item();
        item3.setItemName("test3");
        date.setTime(sdf.parse("2019-10-05"));
        item3.setDueDate(date);
        todo.add(item1);
        todo.add(item2);
        todo.add(item3);

        saveList.saveList(LOADANDSAVEFILE);

        boolean result = testEqual(todo);
        assertTrue(result);
    }


    private boolean testEqual(ArrayList<Item> todo) throws FileNotFoundException, ParseException {
        Scanner scanner = new Scanner(new File("saveTest.txt"));
        Date date;
        Item item1 = todo.get(0);
        Item item2 = todo.get(1);
        Item item3 = todo.get(2);
        String str1 = item1.getItemName();
        if(!(str1.equals(scanner.nextLine()))){
            return false;
        }

        else if(!(item1.getDueDate()).equals(sdf.parse(scanner.nextLine()))){
            return false;
        }

        String str2 = item2.getItemName();
        if(!(str2.equals(scanner.nextLine()))){
            return false;
        }
        else if(!(item2.getDueDate()).equals(sdf.parse(scanner.nextLine()))){
            return false;
        }

        String str3 = item3.getItemName();
        if(!(str3.equals(scanner.nextLine()))){
            return false;
        }
        else if(!(item3.getDueDate()).equals(sdf.parse(scanner.nextLine()))){
            return false;
        }
        return true;
    }
}
