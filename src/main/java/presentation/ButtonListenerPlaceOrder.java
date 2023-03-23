package presentation;

import business.CompositeProduct;
import business.DeliveryService;
import business.MenuItem;
import data.Client;
import data.Employee;
import data.Order;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Observable;

@SuppressWarnings("depricable")
public class ButtonListenerPlaceOrder extends Observable implements ActionListener {
    private List<MenuItem> products;
    private JTextField text1;
    private JTextField text2;
    private JTextField text3;
    private JTextField text4;
    private JTextArea textArea;
    private List<MenuItem> orderedProducts;
    private Order order;
    private Client client;
    private DeliveryService deliveryService;
    private List<Client> clients;
    private Employee employee;

    public ButtonListenerPlaceOrder(List<MenuItem> products, JTextField text1, JTextField text2, JTextField text3, JTextField text4, JTextArea textArea, Client client, DeliveryService deliveryService, List<Client> clients, Employee employee) {
        this.products = products;
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;
        this.text4 = text4;
        this.textArea = textArea;
        this.client = client;
        this.deliveryService = deliveryService;
        this.clients = clients;
        this.employee = employee;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        orderedProducts = new ArrayList<>();
        int nrProducts = Integer.parseInt(text1.getText());
        String titles = text2.getText();
        String orderProductsTitles[] = titles.split(", ");

        for (int i = 0; i < nrProducts; i++){
            int ok = 0;
            for (MenuItem p : products){
                if (p.getTitle().equals(orderProductsTitles[i])){
                    p.incrementNrOfOrders();
                    orderedProducts.add(p);
                    ok = 1;
                    break;
                }
            }

            if (ok == 0){
                textArea.append("Product with title " + orderProductsTitles[i] + " not in menu!\n");
            }
        }

        String dateStr = text3.getText();

        String[] dateString = dateStr.split(". ");
        int date = Integer.parseInt(dateString[0]);
        int month = Integer.parseInt(dateString[1]);
        int year = Integer.parseInt(dateString[2]);

        String hourStr = text4.getText();

        String[] hourString = hourStr.split(": ");
        int hour = Integer.parseInt(hourString[0]);
        int min = Integer.parseInt(hourString[1]);

        Date date1 = new Date(year - 1900, month - 1, date, hour, min);

        if (orderedProducts.size() > 0) {
            client.setNrOrders(client.getNrOrders() + 1);
            for (Client c : clients){
                if (c.getId() == client.getId()){
                    c.setNrOrders(c.getNrOrders() + 1);
                }
            }
            order = new Order(client, date1);
            deliveryService.getOrders().put(order, orderedProducts);

            CompositeProduct compositeProduct = new CompositeProduct(orderedProducts);
            float totalPrice = compositeProduct.computePrice();
            order.setTotalPrice(totalPrice);

            employee.setTextArea(textArea);
            setChanged();
            notifyObservers(order);
            clearChanged();

            Writer writer = null;

            try {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("bill" + order.getOrderId() + ".txt")));
                writer.write(order.toString() + " consists of:\n" + orderedProducts.toString() + "\nTotal price: " + totalPrice);

            }catch (IOException exp){
                System.out.println(exp.getMessage());
            }finally {
                try {
                    writer.close();
                }catch (Exception exp){
                    System.out.println(exp.getMessage());
                }
            }

            deliveryService.getOrders().entrySet().forEach(entry -> {textArea.append(entry.getKey().toString() + "" + entry.getValue().toString() + "\n");});
        }else {
            textArea.setText("Order not in menu!\n");
        }

    }
}
