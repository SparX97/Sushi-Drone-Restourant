import javax.swing.*;

/**
 * Created by SPAS on 10/05/2017.
 */
public class Main {

public static class GUIcreator implements Runnable {
    private ClientComms comms;

    public GUIcreator(ClientComms comms){
        this.comms = comms;
    }
    
    @Override
    public void run() {
        ClientFrame client = new ClientFrame("Client window", comms);
        client.setUp();
    }
}

    public static void main(String[] args) {

        ClientComms comms = new ClientComms();
        SwingUtilities.invokeLater(new GUIcreator(comms));

        comms.startClientComms();
    }
}
