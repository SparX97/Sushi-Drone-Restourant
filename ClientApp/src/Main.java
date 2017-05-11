import javax.swing.*;

/**
 * Created by SPAS on 10/05/2017.
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        ClientFrame client = new ClientFrame("Client window");
                        client.setUp();
                    }
                }
        );

        ClientComms client = new ClientComms();
        client.startClient();
        /*System.out.println("anything?");
        client.sendMessage("HOW YOU DOIN MY MAN!!");*/
    }
}
