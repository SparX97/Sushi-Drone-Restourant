/**
 * Created by SPAS on 16/05/2017.
 */
public class Postcode {

    private final String postcode;
    private final int distance;

    public Postcode(String postcode, int distance){
        this.postcode = postcode;
        this.distance = distance;
    }

    public String getPostcode(){
        return postcode;
    }

    public int getDistance(){
        return distance;
    }
}
