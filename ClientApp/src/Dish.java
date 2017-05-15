/**
 * Created by SPAS on 10/05/2017.
 */
public class Dish {

    private String name;

    private String description;
    private double price;
    private int inStock;

    public Dish(String name, String description, double price, int inStock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.inStock = inStock;
    }

    public void removeStock(){
        inStock--;
    }

    public void addStock(){
        inStock++;
    }

    public int getStock() {
        return inStock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
