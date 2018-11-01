//package test;
//
//import main.Exceptions.PassedDueDateException;
//import main.model.AlphabeticalList;
//import main.model.ToDoList;
//import main.model.Item;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//
//import static junit.framework.TestCase.assertTrue;
//import static junit.framework.TestCase.assertFalse;
//
//
//public class ToDoListTests {
//
//    private ToDoList t = new AlphabeticalList();
//    ArrayList<Item> todo;
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
//
//    @BeforeEach
//    public void runBefore() throws ParseException, PassedDueDateException {
//        todo = t.getListOfItems();
//        Item i1 = new Item();
//        i1.setItemName("name1");
//        i1.setDueDate(sdf.parse("2018-09-27"));
//        i1.setStatus(true);
//        Item i2 = new Item();
//        i2.setItemName("name2");
//        i2.setDueDate(sdf.parse("2018-09-28"));
//        i2.setStatus(false);
//        todo.add(i1);
//        todo.add(i2);
//    }
//
//    @Test
//    public void testSameItem() {
//        assertTrue(t.sameItem("name1", todo));
//    }
//
//    @Test
//    public void testNotSameItem(){
//        assertFalse(t.sameItem("name3",todo));
//    }
//
//}
