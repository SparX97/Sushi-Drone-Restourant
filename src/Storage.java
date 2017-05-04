import java.util.ArrayList;

/**
 * Created by SPAS on 04/05/2017.
 */
public class Storage {

    private ArrayList<Tracker> inventory;
    private ArrayList<Tracker> readyDishes;
    public Storage(){
        inventory = new ArrayList<>();
        readyDishes = new ArrayList<>();
    }

    private class Tracker<T> {

        private T item;
        private int quantity;
        private int restockOn;

        private Tracker(T item, int restockOn){
            this.item = item;
            this.restockOn = restockOn;
            quantity = 0;
        }
    }

    //new ingredient for new recipes
    private void newIngredient(Ingredient component, int restockLevel) {
        inventory.add(new Tracker<Ingredient>(component, restockLevel));
    }

    private void newDish(SushiDish recipe, int restockLevel){
        readyDishes.add(new Tracker<SushiDish>(recipe, restockLevel));
    }
}
