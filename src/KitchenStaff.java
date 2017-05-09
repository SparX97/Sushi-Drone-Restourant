import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by SPAS on 08/05/2017.
 */
public class KitchenStaff implements Runnable {


    private Random rand;
//    private volatile boolean isCooking;
    private Map<Ingredient, Inventory> ingredientsRef;
    private Map<SushiDish, Inventory> readyDishRef;

    public KitchenStaff() {
        rand = new Random();
    }

    public KitchenStaff(HashMap<Ingredient, Inventory> ingredients, HashMap<SushiDish, Inventory> readyDishes) {
        ingredientsRef = ingredients;
        readyDishRef = readyDishes;
        rand = new Random();
//        isCooking = false;
    }

    //look at readyDishes restocklvl
    @Override
    public void run() {
        SushiDish understocked = null;
        while (true) {
            synchronized (readyDishRef) {

                for (SushiDish i : readyDishRef.keySet()) {
                    Inventory temp = (Inventory) readyDishRef.get(i);
                    if (temp.getQuantity() < temp.getRestocklvl() && !i.isOccupied()) {
                        i.setOccupied(true);
//                        isCooking = true;
                        understocked = i;
                        break;
                    }
                }
            }
            if(understocked != null) cook(understocked);
        }
    }

    //cook ingredients if bellow restocklvl
    private void cook(SushiDish dish) {
        Map<Ingredient, Integer> recipe = dish.getRecipe();
        for (Ingredient i : recipe.keySet()) {
            int useAmount = recipe.get(i);
            synchronized (ingredientsRef) {
                Inventory stock = ingredientsRef.get(i);
                stock.decrementBy(useAmount);
            }
        }
        try {
            Thread.sleep(2000 + 100 * rand.nextInt(40));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (readyDishRef) {
            Inventory dishStock = readyDishRef.get(dish);
            dishStock.increment();
        }
    }

/*
    public void run() {
        while (true) {
            synchronized (readyDishes) {
                for (Track<SushiDish> i : readyDishes) {
                    if (i.getQuantity() < i.getRestocklvl())
                    {
                        cook(i.getItem());//read about synch
                        i.increment();
                    }
                }
            }
        }
    }

    private void cook(SushiDish dish) {
        synchronized (inventory) {// not sure
            ArrayList<Ingredient> recipe = dish.getRecipe();
            for(Ingredient i : recipe){
                for(Track j : inventory){
                    if(i == j.getItem())j.decrement();
                }
            }
        }
        try {
            Thread.sleep(2000 + 10 * rand.nextInt(40));
        } catch (InterruptedException e) {
            System.err.println(e);
        }
    }*/
}
