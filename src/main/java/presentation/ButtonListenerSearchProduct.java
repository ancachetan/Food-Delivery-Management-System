package presentation;

import business.DeliveryService;
import business.MenuItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ButtonListenerSearchProduct implements ActionListener {
    private List<MenuItem> products;
    private JTextArea textArea;
    private JTextField text1;
    private JTextField text2;
    private JTextField text3;
    private JTextField text4;
    private JTextField text5;
    private JTextField text6;
    private JTextField text7;
    private DeliveryService deliveryService;

    public ButtonListenerSearchProduct(List<MenuItem> products, JTextArea textArea, JTextField text1, JTextField text2, JTextField text3, JTextField text4, JTextField text5, JTextField text6, JTextField text7, DeliveryService deliveryService) {
        this.products = products;
        this.textArea = textArea;
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        this.text4 = text4;
        this.text5 = text5;
        this.text6 = text6;
        this.text7 = text7;
        this.deliveryService = deliveryService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<MenuItem> menuItems1 = deliveryService.searchProduct(text1.getText(), text2.getText(), text3.getText(), text4.getText(), text5.getText(), text6.getText(), text7.getText());

        textArea.setText("");
        if (menuItems1.size() != products.size()){
            for (MenuItem p : menuItems1){
                textArea.append(p.toString() + "\n");
            }
        }
    }
}
