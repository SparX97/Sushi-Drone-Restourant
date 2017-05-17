import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SPAS on 09/05/2017.
 */

class BFrameThread implements Runnable {

    private StockManager managerRef;

    public BFrameThread(StockManager ref){
        this.managerRef = ref;
    }

    @Override
    public void run() {
        BusinessFrame bFrame = new BusinessFrame("Business App Window");
        bFrame.setUp(managerRef);
    }
}
public class Main {

    public static void main(String args[]){

//        List<Supplier> suppliers = new ArrayList<>();
        StockManager theStockManager = new StockManager();

        Supplier dodo = new Supplier("dodo", 1000);
        theStockManager.addSupplier(dodo);
//        dodo.addProduct(tomato);
//        dodo.addProduct(bean);

        Supplier mikel = new Supplier("mikel", 1200);
        theStockManager.addSupplier(mikel);
//        mikel.addProduct(pear);
//        mikel.addProduct(coco);

        //TODO so this test works!
        Ingredient apple = new Ingredient("apple", dodo);
        Ingredient tomato = new Ingredient("tomato", dodo);
        Ingredient pear = new Ingredient("pear", mikel);
        Ingredient coco = new Ingredient("coco", dodo);
        Ingredient bean = new Ingredient("bean", mikel);
        Ingredient banana = new Ingredient("banana", dodo);
        Ingredient lemon = new Ingredient("lemon", mikel);
        Ingredient soya = new Ingredient("soya", mikel);
        Ingredient salt = new Ingredient("salt", mikel);
        Ingredient fish = new Ingredient("fish", mikel);
        Ingredient sushiPaper = new Ingredient("sushi paper", mikel);
        Ingredient mango = new Ingredient("mango", mikel);
        Ingredient carrot = new Ingredient("carrot", mikel);
        Ingredient beef = new Ingredient("beef", mikel);
        Ingredient chicken = new Ingredient("chicken", mikel);
        Ingredient oil = new Ingredient("oil", mikel);



        Map<Ingredient, Integer> recipe1 = new HashMap<>();
        recipe1.put(apple,2);
        recipe1.put(tomato,3);
        SushiDish dish1 = new SushiDish("dish1","shit on a stick",8, recipe1);

        Map<Ingredient, Integer> recipe2 = new HashMap<>();
        recipe2.put(pear,1);
        recipe2.put(coco,2);
        recipe2.put(bean,2);
        recipe2.put(apple,3);
        SushiDish dish2 = new SushiDish("dish2","kebabche", 5, recipe2);

        Map<Ingredient, Integer> recipe3 = new HashMap<>();
        recipe3.put(apple,1);
        recipe3.put(coco,2);
        recipe3.put(tomato,4);
        SushiDish dish3 = new SushiDish("dish3","kur",9, recipe3);


        theStockManager.addIngredient(apple, 5);
        theStockManager.addIngredient(tomato, 6);
        theStockManager.addIngredient(pear, 7);
        theStockManager.addIngredient(coco, 8);
        theStockManager.addIngredient(bean, 9);
        theStockManager.addIngredient(banana, 20);
        theStockManager.addIngredient(lemon, 3);
        theStockManager.addIngredient(soya, 15);
        theStockManager.addIngredient(salt, 25);
        theStockManager.addIngredient(fish, 10);
        theStockManager.addIngredient(sushiPaper, 12);
        theStockManager.addIngredient(mango, 4);
        theStockManager.addIngredient(carrot, 13);
        theStockManager.addIngredient(beef, 6);
        theStockManager.addIngredient(chicken, 15);
        theStockManager.addIngredient(oil, 17);

        theStockManager.addDish(dish1,5,1);
        theStockManager.addDish(dish2,7,2);
        theStockManager.addDish(dish3,3,3);

        theStockManager.supply(apple,120);
        theStockManager.supply(tomato,120);
        theStockManager.supply(pear,120);
        theStockManager.supply(coco,120);
        theStockManager.supply(bean,120);

        /*KitchenStaff staff1 = new KitchenStaff("bitch1", theStockManager);
        KitchenStaff staff2 = new KitchenStaff("gosho2", theStockManager);
        KitchenStaff staff3 = new KitchenStaff("kiki3", theStockManager);

        Thread t1 = new Thread(staff1);
        Thread t2 = new Thread(staff2);
        Thread t3 = new Thread(staff3);

        t1.start();
        t2.start();
        t3.start();*/

        SwingUtilities.invokeLater(new BFrameThread(theStockManager));

        ServerComms myServer = new ServerComms(theStockManager);
        myServer.startServer();


    }
}
