import java.util.HashMap;
import java.util.Map;

/**
 * Created by SPAS on 04/05/2017.
 */
public class StockManager {
    private static Map<Ingredient, Inventory> ingredients;
    private static Map<SushiDish, Inventory> readyDishes;

    public StockManager(){
        ingredients = new HashMap<>();
        readyDishes = new HashMap<>();
    }

    //checks if the restaurant uses this kind of ingredient
    private boolean igredientExists(Ingredient someIngr){
        for(Ingredient i : ingredients.keySet()){
            if(i.getName().equals(someIngr.getName()))return true;
        }
        return false;
    }

    //adds a new type of ingredient
    public void addIngredient(Ingredient food, int restocklvl){
        if(this.igredientExists(food)){
            System.err.println("we are already using: " + food.getName());
        } else {
            Inventory temp = new Inventory(restocklvl);
            ingredients.put(food, temp);
        }
    }

    //checks if restaurant already offers the dish
    private boolean dishExists(SushiDish someDish){
        for(SushiDish i : readyDishes.keySet()){
            if(i.getName().equals(someDish.getName()))return true;
        }
        return false;
    }

    //adds a new type of SushiDish
    public void addDish(SushiDish dish, int restocklvl){
        if(this.dishExists(dish)){
            System.err.println("we are already using: " + dish.getName());
        } else {
            Inventory temp = new Inventory(restocklvl);
            readyDishes.put(dish, temp);
        }
    }

    //may delete later
    public void supply(Ingredient food, int amount){
        for (Ingredient i : ingredients.keySet()){
            if(i.getName().equals(food.getName())){
                //increase the ingr in inventory by amount
                ingredients.get(i).incrementBy(amount);
            }
        }
    }

    public Map getIngredients(){return ingredients;}

    public Map getReadyDishes(){return readyDishes;}

}
