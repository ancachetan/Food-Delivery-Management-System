package presentation;

import business.DeliveryService;
import business.MenuItem;
import data.Client;
import data.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClientFrame extends JFrame {
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JLabel label11;
    private JTextField text1;
    private JTextField text2;
    private JTextField text3;
    private JTextField text4;
    private JTextField text5;
    private JTextField text6;
    private JTextField text7;
    private JTextField text8;
    private JTextField text9;
    private JTextField text10;
    private JTextField text11;
    private JTextArea textArea;
    private List<MenuItem> products;
    private DeliveryService deliveryService;
    private Client client;
    private List<Client> clients;
    private Employee employee;

    public ClientFrame(List<MenuItem> products, DeliveryService deliveryService, Client client, List<Client> clients, Employee employee){
        this.setSize(900,700);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Client page");

        this.products = products;
        this.deliveryService = deliveryService;
        this.client = client;
        this.clients = clients;
        this.employee = employee;

        this.button1 = new JButton("Show menu");
        this.button2 = new JButton("Search");
        this.button3 = new JButton("Place order");
        this.button4 = new JButton("Clear");

        this.text1 = new JTextField(20);
        this.text2 = new JTextField(20);
        this.text3 = new JTextField(20);
        this.text4 = new JTextField(20);
        this.text5 = new JTextField(20);
        this.text6 = new JTextField(20);
        this.text7 = new JTextField(20);
        this.text8 = new JTextField(40);
        this.text9 = new JTextField(10);
        this.text10 = new JTextField(10);
        this.text11 = new JTextField(10);
        this.label1 = new JLabel("Find by title:    ");
        this.label2 = new JLabel("Find by rating:   ");
        this.label3 = new JLabel("Find by calories: ");
        this.label4 = new JLabel("Find by protein:  ");
        this.label5 = new JLabel("Find by fat:      ");
        this.label6 = new JLabel("Find by sodium:   ");
        this.label7 = new JLabel("Find by price:    ");
        this.label8 = new JLabel("Titles for order products: ");
        this.label9 = new JLabel("Number of products to order: ");
        this.label10 = new JLabel("Date: ");
        this.label11 = new JLabel("Hour: ");

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
        JPanel panel12 = new JPanel();
        JPanel panel13 = new JPanel();

        panel1.setLayout(new FlowLayout());
        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);
        panel1.add(button4);

        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel3.setLayout(new FlowLayout());
        panel4.setLayout(new FlowLayout());
        panel5.setLayout(new FlowLayout());
        panel6.setLayout(new FlowLayout());
        panel7.setLayout(new FlowLayout());
        panel8.setLayout(new FlowLayout());
        panel9.setLayout(new FlowLayout());
        panel12.setLayout(new FlowLayout());
        panel13.setLayout(new FlowLayout());
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
        panel12.add(label9);
        panel12.add(text9);
        panel12.add(label8);
        panel12.add(text8);
        panel13.add(label10);
        panel13.add(text10);
        panel13.add(label11);
        panel13.add(text11);


        panel2.add(panel3);
        panel2.add(panel4);
        panel2.add(panel5);
        panel2.add(panel6);
        panel2.add(panel7);
        panel2.add(panel8);
        panel2.add(panel9);
        panel2.add(panel12);
        panel2.add(panel13);

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
        ButtonListenerShowMenu listener1 = new ButtonListenerShowMenu(products, textArea);
        this.button1.addActionListener(listener1);

        ButtonListenerSearchProduct listener2 = new ButtonListenerSearchProduct(products, textArea, text1, text2, text3, text4, text5, text6, text7, deliveryService);
        this.button2.addActionListener(listener2);

        ButtonListenerPlaceOrder listener3 = new ButtonListenerPlaceOrder(products, text9, text8, text10, text11, textArea, client, deliveryService, clients, employee);
        this.button3.addActionListener(listener3);

        listener3.addObserver(employee);

        this.button4.addActionListener(new ActionListener() {
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
                text8.setText("");
                text9.setText("");
                text10.setText("");
                text11.setText("");
            }
        });
    }

}
