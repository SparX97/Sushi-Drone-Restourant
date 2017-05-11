/**
 * Created by SPAS on 09/05/2017.
 */
public class Main {

    public static void main(String args[]){

        //TODO so this test works!
        /*Ingredient apple = new Ingredient("apple");
        Ingredient tomato = new Ingredient("tomato");
        Ingredient pear = new Ingredient("pear");
        Ingredient coco = new Ingredient("coco");
        Ingredient bean = new Ingredient("bean");


        Map<Ingredient, Integer> recipe1 = new HashMap<>();
        recipe1.put(apple,2);
        recipe1.put(tomato,3);
        SushiDish dish1 = new SushiDish("dish1", recipe1);

        Map<Ingredient, Integer> recipe2 = new HashMap<>();
        recipe2.put(pear,1);
        recipe2.put(coco,2);
        recipe2.put(bean,2);
        recipe2.put(apple,3);
        SushiDish dish2 = new SushiDish("dish2", recipe2);

        Map<Ingredient, Integer> recipe3 = new HashMap<>();
        recipe3.put(apple,1);
        recipe3.put(coco,2);
        recipe3.put(tomato,4);
        SushiDish dish3 = new SushiDish("dish3", recipe3);

        StockManager theStockManager = new StockManager();
        theStockManager.addIngredient(apple, 5);
        theStockManager.addIngredient(tomato, 6);
        theStockManager.addIngredient(pear, 7);
        theStockManager.addIngredient(coco, 8);
        theStockManager.addIngredient(bean, 9);
        theStockManager.addDish(dish1,1);
        theStockManager.addDish(dish2,2);
        theStockManager.addDish(dish3,3);

        theStockManager.supply(apple,120);
        theStockManager.supply(tomato,120);
        theStockManager.supply(pear,120);
        theStockManager.supply(coco,120);
        theStockManager.supply(bean,120);

        KitchenStaff staff1 = new KitchenStaff("bitch1", theStockManager);
        KitchenStaff staff2 = new KitchenStaff("gosho2", theStockManager);
        KitchenStaff staff3 = new KitchenStaff("kiki3", theStockManager);

        Thread t1 = new Thread(staff1);
        Thread t2 = new Thread(staff2);
        Thread t3 = new Thread(staff3);

        t1.start();
        t2.start();
        t3.start();*/


        ServerComms myServer = new ServerComms();
        myServer.startServer();
    }
}
