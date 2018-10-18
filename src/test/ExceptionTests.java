package test;

import main.Exceptions.OutOfIndexException;
import main.Exceptions.PassedDueDateException;
import main.model.AlphabeticalList;
import main.model.ChronologicalList;
import main.model.Item;
import main.model.ToDoList;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static junit.framework.TestCase.fail;

public class ExceptionTests {
    private ToDoList alphaList;
    private ToDoList chroList;

    @Before
    public void runBefore(){
        alphaList = new AlphabeticalList();
        chroList = new ChronologicalList();
        ArrayList<Item> alphatodo =  alphaList.getTodo();
        ArrayList<Item> chronotodo =  chroList.getTodo();
    }

    @Test
    public void OutOfIndexExpectException(){
        try{
            alphaList.getModifyingItem(1);
            fail("Error: exception expected.");
        }catch (OutOfIndexException e){
        }
        try{
            chroList.getModifyingItem(1);
            fail("Error: exception expected.");
        }catch (OutOfIndexException e){
        }
    }

    @Test
    public void OutOfIndexExpectNoException(){
        Item item = new Item();
        alphaList.insert(item);
        chroList.insert(item);

        try{
            alphaList.getModifyingItem(1);
        }catch (OutOfIndexException e){
            fail("Error: No exception expected.");
        }

        try{
            chroList.getModifyingItem(1);
        }catch (OutOfIndexException e){
            fail("Error: No exception expected.");
        }
    }

    @Test
    public void PassedDueDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Item item = new Item();
        try{
            item.setDueDate(sdf.parse("2018-01-01"));
            fail("Error: Exception expected.");
        }catch(PassedDueDateException e){
        }
    }

    @Test
    public void NotPassedDueDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Item item = new Item();
        try{
            item.setDueDate(sdf.parse("9999-01-01"));
        }catch(PassedDueDateException e){
            fail("Error: No Exception expected.");
        }
    }
}
