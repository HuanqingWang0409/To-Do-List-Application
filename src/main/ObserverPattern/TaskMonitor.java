package main.ObserverPattern;

import main.model.Item;

import javax.swing.*;
import java.util.Objects;

public class TaskMonitor implements Observer {
    private Item item;
    private int numTasksBefore;

    public TaskMonitor(Item item){
        this.item = item;
        numTasksBefore = 0;
    }

    @Override
    public void update(JTextArea displayedMessage) {
        if(numTasksBefore <= 1) {
            displayedMessage.append("The task  " + item.getItemName() + "  indicates that there is  "
                    + numTasksBefore + "  task not being done yet before that task.\n");
        }
        else{
            displayedMessage.append("The task  " +item.getItemName()+ "  indicates that there are  "
                    +numTasksBefore+ "  tasks not being done yet before that task.\n");
        }
    }


    public Item getItem() {
        return item;
    }

    public int getNumTasksBefore() {
        return numTasksBefore;
    }

    public void setNumTasksBefore(int num) {
        this.numTasksBefore = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskMonitor)) return false;
        TaskMonitor that = (TaskMonitor) o;
        return numTasksBefore == that.numTasksBefore &&
                Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {

        return Objects.hash(item, numTasksBefore);
    }
}
