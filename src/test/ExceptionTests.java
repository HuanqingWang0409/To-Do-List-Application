package test;

import main.Exceptions.PassedDueDateException;
import main.model.ToDoListControlUnit;
import main.model.Item;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static junit.framework.TestCase.fail;

public class ExceptionTests {
    private ToDoListControlUnit alphaList;
    private ToDoListControlUnit chroList;

    @Before
    public void runBefore(){
        alphaList = new ToDoListControlUnit(1, new TextArea());
        chroList = new ToDoListControlUnit(2, new TextArea());
        ArrayList<Item> alphatodo =  alphaList.getToDoList().getListOfItems();
        ArrayList<Item> chronotodo =  chroList.getToDoList().getListOfItems();
    }

    @Test
    public void PassedDueDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Item item = new Item();
        Calendar date = Calendar.getInstance();
        try{
            date.setTime(sdf.parse("2018-01-01"));
            item.setDueDate(date);
            fail("Error: Exception expected.");
        }catch(PassedDueDateException e){
        }
    }

    @Test
    public void NotPassedDueDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Item item = new Item();
        Calendar date = Calendar.getInstance();
        try{
            date.setTime(sdf.parse("9999-01-01"));
            item.setDueDate(date);
        }catch(PassedDueDateException e){
            fail("Error: No Exception expected.");
        }
    }
}
