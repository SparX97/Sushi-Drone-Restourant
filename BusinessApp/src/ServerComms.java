import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

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

    /*//Thread to wait for input from customer
    private class InputReader implements Runnable{

        private ObjectInputStream input;
        private ServerComms recipiant;

        public InputReader(ServerComms theComms, ObjectInputStream input){
            this.recipiant = theComms;
            this.input = input;
        }

        @Override
        public void run(){
            String message;
            while (true){
                try {
                    message = (String) input.readObject();
                    recipiant.receiveMessage(message);
                }catch (IOException e){
                    e.printStackTrace();
                    System.err.println("input reader IO e!!!");
                }catch (ClassNotFoundException e){
                    e.printStackTrace();
                    System.err.println("input reader ClassNot... e!!!");
                }

            }
        }
    }*/


    public void startServer(){
        try {
            //port and number of people that can connect
            theServer = new ServerSocket(2512, 30);
            while(true){
                try{
                     connectToClient();
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

    //handles new connections
    public void connectToClient()throws IOException{
        connecting();
        makeStreams();
        exchangeInfo();
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

    //create a Thread to wait for input
    private void exchangeInfo() throws IOException{
        String theMessage = "FROM SERVER - You are now connected to the sushi SERVER !!";
        sendMessage(theMessage);
        //Customer Able to select(true)?
        //TODO delete this and yea thread
        do{
            try{
                theMessage = (String) input.readObject();
//                receiveMessage(theMessage);
                System.out.println(theMessage);
                //TODO send to receiveMessage
            }catch (ClassNotFoundException e){
                System.err.println(" This is very bad... what class did you send");
            }catch (SocketException e){
                System.out.println("Customer disconnected... looking for a new one\n");
                connectToClient();
            }
        }while(theConnection.isConnected());
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

    private synchronized void receiveMessage(String message) {
        System.out.println(message);
    }
}

