package test;

import main.Exceptions.OutOfIndexException;
import main.Exceptions.PassedDueDateException;
import main.model.AlphabeticalList;
import main.model.ChronologicalList;
import main.model.ToDoListControlUnit;
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
    private ToDoListControlUnit alphaList;
    private ToDoListControlUnit chroList;

    @Before
    public void runBefore(){
        alphaList = new ToDoListControlUnit(1);
        chroList = new ToDoListControlUnit(2);
        ArrayList<Item> alphatodo =  alphaList.getToDoList().getListOfItems();
        ArrayList<Item> chronotodo =  chroList.getToDoList().getListOfItems();
    }

    @Test
    public void OutOfIndexExpectException(){
        try{
            alphaList.getModifyingItem(4);
            fail("Error: exception expected.");
        }catch (OutOfIndexException e){
        }
        try{
            chroList.getModifyingItem(4);
            fail("Error: exception expected.");
        }catch (OutOfIndexException e){
        }
    }

    @Test
    public void OutOfIndexExpectNoException(){
        Item item = new Item();
        item.setItemName(" ");
        try{
            item.setDueDate(new Date());
        }catch (PassedDueDateException e){
            fail("Passed due date.");
        }
        alphaList.getToDoList().insert(item);
        chroList.getToDoList().insert(item);

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
