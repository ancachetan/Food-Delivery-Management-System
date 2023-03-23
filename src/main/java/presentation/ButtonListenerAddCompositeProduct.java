package presentation;

import business.CompositeProduct;
import business.DeliveryService;
import business.MenuItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ButtonListenerAddCompositeProduct implements ActionListener {
    private List<MenuItem> products;
    private JTextField text1;
    private JTextField text2;
    private DeliveryService deliveryService;

    public ButtonListenerAddCompositeProduct(List<MenuItem> products, JTextField text1, JTextField text2, DeliveryService deliveryService) {
        this.products = products;
        this.text1 = text1;
        this.text2 = text2;
        this.deliveryService = deliveryService;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        deliveryService.addCompositeProduct(Integer.parseInt(text1.getText()), text2.getText());
    }
}
