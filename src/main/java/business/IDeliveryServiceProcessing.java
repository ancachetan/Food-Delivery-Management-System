package business;

import java.util.List;

public interface IDeliveryServiceProcessing {
    public int addProduct(String title, float rating, int calories, int protein, int fat, int sodium, float price);
    public int deleteProduct(String title);
    public int modifyProduct(String title, float rating, int calories, int protein, int fat, int sodium, float price);
    public List<MenuItem> importProducts();
    public void addCompositeProduct(int nrProducts, String titles);
    public List<MenuItem> searchProduct(String title, String ratingString, String caloriesString, String proteinString, String fatString, String sodiumString, String priceString);
}
