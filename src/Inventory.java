/**
 * Created by SPAS on 08/05/2017.
 */
public class Inventory {

    private int quantity;
    private int restocklvl;

    private Inventory(int restocklvl) {
        this.restocklvl = restocklvl;
        quantity = 0;
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
}
