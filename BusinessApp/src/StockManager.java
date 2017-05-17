import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SPAS on 04/05/2017.
 */
public class StockManager {
    private static Map<Ingredient, Inventory> ingredients;
    private static Map<SushiDish, Inventory> readyDishes;
    private static List<Supplier> suppliers;

    public StockManager(){
        ingredients = new HashMap<>();
        readyDishes = new HashMap<>();
        suppliers = new ArrayList<>();
    }

    //checks if the restaurant uses this kind of ingredient
    private boolean ingredientExists(Ingredient someIngr){
        for(Ingredient i : ingredients.keySet()){
            if(i.getName().equals(someIngr.getName()))return true;
        }
        return false;
    }

    //adds a new type of ingredient
    public void addIngredient(Ingredient food, int restocklvl){
        if(this.ingredientExists(food)){
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
    public void addDish(SushiDish dish,int startSupply, int restocklvl){
        if(this.dishExists(dish)){
            System.err.println("we are already using: " + dish.getName());
        } else {
            Inventory temp = new Inventory(restocklvl);
            temp.incrementBy(startSupply);
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

    //could cause problems with dishes that require it
    //delete dishes that use this ingredient first
    public void removeIngrdient(Ingredient someIngredient){
        ingredients.remove(someIngredient);
    }

    public void addSupplier(Supplier seller){
        suppliers.add(seller);
    }

    public Map getIngredients(){return ingredients;}

    public Map getReadyDishes(){
        return readyDishes;
    }

    public List getSuppliers(){
        return suppliers;
    }

}
