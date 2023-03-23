package presentation;

import business.DeliveryService;
import data.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class ButtonListenerReports implements ActionListener {
    private DeliveryService deliveryService;
    private List<Client> clients;

    public ButtonListenerReports(DeliveryService deliveryService, List<Client> clients) {
        this.deliveryService = deliveryService;
        this.clients = clients;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("Compose product");
        frame.setSize(600,400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel label1 = new JLabel("Start hour:                      ");
        JLabel label2 = new JLabel("End hour:                        ");
        JLabel label3 = new JLabel("Mim number of times for product: ");
        JLabel label4 = new JLabel("Min number of orders:            ");
        JLabel label5 = new JLabel("Min price:                       ");
        JLabel label6 = new JLabel("Specified date:                  ");


        JTextField text1 = new JTextField(10);
        JTextField text2 = new JTextField(10);
        JTextField text3 = new JTextField(10);
        JTextField text4 = new JTextField(10);
        JTextField text5 = new JTextField(10);
        JTextField text6 = new JTextField(10);

        JButton button1 = new JButton("Generate reports");
        ButtonListenerGenerateReports listener1 = new ButtonListenerGenerateReports(deliveryService, text1, text2, text3, text4, text5, text6, clients);
        button1.addActionListener(listener1);


        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();
        JPanel panel7 = new JPanel();

        panel1.setLayout(new FlowLayout());
        panel2.setLayout(new FlowLayout());
        panel3.setLayout(new FlowLayout());
        panel4.setLayout(new FlowLayout());
        panel5.setLayout(new FlowLayout());
        panel6.setLayout(new FlowLayout());
        panel7.setLayout(new BoxLayout(panel7, BoxLayout.Y_AXIS));

        panel1.add(label1);
        panel1.add(text1);

        panel2.add(label2);
        panel2.add(text2);

        panel3.add(label3);
        panel3.add(text3);

        panel4.add(label4);
        panel4.add(text4);

        panel5.add(label5);
        panel5.add(text5);

        panel6.add(label6);
        panel6.add(text6);

        panel7.add(button1);
        panel7.add(panel1);
        panel7.add(panel2);
        panel7.add(panel3);
        panel7.add(panel4);
        panel7.add(panel5);
        panel7.add(panel6);

        frame.setContentPane(panel7);
        frame.setVisible(true);
    }
}
