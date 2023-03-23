package business;


public class BaseProduct extends MenuItem {

    public BaseProduct(String title, float rating, int calories, int protein, int fat, int sodium, float price) {
        super(title, rating, calories, protein, fat, sodium, price);
    }

    public float computePrice(){
        return getPrice();
    }
}
