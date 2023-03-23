package presentation;

import business.BaseProduct;
import business.DeliveryService;
import business.MenuItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ButtonListenerAddProduct implements ActionListener {
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

    public ButtonListenerAddProduct(List<MenuItem> products, JTextArea textArea, JTextField text1, JTextField text2, JTextField text3, JTextField text4, JTextField text5, JTextField text6, JTextField text7, DeliveryService deliveryService) {
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
        int flag = deliveryService.addProduct(text1.getText(), Float.parseFloat(text2.getText()), Integer.parseInt(text3.getText()), Integer.parseInt(text4.getText()), Integer.parseInt(text5.getText()), Integer.parseInt(text6.getText()), Float.parseFloat(text2.getText()));

        textArea.setText("");
        if (flag == 1){
            textArea.append("One base product was added to the menu!: " + text1.getText() + "\n");
        }else {
            textArea.append("The product with title " + text1.getText() +  " already exists!\n");
        }
    }
}
