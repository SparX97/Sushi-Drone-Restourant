import java.util.HashMap;
import java.util.Map;

/**
 * Created by SPAS on 10/05/2017.
 */
public class Order {

    private Map<String, Integer> orderedDishes;
    private double total;
    private boolean isDispatching;

    public Order(){
        total = 0;
        orderedDishes = new HashMap<>();
        isDispatching = false;
    }

    //adding if true, removing if false
    public void addToOrder(String dish, double price, boolean isAdding) {

        boolean isThere = false;
        for(String i : orderedDishes.keySet()) {
            if(dish.equals(i)){
                int pastAmount = orderedDishes.get(i);
                if(isAdding)
                {
                    pastAmount++;
                    total = total + price;
                } else {
                    pastAmount--;
                    total = total - price;
                }
                //TODO test if working
                isThere = true;

                if(pastAmount == 0)orderedDishes.remove(dish);
                break;
            }
        }
        if(!isThere && isAdding) {
            orderedDishes.put(dish, 1);
            total = total + price;
        }
    }

    public double getTotal(){return total;}

    public void toggleDispatching(){isDispatching = !isDispatching;}

    public boolean getDispatching(){
        return isDispatching;
    }

    private void removeAll(){
        orderedDishes.clear();
        total = 0;
        isDispatching = false;
    }
}
