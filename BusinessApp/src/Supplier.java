import java.util.ArrayList;
import java.util.List;

/**
 * Created by SPAS on 04/05/2017.
 */
public class Supplier {

    private String name;
    private int distance;
    private List<Ingredient> products;// may not be needed

    public Supplier(String name, int distance) {
        this.name = name;
        this.distance = distance;
        products = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getDistance() {
        return distance;
    }

    public List<Ingredient> getProducts() {
        return products;
    }

    //suppliers can sell more than one product
    public void addProduct(Ingredient newIngr){
        products.add(newIngr);
        newIngr.setSupplier(this);
    }
}
