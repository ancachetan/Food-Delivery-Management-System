package data;

public class Client extends User{
    private static int ID = 0;
    private int nrOrders;
    public Client(String username, String password) {
        super(username, password);
        ID++;
        this.setId(ID);
        this.setRole("CLIENT");
        this.nrOrders = 0;
    }

    public int getNrOrders() {
        return nrOrders;
    }

    public void setNrOrders(int nrOrders) {
        this.nrOrders = nrOrders;
    }
}
