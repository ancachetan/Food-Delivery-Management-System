package data;

import java.util.Date;

public class Order {
    private static int ID = 0;
    private int orderId;
    private Client client;
    private Date date;
    private float totalPrice;

    public Order(Client client, Date date) {
        ID++;
        this.orderId = ID;
        this.client = client;
        this.date = date;
        this.totalPrice = 0;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId = " + orderId +
                ", clientId = " + client.getId() +
                ", date = " + date +
                ", totalPrice = " + totalPrice +
                '}';
    }

    @Override
    public int hashCode() {
        return this.orderId + this.client.getId();
    }
}
