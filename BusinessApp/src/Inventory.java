/**
 * Created by SPAS on 08/05/2017.
 */
public class Inventory {

    private int quantity;
    private int restocklvl;

    public Inventory(int restocklvl) {
        this.restocklvl = restocklvl;
        this.quantity = 0;
    }

    public Inventory(){
        this.restocklvl = 0;
        this.quantity = 0;
    }

    public int getQuantity(){
        return quantity;
    }

    public int getRestocklvl(){
        return restocklvl;
    }

    public void decrementBy(int amount){
        quantity -= amount;
    }

    public void incrementBy(int amount){quantity += amount;}

    public void increment(){
        incrementBy(1);
    }

    public void setRestock(int newRestock){
        restocklvl = newRestock;
    }

    public void setStock(int stock){
        this.quantity = stock;
    }
}
