package presentation;

import business.CompositeProduct;
import business.DeliveryService;
import business.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ButtonListenerCompositeProduct implements ActionListener {
    private List<MenuItem> products;
    private DeliveryService deliveryService;

    public ButtonListenerCompositeProduct(List products, DeliveryService deliveryService) {
        this.products = products;
        this.deliveryService = deliveryService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("Compose product");
        frame.setSize(600,400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel label1 = new JLabel("Number of products: ");
        JLabel label2 = new JLabel("Title of the products: ");

        JTextField text1 = new JTextField(20);
        JTextField text2 = new JTextField(50);

        JButton button1 = new JButton("Add composite product to menu");
        ButtonListenerAddCompositeProduct listener = new ButtonListenerAddCompositeProduct(this.products, text1, text2, deliveryService);
        button1.addActionListener(listener);


        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel2.setLayout(new FlowLayout());
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

        panel1.add(label1);
        panel1.add(text1);

        panel2.add(label2);
        panel2.add(text2);

        panel3.add(panel1);
        panel3.add(panel2);
        panel3.add(button1);

        frame.setContentPane(panel3);
        frame.setVisible(true);
    }
}
