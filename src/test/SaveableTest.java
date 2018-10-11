package test;

import main.model.AlphabeticalList;
import main.model.Item;
import main.model.ToDoList;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SaveableTest {
    private static final String LOADANDSAVEFILE = "saveTest.txt";
    private ToDoList saveList = new AlphabeticalList();
    private ArrayList<Item> todo = saveList.getTodo();

    @Test
    public void testSaveData() throws FileNotFoundException, UnsupportedEncodingException {
        Item item1 = new Item();
        item1.setItemName("test1");
        item1.setStatus(false);
        item1.setDueDate(20181003);
        Item item2 = new Item();
        item2.setItemName("test2");
        item2.setStatus(false);
        item2.setDueDate(20181004);
        Item item3 = new Item();
        item3.setItemName("test3");
        item3.setStatus(true);
        item3.setDueDate(20181005);
        todo.add(item1);
        todo.add(item2);
        todo.add(item3);

        saveList.saveList(LOADANDSAVEFILE);

        boolean result = testEqual(todo);
        assertTrue(result);
    }


    private boolean testEqual(ArrayList<Item> todo) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("saveTest.txt"));

        Item item1 = todo.get(0);
        Item item2 = todo.get(1);
        Item item3 = todo.get(2);
        String str1 = item1.getItemName();
        if(!(str1.equals(scanner.nextLine()))){
            return false;
        }

        else if(item1.getDueDate()!=Integer.parseInt(scanner.nextLine())){
            return false;
        }
        else if(item1.getStatus() != Boolean.parseBoolean(scanner.nextLine())){
            return false;
        }

        String str2 = item2.getItemName();
        if(!(str2.equals(scanner.nextLine()))){
            return false;
        }
        else if(item2.getDueDate()!=Integer.parseInt(scanner.nextLine())){
            return false;
        }
        else if(item2.getStatus() != Boolean.parseBoolean(scanner.nextLine())){
            return false;
        }

        String str3 = item3.getItemName();
        if(!(str3.equals(scanner.nextLine()))){
            return false;
        }
        else if(item3.getDueDate()!=Integer.parseInt(scanner.nextLine())){
            return false;
        }
        else if(item3.getStatus()!=Boolean.parseBoolean(scanner.nextLine())){
            return false;
        }
        return true;
    }
}