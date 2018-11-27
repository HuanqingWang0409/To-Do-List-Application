package main.ui;

import main.model.ToDoListControlUnit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class ToDoListGUI extends JFrame implements ActionListener
{
    public static final Font MESSAGEFONT = new Font("Times New Roman", Font.PLAIN, 22);
    public static final Font BUTTONFONT = new Font("Times New Roman", Font.BOLD, 28);
    public static final Color BUTTONCOLOR = Color.BLACK;
    public static final Color BUTTONBACKGROUND = Color.LIGHT_GRAY;
    public static final String INPUT_TEXT = "Input text:";
    private JScrollPane scroll;
    private TextArea displayedMessage = new TextArea();
    private Label inputLabel;
    private TextField inputField;
    private JButton okButton = new JButton("OK");
    private JButton btn1 = new JButton("Add a task");
    private JButton btn2 = new JButton("Cross off a task");
    private JButton btn3 = new JButton("Print all lists");
    private JButton btn4 = new JButton("Check task status");

    private ToDoListControlUnit theList;
    private int currentButton;
    private int numCrossed;
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();


    public ToDoListGUI()
    {
        super("Todo List Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(130, 130, 130, 130));
        setPreferredSize(new Dimension(1200, 800));

        displayedMessage.setEditable(false);
        scroll = new JScrollPane(displayedMessage, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(900,200));
        inputLabel = new Label(INPUT_TEXT);
        inputField = new TextField(15);
        displayedMessage.setFont(MESSAGEFONT);
        inputLabel.setFont(MESSAGEFONT);
        inputField.setFont(MESSAGEFONT);
        currentButton = 0;
        numCrossed = 0;

        okButton.setActionCommand("OK");
        btn1.setActionCommand("Add a task");
        btn2.setActionCommand("Cross off a task");
        btn3.setActionCommand("Print all lists");
        btn4.setActionCommand("Check task status");

        okButton.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        btn1.setFont(BUTTONFONT);
        btn2.setFont(BUTTONFONT);
        btn3.setFont(BUTTONFONT);
        btn4.setFont(BUTTONFONT);
        okButton.setForeground(BUTTONCOLOR);
        btn1.setForeground(BUTTONCOLOR);
        btn2.setForeground(BUTTONCOLOR);
        btn3.setForeground(BUTTONCOLOR);
        btn4.setForeground(BUTTONCOLOR);
        okButton.setBackground(BUTTONBACKGROUND);
        btn1.setBackground(BUTTONBACKGROUND);
        btn2.setBackground(BUTTONBACKGROUND);
        btn3.setBackground(BUTTONBACKGROUND);
        btn4.setBackground(BUTTONBACKGROUND);


        this.addWindowListener(new WindowAdapter() {
                                   @Override
                                   public void windowClosing(WindowEvent e) {
                                       super.windowClosing(e);
                                           try {

                                               theList.saveList();
                                           } catch (IOException exception) {
                                               theList.printCannotFindFilePromptToGUI(false, displayedMessage);
                                           }
                                   }
                               });


        okButton.addActionListener(this);
        btn1.addActionListener(this); //sets "this" class as an action listener for btn1.
        //that means that when the btn1 is clicked,
        //this.actionPerformed(ActionEvent e) will be called.
        //You could also set a different class, if you wanted
        //to capture the response behaviour elsewhere
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);


        scroll.setBounds(15,15,1200,400);
        panel1.setBounds(15,500,1170,100);
        panel2.setBounds(15,600,1170,200);


        panel1.add(inputLabel);
        panel1.add(inputField);
        panel1.add(okButton);
        panel2.add(btn1);
        panel2.add(btn2);
        panel2.add(btn3);
        panel2.add(btn4);
        add(scroll);
        add(panel1);
        add(panel2);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        new chooseListTypePrompt();

    }

    //this is the method that runs when Swing registers an action on an element
    //for which this class is an ActionListener
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("OK")) {
            if(currentButton==2){
                numCrossed = theList.execute_crossOffItem(numCrossed,displayedMessage,inputField);
                inputField.setText("");
                theList.appendChoicePromptToGUI(displayedMessage);
            }
            if(currentButton==4){
                theList.execute_checkTaskStatus(displayedMessage,inputField);
                inputField.setText("");
                theList.appendChoicePromptToGUI(displayedMessage);
            }
        }

        if(e.getActionCommand().equals("Add a task")){
            theList.new addItemPrompt();
        }

        if(e.getActionCommand().equals("Cross off a task")){
            currentButton = 2;
            theList.execute_printAList(theList.getToDoList(),displayedMessage);
            displayedMessage.append("Please type the number of task you want to cross off in the box below. \n" +
                    "Press OK when you are done. \n");

        }

        if(e.getActionCommand().equals("Print all lists")) {
            theList.execute_printAList(theList.getToDoList(),displayedMessage);
            theList.execute_printAList(theList.getDoneList(),displayedMessage);
            theList.execute_printAList(theList.getOverdueList(),displayedMessage);
            theList.appendChoicePromptToGUI(displayedMessage);
        }

        if(e.getActionCommand().equals("Check task status")) {
            currentButton = 4;
            theList.printInputTaskNamePromptToGUI(displayedMessage);
        }
    }



    public class chooseListTypePrompt extends JFrame implements ActionListener {
        private Label label1 = new Label("Before you get started, please choose the order of tasks in your list: \n");
        private Label label2 = new Label("alphabetical order or chronological order.");
        private JButton btn5 = new JButton("Alphabetical list");
        private JButton btn6 = new JButton("Chronological list");

        public chooseListTypePrompt(){
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setPreferredSize(new Dimension(720, 420));
            setLayout(null);
            ((JPanel) getContentPane()).setBorder(new EmptyBorder(130, 130, 130, 130));
            label1.setBounds(10,20,800,30);
            label2.setBounds(10,52,800,30);
            btn5.setBounds(160,120,350,80);
            btn6.setBounds(160,240,350,80);
            label1.setFont(MESSAGEFONT);
            label2.setFont(MESSAGEFONT);
            btn5.setFont(BUTTONFONT);
            btn6.setFont(BUTTONFONT);
            btn5.setBackground(BUTTONBACKGROUND);
            btn6.setBackground(BUTTONBACKGROUND);
            btn5.setForeground(BUTTONCOLOR);
            btn6.setForeground(BUTTONCOLOR);
            btn5.setActionCommand("Alphabetical list");
            btn6.setActionCommand("Chronological list");

            add(label1);
            add(label2);
            add(btn5);
            add(btn6);
            btn5.addActionListener(this);
            btn6.addActionListener(this);


            pack();
            setLocationRelativeTo(null);
            setVisible(true);
            setResizable(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Alphabetical list")) {
                theList = new ToDoListControlUnit(1, displayedMessage);
                this.dispose();
                printWebPage();
            }

            if(e.getActionCommand().equals("Chronological list")) {
                theList = new ToDoListControlUnit(2, displayedMessage);
                this.dispose();
                printWebPage();

            }
        }
    }

    public void printWebPage(){
        try{
            ReadWebPageEx.printURL(displayedMessage);
        }catch(IOException e){
            displayedMessage.append("Error: Cannot find the web-page!");
        }finally {
            theList.appendChoicePromptToGUI(displayedMessage);
        }
    }


    public static void main(String[] args) {
            new ToDoListGUI();
    }
}