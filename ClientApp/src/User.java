import java.io.Serializable;

/**
 * Created by SPAS on 14/05/2017.
 */
public class User implements Serializable{
    private String username;
    private String password;
    private String postcode;

    public User(String username, String password, String postcode) {
        this.username = username;
        this.password = password;
        this.postcode = postcode;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
