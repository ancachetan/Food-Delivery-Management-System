package business;

import java.util.Observable;

public abstract class MenuItem{
    private String title;
    private float rating;
    private int calories;
    private int protein;
    private int fat;
    private int sodium;
    private float price;
    private int nrOfOrders;

    public MenuItem(String title, float rating, int calories, int protein, int fat, int sodium, float price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
        this.nrOfOrders = 0;
    }

    public MenuItem(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNrOfOrders() {
        return nrOfOrders;
    }

    public void setNrOfOrders(int nrOfOrders) {
        this.nrOfOrders = nrOfOrders;
    }

    public int incrementNrOfOrders(){
        this.nrOfOrders++;
        return this.nrOfOrders;
    }

    public abstract float computePrice();

    @Override
    public String toString() {
        return "MenuItem{" +
                "title = " + title +
                ", rating = " + rating +
                ", calories = " + calories +
                ", protein = " + protein +
                ", fat = " + fat +
                ", sodium = " + sodium +
                ", price = " + price +
                ", nrOfOrders = " + nrOfOrders +
                '}';
    }
}
