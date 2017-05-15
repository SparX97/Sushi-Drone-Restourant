import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SPAS on 11/05/2017.
 */
public class ClientComms {

    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket connectionToServer;
//    private ClientFrame frameRef = null;

    private List<String> postCodes;
    private volatile String permission;
    private List<Dish> dishList;

    public ClientComms() {
        postCodes = new ArrayList<>();
        permission = "wait";
        dishList = new ArrayList<>();
    }

    //handles communicating with the server
    public void startClientComms() {
        try {
            connectThisClient();
            makeStreams();
            exchangeInfo();
        } catch (EOFException eof) {
            eof.printStackTrace();
        } catch (ConnectException connectException) {
            System.err.println("No Server to connect to!!");
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            closeStreams();
        }
    }

    /*public void setFrameRef(ClientFrame reference) {
        this.frameRef = reference;
    }*/

    //connect to the server
    private void connectThisClient() throws IOException {
        System.out.println("Waiting for server to connect!!\n");
        connectionToServer = new Socket(InetAddress.getByName("127.0.0.1"), 2512);
        /*connectionToServer = new Socket();
        connectionToServer.connect(new InetSocketAddress("127.0.0.1", 2512), 5000);*/
        System.out.println("Congrats, you are connected to the server!! " + connectionToServer.getInetAddress().getHostName());
    }

    //crete I/O
    private void makeStreams() throws IOException {
        output = new ObjectOutputStream(connectionToServer.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connectionToServer.getInputStream());
        System.out.println("Client I/O created!!\n");
    }

    //cleanup after the connection
    private void closeStreams() {
        System.out.println("Closing the Streams!!");
        try {
            input.close();
            output.close();
            connectionToServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException nullPointerException) {
            System.err.println("No Streams to close!!");
        }
    }

    private void exchangeInfo() throws IOException {
        Object message = "FROM CLIENT - you are now connected to your CLIENT!!";
        sendMessage(message);

        do {
            try {
                message = input.readObject();
//                System.out.println(message);
                receiveMessage(message);
                //TODO send to receiveMessage
            } catch (ClassNotFoundException e) {
                System.err.println("just a class error... it should have been converted!!");
            } catch (SocketException e) {
                System.err.println("Server was shut down");
                break;
            }
        } while (connectionToServer.isConnected());
    }

    public synchronized void sendMessage(Object message) {
        try {
            output.writeObject(message);
            output.flush();
            System.out.println("CLIENT sent: " + message + "!!");
        } catch (IOException e) {
            System.err.println("Message could not be sent!! (client -> server)");
        }
    }

    private synchronized void receiveMessage(Object message) {
        System.out.println(message);

        switch (message.getClass().getSimpleName()) {

            case "String":
                String[] parts = (message.toString()).split(":");
                switch (parts[0]) {
                    case "post":
                        postCodes.add(parts[1]);
                        break;

                    case "acc":
                        if(parts[1].equals("t")){
                            permission = "yes";
                        } else {
                            permission = "no";
                        }
                        break;

                    case "dish":
                        dishList.add(new Dish(parts[1],parts[2], Double.parseDouble(parts[3]),Integer.parseInt(parts[4])));
                        break;

                }
                break;


       /* switch (message.getClass().toString()){
            case "ArrayList<String>":
                frameRef.setPostcodes((ArrayList) message);
                System.out.println("1111111111111111111111111");
                break;
            case "ArrayList":
                frameRef.setPostcodes((ArrayList) message);
                System.out.println("2222222222222222222222222");
                break;*/
//        }
        }
    }

    public List getPostCodes() {
        return postCodes;
    }

    public String getPermission(){
        return permission;
    }

    public void resetPermission(){
        permission = "wait";
    }

    public synchronized List getDishList(){
        return dishList;
    }
}
