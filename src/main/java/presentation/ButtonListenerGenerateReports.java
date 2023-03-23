package presentation;

import business.DeliveryService;
import business.MenuItem;
import data.Client;
import data.Order;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ButtonListenerGenerateReports implements ActionListener {
    private DeliveryService deliveryService;
    private JTextField text1;
    private JTextField text2;
    private JTextField text3;
    private JTextField text4;
    private JTextField text5;
    private JTextField text6;
    private List<Client> clients;

    public ButtonListenerGenerateReports(DeliveryService deliveryService, JTextField text1, JTextField text2, JTextField text3, JTextField text4, JTextField text5, JTextField text6, List<Client> clients) {
        this.deliveryService = deliveryService;
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        this.text4 = text4;
        this.text5 = text5;
        this.text6 = text6;
        this.clients = clients;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String hourStartString = text1.getText();
        int hourStart = Integer.parseInt(hourStartString);

        String hourEndString = text2.getText();
        int hourEnd = Integer.parseInt(hourEndString);

        Map<Order, List<MenuItem>> mapOrder = deliveryService.getOrders().entrySet().stream().filter(map -> map.getKey().getDate().getHours() >= hourStart && map.getKey().getDate().getHours() <= hourEnd).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        try {
            Writer writer1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("report1.txt")));
            writer1.write("Report 2: \n");
            mapOrder.entrySet().forEach(entry -> {
                try {
                    writer1.append(entry.getKey().toString() + "" + entry.getValue().toString() + "\n");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            writer1.close();

        }catch (IOException exp) {
            System.out.println(exp.getMessage());
        }

        int nrOfTimes = Integer.parseInt(text3.getText());

        List<MenuItem> list = deliveryService.getProducts().stream().filter(c -> c.getNrOfOrders() > nrOfTimes).collect(Collectors.toList());

        Writer writer2 = null;

        try {
            writer2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("report2.txt")));
            writer2.write("Report 2: \n");
            for (MenuItem m : list){
                writer2.append(m.toString() + "\n");
            }

        }catch (IOException exp){
            System.out.println(exp.getMessage());
        }finally {
            try {
                writer2.close();
            }catch (Exception exp){
                System.out.println(exp.getMessage());
            }
        }

        int nrOfTimesClient = Integer.parseInt(text4.getText());
        float maxPrice = Float.parseFloat(text5.getText());

        Map<Order, List<MenuItem>> mapOrder1 = deliveryService.getOrders().entrySet().stream().filter(map -> map.getKey().getClient().getNrOrders() > nrOfTimesClient && map.getKey().getTotalPrice() > maxPrice).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        try {
            Writer writer1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("report3.txt")));
            writer1.write("Report 3: \n");
            mapOrder1.entrySet().forEach(entry -> {
                try {
                    writer1.append(entry.getKey().toString() + "" + entry.getValue().toString() + "\n");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            writer1.close();

        }catch (IOException exp) {
            System.out.println(exp.getMessage());
        }

        String dateStr = text6.getText();

        String[] dateString = dateStr.split(". ");
        int day = Integer.parseInt(dateString[0]);
        int month = Integer.parseInt(dateString[1]);
        int year = Integer.parseInt(dateString[2]);

        Date date = new Date(year - 1900, month - 1, day);

        Map<Order, List<MenuItem>> mapOrder2 = deliveryService.getOrders().entrySet().stream().filter(map -> map.getKey().getDate().getYear() == date.getYear() && map.getKey().getDate().getMonth() == date.getMonth() && map.getKey().getDate().getDay() == date.getDay()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        try {
            Writer writer1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("report4.txt")));
            writer1.write("Report 4: \n");
            mapOrder2.entrySet().forEach(entry -> {
                try {
                    writer1.append(entry.getKey().toString() + " " + entry.getValue().toString() + "\n");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            writer1.close();

        }catch (IOException exp) {
            System.out.println(exp.getMessage());
        }

    }
}
