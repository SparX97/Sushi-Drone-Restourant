/**
 * Created by SPAS on 04/05/2017.
 */
public class Ingredient {

    private String name;
    private String unitOfMeasure;
    private Supplier ingSupplier;

    public Ingredient(String name){
        this.name = name;
        this.unitOfMeasure = "kg";
        this.ingSupplier = null;
    }

    public String getName(){
        return name;
    }
}
