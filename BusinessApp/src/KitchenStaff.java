import java.util.Map;
import java.util.Random;

/**
 * Created by SPAS on 08/05/2017.
 */
public class KitchenStaff implements Runnable {


    private String name;
    private Random rand;
    //    private volatile boolean isCooking;
    private Map<Ingredient, Inventory> ingredientsRef;
    private Map<SushiDish, Inventory> readyDishRef;

    /*public KitchenStaff() {
        rand = new Random();
    }*/

    public KitchenStaff(String name, StockManager theStockManager) {
        this.name = name;
        ingredientsRef = theStockManager.getIngredients();
        readyDishRef = theStockManager.getReadyDishes();
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
            if (understocked != null) {
                cook(understocked);
                understocked = null;
            }
        }
    }

    //cook ingredients if bellow restocklvl
    private void cook(SushiDish dish) {
        Map<Ingredient, Integer> recipe = dish.getRecipe();
        synchronized (ingredientsRef) {
            for (Ingredient i : recipe.keySet()) {
                int useAmount = recipe.get(i);

                Inventory stock = ingredientsRef.get(i);
                stock.decrementBy(useAmount);
//                System.out.println(i.getName() + " decremented by: " + useAmount + " - left: " + stock.getQuantity());
            }
//            System.out.println("all by " + name);
        }
        try {
            Thread.sleep(2000 + 100 * rand.nextInt(40));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (readyDishRef) {
            Inventory dishStock = readyDishRef.get(dish);
            dishStock.increment();
            dish.setOccupied(false);
            System.out.println(dish.getName() + " - total created: " + dishStock.getQuantity());
            System.out.println("CREATOR " + name + "\n");
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
