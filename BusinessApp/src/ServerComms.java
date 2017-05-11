import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by SPAS on 10/05/2017.
 */
public class ServerComms {

    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket theServer;
    private Socket theConnection;

    public ServerComms() {
        /*try {
            output = new ObjectOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }*/


    }

    public void startServer(){
        try {
            //port and number of people that can connect
            theServer = new ServerSocket(2512, 30);
            while(true){
                try{
                    connecting();
                    makeStreams();
                    exchangeInfo();
                } catch (EOFException e) {
                    System.out.println("Server disconnected!!");
                }finally {
                    closeStreams();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO I don't thing that two customers can connect to the same socket TEST IT!!!
    //waits and connects to customers
    private void connecting() throws IOException{
        System.out.println("Waiting for a customer to connect \n");
        //socket = server.accept ... waits for someone to connect and accepts them
        theConnection = theServer.accept();
        System.out.println("Congrats you connected to " + theConnection.getInetAddress().getHostName());
    }

    //create input and output
    private void makeStreams() throws IOException{
        output = new ObjectOutputStream(theConnection.getOutputStream());
        //send leftover bytes to the other guy's input
        output.flush();
        input = new ObjectInputStream(theConnection.getInputStream());
        System.out.println("I/O created!! \n");
    }

    //communication between server - client
    private void exchangeInfo() throws IOException{
        String theMessage = "FROM SERVER - You are now connected to the sushi SERVER !!";
        sendMessage(theMessage);
        //Customer Able to select(true)?

        do{
            try{
                theMessage = (String) input.readObject();
                System.out.println(theMessage);
                //TODO send to receiveMessage
            }catch (ClassNotFoundException e){
                System.err.println(" This is very bad... what class did you send");
            }
        }while(true);
    }

    //Closing the connections after we finish
    private void closeStreams(){
        System.out.println("closing all the streams!!");
        //customer able to select (false)?

        try {
                output.close();
                input.close();
                theConnection.close();
        }catch (IOException io){
            io.printStackTrace();
        }
    }

    //sending a message to the client
    private void sendMessage(Object message) {
        try{
            output.writeObject(message);
            output.flush();
            System.out.println("theServer sent: " + message+ "!!");
        }catch (IOException io){
            System.err.println("Message could not be sent!! (server -> client)");
        }
    }

    //TODo might need this later
    /*private void updateServerInfo(String text){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //do things with text
            }
        });
    }*/

    private void receiveMessage() {

    }
}

