/**
 * Created by SPAS on 10/05/2017.
 */
public class Dish {

    private String name;
    private double price;
    private int inStock;

    public Dish(String name, double price, int inStock){
        this.name = name;
        this.price = price;
        this.inStock = inStock;
    }

    public int getStock(){return inStock;}

    public String getName(){return name;}

    public double getPrice(){return price;}
}
