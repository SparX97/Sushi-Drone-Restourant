/**
 * Created by SPAS on 04/05/2017.
 */
public class Ingredient {

    private String name;
    private String unitOfMeasure;
    private Supplier ingSupplier;
    private volatile boolean isOccupied = false;

    public Ingredient(String name, Supplier seller){
        this.name = name;
        this.unitOfMeasure = "kg";
        this.ingSupplier = seller;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public Supplier getSupplier() {
        return ingSupplier;
    }

    public void setSupplier(Supplier seller){
        ingSupplier = seller;
//        seller.addProduct(this);
    }
}
