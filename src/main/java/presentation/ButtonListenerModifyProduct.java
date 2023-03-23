package presentation;

import business.DeliveryService;
import business.MenuItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ButtonListenerModifyProduct implements ActionListener {
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

    public ButtonListenerModifyProduct(List<MenuItem> products, JTextArea textArea, JTextField text1, JTextField text2, JTextField text3, JTextField text4, JTextField text5, JTextField text6, JTextField text7, DeliveryService deliveryService) {
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
        String aux = "";

        String title = text1.getText();

        float rating = -1;
        aux = text2.getText();
        if (!aux.equals("")){
            rating = Float.parseFloat(text2.getText());
        }

        int calories = -1;
        aux = text3.getText();
        if (!aux.equals("")){
            calories = Integer.parseInt(text3.getText());
        }

        int protein = -1;
        aux = text4.getText();
        if (!aux.equals("")){
            protein = Integer.parseInt(text4.getText());
        }

        int fat = -1;
        aux = text5.getText();
        if (!aux.equals("")){
            fat = Integer.parseInt(text5.getText());
        }

        int sodium = -1;
        aux = text6.getText();
        if (!aux.equals("")){
            sodium = Integer.parseInt(text6.getText());
        }

        int price = -1;
        aux = text7.getText();
        if (!aux.equals("")){
            price = Integer.parseInt(text7.getText());
        }

        int flag = deliveryService.modifyProduct(title, rating, calories, protein, fat, sodium, price);

        textArea.setText("");
        if (flag == 1){
            textArea.append("We have modified product with title " + title + "\n");
        }else {
            textArea.setText("No product with title " + title + " to modify");
        }
    }
}
