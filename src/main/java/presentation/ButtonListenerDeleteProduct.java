package presentation;

import business.DeliveryService;
import business.MenuItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ButtonListenerDeleteProduct implements ActionListener {
    private List<MenuItem> products;
    private JTextArea textArea;
    private JTextField text1;
    private DeliveryService deliveryService;

    public ButtonListenerDeleteProduct(List<MenuItem> products, JTextArea textArea, JTextField text1, DeliveryService deliveryService) {
        this.products = products;
        this.textArea = textArea;
        this.text1 = text1;
        this.deliveryService = deliveryService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int poz = deliveryService.deleteProduct(text1.getText());

        textArea.setText("");
        if (poz != -1){
            products.remove(poz);
            textArea.setText("Product with title " + text1.getText() + " was removed!\n");
        }else {
            textArea.setText("No product with title " + text1.getText() + " to remove\n");
        }
    }
}
