import java.util.Map;

/**
 * Created by SPAS on 04/05/2017.
 */
public class SushiDish {
    private String name;
    private String description;
    private int price;
    private boolean occupied;
//    private ArrayList<Ingredient> recipe;
    private Map<Ingredient, Integer> recipe;

    public SushiDish(String name, String desc, int price, Map recipe){
        this.name = name;
        this.description = desc;
        this.price = price;
        this.recipe = recipe;
        occupied = false;
    }

    public SushiDish(Map recipe){
        this.recipe = recipe;
        occupied = false;
    }

    public Map getRecipe(){
        return recipe;
    }

    public void setOccupied(Boolean occupied){
        this.occupied = occupied;
    }

    public boolean isOccupied(){
        return occupied;
    }

}
