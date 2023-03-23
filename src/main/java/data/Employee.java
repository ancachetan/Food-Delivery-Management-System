package data;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("depricable")
public class Employee extends User implements Observer {
    private static int ID;
    private JTextArea textArea;

    public Employee(String username, String password) {
        super(username, password);
        ID++;
        this.setId(ID);
        this.setRole("EMPLOYEE");
    }

    @SuppressWarnings("depricable")
    @Override
    public void update(Observable o, Object arg) {
        textArea.setText("");
        textArea.append("\nEmployee notified: preparing order: \n");
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }
}
