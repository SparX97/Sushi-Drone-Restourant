import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by SPAS on 11/05/2017.
 */
public class ClientComms {

    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket connectionToServer;


    //handles communicating with the server
    public void startClient(){
        try{
            connectThisClient();
            makeStreams();
            exchangeInfo();
        }catch (EOFException eof){
            System.err.println("Client terminated the connection");
        }catch (IOException io) {
            io.printStackTrace();
        }finally{
            closeStreams();
        }
    }

    //connect to the server
    private void connectThisClient() throws IOException{
        System.out.println("Waiting for server to connect!!\n");
        //TODO check IP
        connectionToServer = new Socket(InetAddress.getByName("127.0.0.1"), 2512);
        System.out.println("Congrats, you are connected to the server!! " + connectionToServer.getInetAddress().getHostName());
    }

    //crete I/O
    private void makeStreams() throws IOException{
        output = new ObjectOutputStream(connectionToServer.getOutputStream());
        output.flush();
        input = new ObjectInputStream(connectionToServer.getInputStream());
        System.out.println("Client I/O created!!\n");
    }

    //cleanup after the connection
    private void closeStreams(){
        System.out.println("Closing the Streams!!");
        try {
            input.close();
            output.close();
            connectionToServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exchangeInfo() throws IOException{
        String message = "FROM CLIENT - you are now connected to your CLIENT!!";
        sendMessage(message);

        do{
            try{
                message = (String) input.readObject();
                System.out.println(message);
                //TODO send to receiveMessage
            }catch (ClassNotFoundException e){
                System.err.println("just a class error... it should have been converted!!");
            }catch (SocketException e){
                System.err.println("Server was shut down");
                break;
            }
        }while(connectionToServer.isConnected());
    }

    public void sendMessage(String message){
        try {
            output.writeObject(message);
            output.flush();
            System.out.println("CLIENT sent: " + message + "!!");
        } catch (IOException e) {
            System.err.println("Message could not be sent!! (client -> server)");
        }
    }

    private void receiveMessage(String message){
        System.out.println(message);
    }

}
