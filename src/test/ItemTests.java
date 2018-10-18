package test;

import main.Exceptions.PassedDueDateException;
import main.model.Item;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;

public class ItemTests {
    private Item i;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-=MM-DD");
    Date date;
    @Test
    public void TestGetItemName(){
        i = new Item();
        i.setItemName("name1");
        assertEquals("name1",i.getItemName());
    }

    @Test
    public void TestGetDueDate() throws ParseException, PassedDueDateException {
        i = new Item();
        i.setDueDate(sdf.parse("2018-09-30"));
        assertEquals(sdf.parse("2018-09-30"),i.getDueDate());
    }

    @Test
    public void TestGetStatus(){
        i = new Item();
        i.setStatus(false);
        assertEquals(false,i.getStatus());
        i.setStatus(true);
        assertEquals(true,i.getStatus());
    }
}