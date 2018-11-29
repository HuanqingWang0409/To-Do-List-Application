package main.model;

import main.ObserverPattern.Observer;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static main.ui.ToDoListGUI.MESSAGEFONT;

public abstract class Subject {
    protected List<Observer> observers;

    public Subject(){
        observers = new ArrayList<>();
    }

    public void addObserver(Observer o){
        observers.add(o);
    }

    public void removeObserver(Observer o){
    observers.remove(o);
    }

    public void notifyObservers(){
        JTextArea displayedMessage = new JTextArea("The current task is added successfully!\n");
        displayedMessage.setEditable(false);
        displayedMessage.setFont(MESSAGEFONT);
        displayedMessage.setForeground(Color.gray);

        for(Observer o: observers){
            o.update(displayedMessage);
        }

        JOptionPane.showMessageDialog(null, displayedMessage,
                "Task monitor feedback", JOptionPane.CLOSED_OPTION);
    }
}
