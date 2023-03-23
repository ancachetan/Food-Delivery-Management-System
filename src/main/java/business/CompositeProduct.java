package business;

import java.util.List;

public class CompositeProduct extends MenuItem {
    private List<MenuItem> baseProducts;
    public static int nrProduct = 0;

    public CompositeProduct(List<MenuItem> list) {
        super();
        this.baseProducts = list;
        nrProduct++;
        this.setTitle("Daily menu " + nrProduct);
        computeRating();
        computeCalories();
        computeProtein();
        computeFat();
        computeSodium();
        setPrice(computePrice());
    }

    public void computeRating(){
        float rating = 0;
        for (MenuItem p : baseProducts){
            rating += p.getRating();
        }

        this.setRating(rating);
    }

    public void computeCalories(){
        int calories = 0;
        for (MenuItem p : baseProducts){
            calories += p.getCalories();
        }

        this.setCalories(calories);
    }

    public void computeProtein(){
        int protein = 0;
        for (MenuItem p : baseProducts){
            protein += p.getProtein();
        }

        this.setProtein(protein);
    }

    public void computeFat(){
        int fat = 0;
        for (MenuItem p : baseProducts){
            fat += p.getFat();
        }

        this.setFat(fat);
    }

    public void computeSodium(){
        int sodium = 0;
        for (MenuItem p : baseProducts){
            sodium += p.getSodium();
        }

        this.setSodium(sodium);
    }

    public float computePrice(){
        float price = 0;
        for (MenuItem p : baseProducts){
            price += p.getPrice();
        }

        return price;
    }
}
