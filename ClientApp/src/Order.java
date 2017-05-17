import java.util.HashMap;
import java.util.Map;

/**
 * Created by SPAS on 10/05/2017.
 */
public class Order implements Cloneable{

    private Map<Dish, Integer> orderedDishes;//dish, amount
    private double total;
    private String status;

    public Order(){
        total = 0;
        orderedDishes = new HashMap<>();
        status = null;
    }

    //adding if true, removing if false
    public void addToOrder(Dish theDish, boolean isAdding) {

        if(isDishInOrder(theDish)){
            for(Dish i : orderedDishes.keySet()){
                if(theDish.getName().equals(i.getName())){
                    int pastAmount = orderedDishes.get(i);
                    if(isAdding && i.getStock() > 0){
//                        System.out.println("past amount: " + pastAmount);
                        orderedDishes.put(i,pastAmount+1);
                        theDish.removeStock();
//                        System.out.println("new amount: " + orderedDishes.get(i));
//                        total = total + theDish.getPrice();
                    } else if(theDish.getStock() <= 0 && isAdding){
                        System.out.println("not enough stock of " + theDish.getName() + " for this order");
                    } else if(pastAmount == 1 && !isAdding){
                        theDish.addStock();
//                        total = total - theDish.getPrice();
                        orderedDishes.remove(theDish);
                    } else {
                        theDish.addStock();
                        orderedDishes.put(i,pastAmount-1);
//                        total = total - theDish.getPrice();
                    }
                    //void does nothing if we decrease a removed dish
                    if(pastAmount == 0)orderedDishes.remove(theDish);
                    break;
                }
            }
        } else {
            if(isAdding && theDish.getStock() > 0){
                theDish.removeStock();
                orderedDishes.put(theDish, 1);
//                total = theDish.getPrice();
            } else {
                System.out.println("you can not remove from nothing!!!!");
            }
        }
    }

    public boolean isDishInOrder(Dish dish){
        for(Dish i : orderedDishes.keySet()){
            if(dish.getName().equals(i.getName())){
                return true;
            }
        }
        return false;
    }

    //returns the quintity of this dish in the order
    public int getDishAmount(Dish dish){
        if(isDishInOrder(dish)) {
            return orderedDishes.get(dish);
        } else {
            return 0;
        }
    }

    public Map getOrderedDishes(){
        return orderedDishes;
    }


    //java has problems with adding fractions
    public double getTotal(){
        for (Dish i : orderedDishes.keySet()) {
            total = total + ((i.getPrice() * 100) * orderedDishes.get(i));
        }
        double temp = total / 100;
        total = 0;
        return temp;
    }

    public void setStatus(String newStatus){status = newStatus;}

    public String getStatus(){
        return status;
    }

    public void removeAll(){
        orderedDishes.clear();
        total = 0;
        status = null;
    }

    public void setOrderedDishes(Map<Dish, Integer> orderedDishes) {
        this.orderedDishes = orderedDishes;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
