package business;

import data.Administrator;
import data.Client;
import data.Order;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class DeliveryService implements IDeliveryServiceProcessing{
    private List<MenuItem> products;
    private List<Administrator> administrators;
    private List<Client> clients;
    private Map<Order, List<MenuItem>> orders;

    public DeliveryService(List<MenuItem> products) {
        orders = new HashMap<>();
        this.products = products;
        this.clients = new ArrayList<>();
        this.administrators = new ArrayList<>();
    }

    /**
     * @pre title != "" && rating > 0 && calories > 0 && protein > 0 && fat > 0 && sodium > 0 && price > 0
     * @post getProductsSize() = getProductsSize() + 1
     *
     */
    public int addProduct(String title, float rating, int calories, int protein, int fat, int sodium, float price){
        assert title != "" && rating > 0 && calories > 0 && protein > 0 && fat > 0 && sodium > 0 && price > 0;

        BaseProduct baseProduct = new BaseProduct(title, rating, calories, protein, fat, sodium, price);
        int sizePre = getProductsSize();
        int flag = 1;
        for (MenuItem p : products){
            if (p.getTitle().equals(title)){
                flag = 0;
                break;
            }
        }

        if (flag == 1) {
            products.add(baseProduct);
        }

        int sizePost = getProductsSize();
        assert sizePost == sizePre + 1;

        return flag;
    }

    /**
     * @pre title != ""
     * @post getProductsSize() = getProductsSize() - 1
     *
     */
    public int deleteProduct(String title){
        assert title != "";

        int poz = -1;
        int sizePre = getProductsSize();

        for (int i = 0; i < products.size(); i++){
            if (products.get(i).getTitle().equals(title)){
                poz = i;
                break;
            }
        }

        int sizePost = getProductsSize();
        assert sizePost == sizePre - 1;

        return poz;
    }


    /**
     * @pre (title != "") && (rating > 0 || calories > 0 || protein > 0 || fat > 0 || sodium > 0 || price > 0)
     *
     */
    public int modifyProduct(String title, float rating, int calories, int protein, int fat, int sodium, float price){
        assert (title != "") && (rating > 0 || calories > 0 || protein > 0 || fat > 0 || sodium > 0 || price > 0);

        int flag = 0;

        for (MenuItem p : products){
            if (p.getTitle().equals(title)){
                flag = 1;
                if (rating != -1){
                    assert rating > 0;
                    p.setRating(rating);
                }

                if (calories != -1){
                    assert calories > 0;
                    p.setCalories(calories);
                }

                if (protein != -1){
                    assert protein > 0;
                    p.setProtein(protein);
                }

                if (fat != -1){
                    assert fat > 0;
                    p.setFat(fat);
                }

                if (sodium != -1){
                    assert sodium > 0;
                    p.setSodium(sodium);
                }

                if (price != -1){
                    assert price > 0;
                    p.setPrice(price);
                }
            }
        }

        return flag;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * @post getProductsSize() > 0
     *
     */
    public List<MenuItem> importProducts(){
        List<MenuItem> products1 = new ArrayList<>();
        Pattern pattern = Pattern.compile(",");
        try (Stream<String> lines = Files.lines(Path.of("products.csv"))){
            products1 = lines.skip(1).map(line -> {
                String[] specifications = pattern.split(line);
                return new BaseProduct(specifications[0],
                        Float.parseFloat(specifications[1]),
                        Integer.parseInt(specifications[2]),
                        Integer.parseInt(specifications[3]),
                        Integer.parseInt(specifications[4]),
                        Integer.parseInt(specifications[5]),
                        Integer.parseInt(specifications[6]));
            }).collect(toList());
        }catch (IOException exp){
            System.out.println(exp.getMessage());
        }

        products1 = products1.stream().filter( distinctByKey(p-> p.getTitle())).collect(toList());
        assert getProductsSize() > 0;
        return products1;
    }

    /**
     * @pre titles != "" && nrProducts > 0
     * @post getProductsSize() = getProductsSize() + 1
     */
    public void addCompositeProduct(int nrProducts, String titles){
        assert titles != "";

        String baseProductsTitles[] = titles.split(", ");
        List<MenuItem> baseProductsArray = new ArrayList<>();

        int sizePre = getProductsSize();

        for (int i = 0; i < nrProducts; i++){
            for (MenuItem p : products){
                if (p.getTitle().equals(baseProductsTitles[i])){
                    baseProductsArray.add(p);
                }
            }
        }

        CompositeProduct compositeProduct = new CompositeProduct(baseProductsArray);
        products.add(compositeProduct);

        int sizePost = getProductsSize();
        assert sizePost == sizePre + 1;
    }


    /**
     * @pre title != "" || ratingString != "" || caloriesString != "" || proteinString != "" || fatString != "" || sodiumString != "" || priceString != ""
     *
     */

    public List<MenuItem> searchProduct(String title, String ratingString, String caloriesString, String proteinString, String fatString, String sodiumString, String priceString){
        List<MenuItem> menuItems1 = new ArrayList<>(products);

        assert title != "" || ratingString != "" || caloriesString != "" || proteinString != "" || fatString != "" || sodiumString != "" || priceString != "";

        int ok1 = 1;
        if (title == ""){
            ok1 = 0;
        }

        if (ok1 == 1) {
            menuItems1 = menuItems1.stream().filter(p -> p.getTitle().contains(title)).collect(toList());
        }

        int ok2 = 0;
        try {
            float rating = Float.parseFloat(ratingString);
            menuItems1 = menuItems1.stream().filter(p -> p.getRating() == rating).collect(toList());
            ok2 = 1;
        }catch (NumberFormatException exp) {
            System.out.println(exp.getMessage());
        }

        int ok3 = 0;
        try {
            int calories = Integer.parseInt(caloriesString);
            menuItems1 = menuItems1.stream().filter(p -> p.getCalories() == calories).collect(toList());
            ok3 = 1;
        }catch (NumberFormatException exp){
            System.out.println(exp.getMessage());
        }

        int ok4 = 0;
        try {
            int protein = Integer.parseInt(proteinString);
            menuItems1 = menuItems1.stream().filter(p -> p.getProtein() == protein).collect(toList());
            ok4 = 1;
        }catch (NumberFormatException exp){
            System.out.println(exp.getMessage());
        }

        int ok5 = 0;
        try {
            int fat = Integer.parseInt(fatString);
            menuItems1 = menuItems1.stream().filter(p -> p.getFat() == fat).collect(toList());
            ok5 = 1;
        }catch (NumberFormatException exp){
            System.out.println(exp.getMessage());
        }

        int ok6 = 0;
        try {
            int sodium = Integer.parseInt(sodiumString);
            menuItems1 = menuItems1.stream().filter(p -> p.getSodium() == sodium).collect(toList());
            ok6 = 1;
        }catch (NumberFormatException exp){
            System.out.println(exp.getMessage());
        }

        int ok7 = 0;
        try {
            int price = Integer.parseInt(priceString);
            menuItems1 = menuItems1.stream().filter(p -> p.getPrice() == price).collect(toList());
            ok7 = 1;
        }catch (NumberFormatException exp){
            System.out.println(exp.getMessage());
        }

        return menuItems1;
    }

    public List<MenuItem> getProducts() {
        return products;
    }

    public void setProducts(List<MenuItem> products) {
        this.products = products;
    }

    public Map<Order, List<MenuItem>> getOrders() {
        return orders;
    }

    public void setOrders(Map<Order, List<MenuItem>> orders) {
        this.orders = orders;
    }

    public List<Administrator> getAdministrators() {
        return administrators;
    }

    public void setAdministrators(List<Administrator> administrators) {
        this.administrators = administrators;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public int getProductsSize(){
        return products.size();
    }
}
