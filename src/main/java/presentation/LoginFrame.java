package presentation;
import business.DeliveryService;
import business.MenuItem;
import data.Administrator;
import data.Client;
import data.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LoginFrame extends JFrame {
    private JTextField text1;
    private JTextField text2;
    private JLabel label1;
    private JLabel label2;
    private JButton button1;
    private JButton button2;
    //private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private AdminFrame adminFrame;
    private ClientFrame clientFrame;
    private DeliveryService deliveryService;
    private Client client;
    private List<Client> clients;
    private List<Administrator> administrators;
    private List<MenuItem> productsAux;
    private Employee employee;

    public LoginFrame(){
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Login page");

        clients = new ArrayList<>();
        administrators = new ArrayList<>();
        List<MenuItem> products = new ArrayList<>();
        deliveryService = new DeliveryService(products);
        productsAux = deliveryService.importProducts();

        this.label1 = new JLabel("username: ");
        this.label2 = new JLabel("password: ");
        this.text1 = new JTextField(20);
        this.text2 = new JPasswordField(20);
        this.button1 = new JButton("Admin login");
        this.button2 = new JButton("Client login");
        //this.button3 = new JButton("Employee login");
        this.button4 = new JButton("Register as administrator");
        this.button5 = new JButton("Register as client");
        this.button6 = new JButton("Register as employee");

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();

        panel1.setLayout(new FlowLayout());
        panel1.add(label1);
        panel1.add(text1);
        panel1.add(label2);
        panel1.add(text2);

        panel2.setLayout(new FlowLayout());
        panel2.add(button1);
        panel2.add(button2);
        //panel2.add(button3);

        panel4.add(button4);
        panel4.add(button5);
        panel4.add(button6);

        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        panel3.add(panel1);
        panel3.add(panel2);
        panel3.add(panel4);

        this.setContentPane(panel3);
        this.setVisible(true);

        setActionListeners();
    }

    public void setActionListeners(){

        this.button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Administrator administrator = new Administrator(text1.getText(), text2.getText());
                administrators.add(administrator);
                deliveryService.setAdministrators(administrators);
            }
        });

        this.button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client1 = new Client(text1.getText(), text2.getText());
                clients.add(client1);
                deliveryService.setClients(clients);
            }
        });

        this.button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                employee = new Employee (text1.getText(), text2.getText());
            }
        });

        this.button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = text1.getText();
                String password = text2.getText();

                int ok1 = 0;
                for (Administrator a : administrators){
                    if (a.getUsername().equals(username)){
                        ok1 = 1;
                    }
                }

                int ok2 = 0;
                if (ok1 == 0){
                    JOptionPane.showMessageDialog(null, "Wrong username","Error", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    for (Administrator a : administrators){
                        if (a.getUsername().equals(username) && a.getPassword().equals(password)){
                            ok2 = 1;
                        }
                    }

                    if (ok2 == 0){
                        JOptionPane.showMessageDialog(null, "Wrong password","Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

                if (ok1 == 1 && ok2 == 1) {
                    adminFrame = new AdminFrame(deliveryService.getProducts(), deliveryService, productsAux, clients);
                    adminFrame.setVisible(true);
                }
            }
        });

        this.button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = text1.getText();
                String password = text2.getText();

                int ok1 = 0;
                for (Client c : clients){
                    if (c.getUsername().equals(username)){
                        ok1 = 1;
                    }
                }

                int ok2 = 0;
                if (ok1 == 0){
                    JOptionPane.showMessageDialog(null, "Wrong username","Error", JOptionPane.INFORMATION_MESSAGE);
                }else {
                    for (Client c : clients){
                        if (c.getUsername().equals(username) && c.getPassword().equals(password)){
                            ok2 = 1;
                        }
                    }

                    if (ok2 == 0){
                        JOptionPane.showMessageDialog(null, "Wrong password","Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

                if (ok1 == 1 && ok2 == 1) {
                    for (Client c : clients){
                        if (c.getUsername().equals(username) && c.getPassword().equals(password)){
                            setClient(c);
                        }
                    }
                    clientFrame = new ClientFrame(deliveryService.getProducts(), deliveryService, getClient(), clients, employee);
                    clientFrame.setVisible(true);
                }
            }
        });
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
