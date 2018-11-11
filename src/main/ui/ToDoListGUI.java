package main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoListGUI extends JFrame implements ActionListener
{
    private JTextArea displayedMessage;
    private JTextField inputField;
    private JButton btn1 = new JButton("Add a task");
    private JButton btn2 = new JButton("Cross off a task");
    private JButton btn3 = new JButton("Print all lists");
    private JButton btn4 = new JButton("Check task status");
    private JButton btn5 = new JButton("Alphabetical list");
    private JButton btn6 = new JButton("Chronological list");
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel4 = new JPanel();
    private JPanel panel3 = new JPanel();

    //false when the type of list has not been selected; true otherwise
    private boolean flag;

    public ToDoListGUI()
    {
        super("Todo List Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 500));
        flag = false;
//        ((JPanel) getContentPane()).setBorder(new EmptyBorder(500, 13, 13, 13) );

        btn1.setActionCommand("Add a task");
        btn2.setActionCommand("Cross off a task");
        btn3.setActionCommand("Print all lists");
        btn4.setActionCommand("Check task status");
        btn5.setActionCommand("Alphabetical list");
        btn6.setActionCommand("Chronological list");

        btn1.addActionListener(this); //sets "this" class as an action listener for btn1.
        //that means that when the btn1 is clicked,
        //this.actionPerformed(ActionEvent e) will be called.
        //You could also set a different class, if you wanted
        //to capture the response behaviour elsewhere
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        btn5.addActionListener(this);
        btn6.addActionListener(this);

        displayedMessage = new JTextArea("what would you like to do: \n[1] add a todo list item\n" +
                "[2] cross off an item in todo list \n[3] show all the items \n" +
                "[4] check the status of a task \n[5] quit");
        inputField = new JTextField(10);
        setLayout(new GridLayout(4, 1));

        panel1.add(displayedMessage);
        panel2.add(inputField);
        panel3.add(btn5);
        panel3.add(btn6);
        panel4.add(btn1);
        panel4.add(btn2);
        panel4.add(btn3);
        panel4.add(btn4);

        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);


        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    //this is the method that runs when Swing registers an action on an element
    //for which this class is an ActionListener
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("Add a task") && flag) {
            displayedMessage.setText(inputField.getText());
        }
        if(e.getActionCommand().equals("Cross off a task") && flag){
            displayedMessage.setText(inputField.getText());
        }
        if(e.getActionCommand().equals("Print all lists") && flag)
            displayedMessage.setText(inputField.getText());
        if(e.getActionCommand().equals("Check task status") && flag)
            displayedMessage.setText(inputField.getText());
        if(e.getActionCommand().equals("Alphabetical list") && !flag)
            flag = true;
        if(e.getActionCommand().equals("Chronological list") && !flag)
            flag = true;
    }
    public static void main(String[] args)
    {
        new ToDoListGUI();
    }

}