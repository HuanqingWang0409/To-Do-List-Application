package test;

import main.model.Item;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;

public class ItemTest {
    private Item i;

    @Test
    public void TestGetItemName(){
        i = new Item();
        i.setItemName("name1");
        assertEquals("name1",i.getItemName());
    }

    @Test
    public void TestGetDueDate(){
        i = new Item();
        i.setDueDate(180930);
        assertEquals(180930,i.getDueDate());
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
