package presentation;

import business.DeliveryService;
import business.MenuItem;
import data.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminFrame extends JFrame {
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JTextField text1;
    private JTextField text2;
    private JTextField text3;
    private JTextField text4;
    private JTextField text5;
    private JTextField text6;
    private JTextField text7;
    private JTextArea textArea;
    private List<MenuItem> products;
    private DeliveryService deliveryService;
    private List<MenuItem> productsAux;
    private List<Client> clients;

    public AdminFrame(List<MenuItem> products, DeliveryService deliveryService, List<MenuItem> productsAux, List<Client> clients){
        this.setSize(950,700);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Admin page");

        this.deliveryService = deliveryService;
        this.products = products;
        this.productsAux = productsAux;
        this.clients = clients;

        this.button1 = new JButton("Import products");
        this.button2 = new JButton("Add products");
        this.button3 = new JButton("Delete product");
        this.button4 = new JButton("Modify product");
        this.button5 = new JButton("Composite product");
        this.button6 = new JButton("Show menu");
        this.button7 = new JButton("Clear");
        this.button8 = new JButton("Reports");
        this.text1 = new JTextField(20);
        this.text2 = new JTextField(20);
        this.text3 = new JTextField(20);
        this.text4 = new JTextField(20);
        this.text5 = new JTextField(20);
        this.text6 = new JTextField(20);
        this.text7 = new JTextField(20);
        this.label1 = new JLabel("Title:    ");
        this.label2 = new JLabel("Rating:   ");
        this.label3 = new JLabel("Calories: ");
        this.label4 = new JLabel("Protein:  ");
        this.label5 = new JLabel("Fat:      ");
        this.label6 = new JLabel("Sodium:   ");
        this.label7 = new JLabel("Price:    ");

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();
        JPanel panel7 = new JPanel();
        JPanel panel8 = new JPanel();
        JPanel panel9 = new JPanel();
        JPanel panel10 = new JPanel();
        JPanel panel11 = new JPanel();

        panel1.setLayout(new FlowLayout());
        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);
        panel1.add(button4);
        panel1.add(button5);
        panel1.add(button6);
        panel1.add(button7);
        panel1.add(button8);

        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel3.setLayout(new FlowLayout());
        panel4.setLayout(new FlowLayout());
        panel5.setLayout(new FlowLayout());
        panel6.setLayout(new FlowLayout());
        panel7.setLayout(new FlowLayout());
        panel8.setLayout(new FlowLayout());
        panel9.setLayout(new FlowLayout());
        panel3.add(label1);
        panel3.add(text1);
        panel4.add(label2);
        panel4.add(text2);
        panel5.add(label3);
        panel5.add(text3);
        panel6.add(label4);
        panel6.add(text4);
        panel7.add(label5);
        panel7.add(text5);
        panel8.add(label6);
        panel8.add(text6);
        panel9.add(label7);
        panel9.add(text7);

        panel2.add(panel3);
        panel2.add(panel4);
        panel2.add(panel5);
        panel2.add(panel6);
        panel2.add(panel7);
        panel2.add(panel8);
        panel2.add(panel9);

        panel11.setLayout(new FlowLayout());
        this.textArea = new JTextArea(20,80);
        JScrollPane scroll = new JScrollPane(this.textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel11.add(scroll);

        panel10.setLayout(new BoxLayout(panel10, BoxLayout.Y_AXIS));
        panel10.add(panel1);
        panel10.add(panel2);
        panel10.add(panel11);

        this.setContentPane(panel10);
        setActionListeners();
    }

    public void setActionListeners(){

        ButtonListenerImportProducts listener1 = new ButtonListenerImportProducts(products, productsAux);
        this.button1.addActionListener(listener1);

        ButtonListenerAddProduct listener2 = new ButtonListenerAddProduct(products, textArea, text1, text2, text3, text4, text5, text6, text7, deliveryService);
        this.button2.addActionListener(listener2);

        ButtonListenerDeleteProduct listener3 = new ButtonListenerDeleteProduct(products, textArea, text1, deliveryService);
        this.button3.addActionListener(listener3);

        ButtonListenerModifyProduct listener4 = new ButtonListenerModifyProduct(products, textArea, text1, text2, text3, text4, text5, text6, text7, deliveryService);
        this.button4.addActionListener(listener4);

        ButtonListenerCompositeProduct listener5 = new ButtonListenerCompositeProduct(products, deliveryService);
        this.button5.addActionListener(listener5);

        ButtonListenerShowMenu listener6 = new ButtonListenerShowMenu(products, textArea);
        this.button6.addActionListener(listener6);

        this.button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                text1.setText("");
                text2.setText("");
                text3.setText("");
                text4.setText("");
                text5.setText("");
                text6.setText("");
                text7.setText("");
            }
        });

        ButtonListenerReports listener8 = new ButtonListenerReports(deliveryService, clients);
        this.button8.addActionListener(listener8);
    }
}
