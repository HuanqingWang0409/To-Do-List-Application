package main.model;

import main.Exceptions.PassedDueDateException;
import main.ObserverPattern.TaskMonitor;
import main.ui.ReadWebPageEx;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static main.ui.ToDoListGUI.MESSAGEFONT;
import static main.ui.ToDoListGUI.BUTTONFONT;
import static main.ui.ToDoListGUI.BUTTONCOLOR;
import static main.ui.ToDoListGUI.BUTTONBACKGROUND;
import static main.ui.ToDoListGUI.INPUT_TEXT;

public class ToDoListControlUnit {
    private static final String LOADANDSAVEFILE = "File.txt";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    private ToDoList toDoList;
    private ToDoList doneList;
    private ToDoList overdueList;
    private Map<Item, ToDoList> listMap;
    private Item modifyingItem;

    public ToDoListControlUnit(int listType, TextArea displayedMessage) {
        listMap = new HashMap<>();
        try{
            initializeUnit(listType);
        }catch(IOException e){
            printCannotFindFilePromptToGUI(true, displayedMessage);
        }
    }

    public void initializeUnit(int listType) throws IOException {
        if (listType == 1) {
            toDoList = new AlphabeticalList("to-do list");
            doneList = new AlphabeticalList("done list");
            overdueList = new AlphabeticalList("overdue list");
        } else {
            toDoList = new ChronologicalList("to-do list");
            doneList = new ChronologicalList("done list");
            overdueList = new ChronologicalList("overdue list");
        }

        overdueList.setListOfItems(toDoList.loadList(LOADANDSAVEFILE,sdf));
        for (Item i : toDoList.getListOfItems()) {
            listMap.put(i, toDoList);
        }
        for (Item i : overdueList.getListOfItems()) {
            listMap.put(i, overdueList);
        }
    }

//    public void execute(){
//        Scanner scanner = new Scanner(System.in);
//        int numCrossed = 0;
//        String operation;
//
//        while (true) {
//            System.out.println("what would you like to do: [1] add a todo list item, " +
//                    "[2] cross off an item in todo list [3] show all the items " +
//                    "[4] check the status of a task [5] quit");
//            operation = scanner.nextLine();
//
//            if(operation.equals("1")){
//                execute_addItem(scanner, sdf);
//            }
//            else if (operation.equals("2")){
//                numCrossed = execute_crossOffItem(scanner, numCrossed);
//            }
//            else if (operation.equals("3")){
//                execute_printAllLists();
//            }
//            else if(operation.equals("4")){
//                execute_checkTaskStatus(scanner);
//            }
//            else if (operation.equals("5")){
//                toDoList.printListToGUI(toDoList.getListName(),sdf);
//                System.out.println("Number of tasks done this time:" +numCrossed);
//                try{
//                    toDoList.saveList(LOADANDSAVEFILE,sdf);
//                }catch(IOException e){
//                    printCannotFindFilePromptToGUI(false);
//                }
//                break;
//            }
//            else{
//                System.out.println("Error: wrong user input. Please enter a whole number with [1,5]");
//            }
//        }
//    }


//    public int execute_addItem(JTextArea displayedMessage, JTextField inputField, int procedure){
//        if(procedure==1) {
//            modifyingItem = new Item();
//            printInputTaskNamePromptToGUI(displayedMessage);
//            String itemName = inputField.getText();
//            if (!toDoList.checkContain(itemName)) {
//                modifyingItem.setItemName(itemName);
////            while(true) {
////                displayedMessage.append("Enter the due date of this item in YYYY-MM-DD\n");
////                try {
////                    date.setTime(sdf.parse(inputField.getText()));
////                    modifyingItem.setDueDate(date);
////                    break;
////                } catch (ParseException p){
////                    displayedMessage.append("Wrong date format...\n");
////                }catch(PassedDueDateException e){
////                    displayedMessage.append("Error: The due date is already passed.\n");
////                }
////            }
//                return procedure;
//            } else {
//                displayedMessage.append("Error: This item is already in the list.\n");
//                return 0;
//            }
//        }
//
//        if(procedure==2){
//            displayedMessage.append("Enter the due date of this item in YYYY-MM-DD\n" +
//                    "Press the same button again when you are done.\n");
//        }
//        if(procedure==3){
//            Calendar date = Calendar.getInstance();
//            try {
//                    date.setTime(sdf.parse(inputField.getText()));
//                    modifyingItem.setDueDate(date);
//                } catch (ParseException p){
//                    displayedMessage.append("Wrong date format...\n");
//                }catch(PassedDueDateException e){
//                    displayedMessage.append("Error: The due date is already passed.\n");
//                }
//                toDoList.addItem(modifyingItem);
//            listMap.put(modifyingItem, toDoList);
//            TaskMonitor tm = new TaskMonitor(modifyingItem);
//            toDoList.addObserver(tm);
//            toDoList.notifyObservers();
//        }
//        return procedure;
//    }


    public class addItemPrompt extends JFrame implements ActionListener{
        private TextArea displayedMessage;
        private TextField inputField;
        private Label inputLabel;
        private JButton okButton;
        private int procedure;
        private Item modifyingItem;
        private Calendar date;

        public addItemPrompt(){
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setPreferredSize(new Dimension(800, 450));
            displayedMessage = new TextArea();
            displayedMessage.setFont(MESSAGEFONT);
            displayedMessage.setEditable(false);

            inputField = new TextField(10);
            inputField.setFont(MESSAGEFONT);
            inputLabel = new Label(INPUT_TEXT);
            inputLabel.setFont(MESSAGEFONT);
            okButton = new JButton("OK");
            okButton.setFont(BUTTONFONT);
            okButton.setForeground(BUTTONCOLOR);
            okButton.setBackground(BUTTONBACKGROUND);

            setLayout(null);
            ((JPanel) getContentPane()).setBorder(new EmptyBorder(130, 130, 130, 130));
            displayedMessage.setBounds(0,0,800,220);
            inputLabel.setBounds(85,260,100,30);
            inputField.setBounds(225,260,350,33);
            okButton.setBounds(350,340,100,50);
            add(displayedMessage);
            add(inputLabel);
            add(inputField);
            add(okButton);
            okButton.setActionCommand("OK");

            procedure = 1;
            modifyingItem = new Item();
            date = Calendar.getInstance();

            okButton.addActionListener(this);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            setResizable(false);

            displayedMessage.append("Please enter the task name in the box below.\n");
         }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("OK") && procedure==1) {
                String itemName = inputField.getText();
                inputField.setText("");
                if(itemName.equals("")) {
                    displayedMessage.append("Error: Task name cannot be empty. Please change a name.\n");
                }
                else if((!toDoList.checkContain(itemName)) && (!doneList.checkContain(itemName)) && (!overdueList.checkContain(itemName))) {
                    modifyingItem.setItemName(itemName);
                    procedure++;
                    displayedMessage.append("Please enter the due date of this task in YYYY-MM-DD in the box below.\n" +
                            "Ex. 2000-01-01\n");
                }
                else{
                    displayedMessage.append("Error: This task is already in the list.\n");
                }
            }
            else if(e.getActionCommand().equals("OK") && procedure==2) {
                    try {
                        date.setTime(sdf.parse(inputField.getText()));
                        modifyingItem.setDueDate(date);
                        toDoList.addItem(modifyingItem);
                        listMap.put(modifyingItem, toDoList);
                        toDoList.addObserver(new TaskMonitor(modifyingItem));
                        toDoList.notifyObservers();
                        dispose();
                        procedure++;
                    } catch (ParseException p) {
                        displayedMessage.append("\nWrong date format... \n" +
                                "Please enter the due date of this task again in YYYY-MM-DD in the box below.\n" +
                                "Ex. 2000-01-01\n");
                    } catch (PassedDueDateException exception) {
                        displayedMessage.append("Error: The due date is already passed.\n");
                    }
            }
        }
    }


    public int execute_crossOffItem(int numCrossed, TextArea displayedMessage, TextField inputField){
        Item modifyingItem;
//            if(procedure == 1) {
//                toDoList.printListToGUI(toDoList.getListName(), sdf, displayedMessage);
//                displayedMessage.append("Please type the number of element you want to cross off in the box below. \n" +
//                        "Press the same button again when you are done. \n");
//            }
//            if(procedure == 2) {
                    int itemNumber = Integer.parseInt(inputField.getText());
                    try {
                        modifyingItem = toDoList.getListOfItems().get(itemNumber - 1);
                        toDoList.getListOfItems().remove(itemNumber - 1);
                        listMap.remove(modifyingItem);
                        doneList.addItem(modifyingItem);
                        listMap.put(modifyingItem, doneList);
                        toDoList.removeObserver(new TaskMonitor(modifyingItem));
                        numCrossed++;
                    } catch (IndexOutOfBoundsException e) {
                        displayedMessage.append("Error: Out of index.\n");
                    } finally {
                        displayedMessage.append("Cross off is done. Task " +itemNumber+ " has been crossed off.\n" +
                        "You have been crossed off " +numCrossed+ " tasks. Congratulation!\n");
                    }

        return numCrossed;
    }


    public void execute_printAList(ToDoList printingList, TextArea displayedMessage) {
        printingList.printListToGUI(printingList.getListName(),sdf, displayedMessage);
        displayedMessage.append("\n");

    }


    public void execute_checkTaskStatus(TextArea displayedMessage,TextField inputField){
        String itemName = inputField.getText();
        Item checkingItem = new Item();
        checkingItem.setItemName(itemName);
        if (listMap.containsKey(checkingItem)) {
            displayedMessage.append("The task  " +itemName+ "  is in the " + listMap.get(checkingItem).getListName()+".\n");
        } else {
            displayedMessage.append("Sorry, the task is not present in any list.\n");
        }
    }


    public ToDoList getToDoList() {
        return toDoList;
    }

    public ToDoList getDoneList() {
        return doneList;
    }

    public ToDoList getOverdueList() {
        return overdueList;
    }

    public void saveList() throws FileNotFoundException, UnsupportedEncodingException {
        toDoList.saveList(LOADANDSAVEFILE,sdf);
    }

    public void appendChoicePromptToGUI(TextArea displayedMessage){
        displayedMessage.append("\nWhat would you like to do next?\n");
    }

    public void printInputTaskNamePromptToGUI(TextArea displayedMessage){
        displayedMessage.append("Please enter the task name. Press OK when you are done.\n\n");
    }

    //Effects: If purpose==true, file for loading is not found;
    //        if purpose==false, file for saving is not found.
    public void printCannotFindFilePromptToGUI(boolean purpose, TextArea displayedMessage){
        if(purpose)
            displayedMessage.append("Cannot find the file for loading and saving. Loading step skipped.\n");
        else
            displayedMessage.append("Cannot find the file for loading and saving. Saving step skipped.\n");
    }
}
