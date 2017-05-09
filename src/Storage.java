import java.util.HashMap;
import java.util.Map;

/**
 * Created by SPAS on 04/05/2017.
 */
public class Storage {
    private static Map<Ingredient, Inventory> ingredients;
    private static Map<SushiDish, Inventory> readyDishes;

    public Storage(){
        ingredients = new HashMap<>();
        readyDishes = new HashMap<>();
    }

    public void addIngredient(Ingredient food){

    }

    public void igredientExists(Ingredient someIngr){
        
    }

    /*//new ingredient for new recipes
    private void newIngredient(Ingredient component, int restockLevel) {
        inventory.add(new Track<Ingredient>(component, restockLevel));
    }

    private void newDish(SushiDish recipe, int restockLevel){
        readyDishes.add(new Track<SushiDish>(recipe, restockLevel));
    }*/

}
