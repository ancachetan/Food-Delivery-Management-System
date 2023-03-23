package presentation;

import business.MenuItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ButtonListenerShowMenu implements ActionListener {
    private List<MenuItem> products;
    private JTextArea textArea;

    public ButtonListenerShowMenu(List<MenuItem> products, JTextArea textArea) {
        this.products = products;
        this.textArea = textArea;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        textArea.setText("");
        for (MenuItem p : products){
            textArea.append(p.toString() + "\n");
        }
    }
}
