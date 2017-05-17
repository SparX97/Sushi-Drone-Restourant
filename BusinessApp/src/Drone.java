import java.util.List;
import java.util.Map;

/**
 * Created by SPAS on 16/05/2017.
 */
public class Drone implements Runnable{
    private String name;
    private int metersPerSec;
//    private Random rand;
    //    private volatile boolean isCooking;
    private Map<Ingredient, Inventory> ingredientsRef;
    private Map<SushiDish, Inventory> readyDishRef;
    private List<Supplier> suppliers;
    private List<Postcode> postcodes;
    private String status;

    /*public KitchenStaff() {
        rand = new Random();
    }*/

    public Drone(String name, int metersPerSec, StockManager theStockManager, List<Supplier> suppliers, List<Postcode> postcodes) {
        this.name = name;
        this.metersPerSec = metersPerSec;
        ingredientsRef = theStockManager.getIngredients();
        readyDishRef = theStockManager.getReadyDishes();
        this.suppliers = suppliers;
        this.postcodes = postcodes;
//        rand = new Random();
        status = "standby";
//        isCooking = false;
    }

    //look at ingredients restocklvl then if deliveries are needed
    @Override
    public void run() {
        Ingredient understocked = null;
        while (true) {
            synchronized (ingredientsRef) {

                for (Ingredient i : ingredientsRef.keySet()) {
                    Inventory temp = (Inventory) ingredientsRef.get(i);
                    if (temp.getQuantity() < temp.getRestocklvl() && !i.isOccupied()) {
                        i.setOccupied(true);
//                        isCooking = true;
                        understocked = i;
                        break;
                    }
                }
            }
            if (understocked != null) {
                fetch(understocked);
                understocked = null;
            }
            //TODO check for deliveries
//            synchronized ()
        }
    }

    //supply ingredients if bellow restocklvl
    private void fetch(Ingredient resource) {
//        boolean delivered = false;
//        Map<Ingredient, Integer> recipe = dish.getRecipe();
        int distance = 0;
        synchronized (suppliers) {
            outerLoop:
            for (Supplier i : suppliers) {
                for(Ingredient j : i.getProducts()){
                    if(resource.getName().equals(j.getName())){
                        System.out.println("Drone: " + name + " travelling to supplier for 2*(supplier Distance()/ drone metersPerSec) = " + 2*(i.getDistance()/metersPerSec));
                        status = "supplying";
                        distance = 2*(i.getDistance()/metersPerSec);
                        break outerLoop;
                    }
                }
                /*int distance = recipe.get(i);

                Inventory stock = ingredientsRef.get(i);
                stock.decrementBy(useAmount);*/
//                System.out.println(i.getName() + " decremented by: " + useAmount + " - left: " + stock.getQuantity());
            }
//            System.out.println("all by " + name);
        }
        if(status.equals("supplying")) {
            try {
                Thread.sleep(distance);
            } catch (InterruptedException e) {
                System.err.println("Drone thread sleep exception");
            }
            synchronized (ingredientsRef){
                for (Ingredient i : ingredientsRef.keySet()){
                    if(i.getName().equals(resource.getName())){
                        ingredientsRef.get(i).incrementBy(10);
                        System.out.println(i.getName() + " stock supplied with 10");
                        status = "standby";
                        break;
                    }
                }
            }
        }
    }
}
