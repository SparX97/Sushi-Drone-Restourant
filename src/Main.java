import java.util.HashMap;
import java.util.Map;

/**
 * Created by SPAS on 09/05/2017.
 */
public class Main {

    public static void main(String args[]){
        Ingredient apple = new Ingredient();
        Ingredient tomato = new Ingredient();
        Ingredient pear = new Ingredient();
        Ingredient coco = new Ingredient();
        Ingredient bean = new Ingredient();


        Map<Ingredient, Integer> recipe1 = new HashMap<>();
        recipe1.put(apple,2);
        recipe1.put(tomato,3);
        SushiDish dish1 = new SushiDish(recipe1);

        Map<Ingredient, Integer> recipe2 = new HashMap<>();
        recipe2.put(pear,1);
        recipe2.put(coco,2);
        recipe2.put(bean,2);
        SushiDish dish2 = new SushiDish(recipe2);

        Map<Ingredient, Integer> recipe3 = new HashMap<>();
        recipe2.put(apple,1);
        recipe2.put(coco,2);
        recipe2.put(tomato,4);
        SushiDish dish3 = new SushiDish(recipe3);
    }
}
