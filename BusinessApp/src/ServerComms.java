import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by SPAS on 10/05/2017.
 */
public class ServerComms {

    /*private ObjectOutputStream output;
    private ObjectInputStream input;*/
    private ServerSocket theServer;
//    private Socket theConnection;

    private boolean isOnline;


    private List<String> postcodes;
    private List<User> users;
    private static StockManager theStockManager;

    public ServerComms(StockManager theStockManager) {

        isOnline = true;
        users = new ArrayList<>();
        postcodes = new ArrayList<>();
        postcodes.add("SO13 5XO");
        postcodes.add("SO07 007");
        postcodes.add("SO18 2NU");
        postcodes.add("SO97 5SZ");
        postcodes.add("SO66 7KO");
        users = new ArrayList<>();
        users.add(new User("spas", "123", "SO18 2NU"));
        users.add(new User("dido", "odi", "SO66 7KO"));
        this.theStockManager = theStockManager;
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


    //create a new miniServer for each client
    public void startServer() {
        try {
            //port and number of people that can connect
            try {
                theServer = new ServerSocket(2512, 30);
            } catch (IOException e){
                System.err.println("Could no listen in on port: 2512");
            }

            while (isOnline) {
                try {
                    System.out.println("Waiting for a customer to connect \n");
                    Socket theConnection = theServer.accept();
                    System.out.println("Congrats you connected to " + theConnection.getInetAddress().getHostName());
//                    connecting(theConnection);
                    MiniServer mini = new MiniServer(theConnection);

                    Thread newServer = new Thread(mini);
                    newServer.start();
//                     connectToClient();
                } catch (EOFException e) {
                    System.out.println("Server disconnected!!");
                } /*finally {
                    //TODO chekc if it works
                    theServer.close();
                }*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO I don't thing that two customers can connect to the same socket TEST IT!!!
    //waits and connects to customers
/*    private Socket connecting() throws IOException{

        //socket = server.accept ... waits for someone to connect and accepts them
        Socket theConnection = theServer.accept();


        return theConnection;
    }*/

    public class MiniServer implements Runnable {


        private Socket clientSocket;
        private ObjectOutputStream output;
        private ObjectInputStream input;

        public MiniServer(Socket clientSocket) {
            //super... give thread name;
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                connectToClient();
            } catch (IOException e) {
                System.err.println("Client just disconnected right now!");
            }finally {
                closeStreams();
            }
        }


        //handles new connections
        public void connectToClient() throws IOException {
//        connecting();
            System.out.println("miniServer started!");
            makeStreams();
            exchangeInfo();
        }

        //create input and output
        private void makeStreams() throws IOException {
            output = new ObjectOutputStream(clientSocket.getOutputStream());
            //send leftover bytes to the other guy's input
            output.flush();
            input = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println("I/O created!! \n");
        }

        //create a Thread to wait for input
        private void exchangeInfo() throws IOException {
            Object theMessage = "FROM SERVER - You are now connected to the sushi SERVER !!";
            sendMessage(theMessage);
            //Customer Able to select(true)?
            //TODO delete this and yea thread
            do {
                try {
                    theMessage = input.readObject();
                    receiveMessage(theMessage);

                    System.out.println(theMessage);
                    //TODO send to receiveMessage
                } catch (ClassNotFoundException e) {
                    System.err.println(" This is very bad... what class did you send");
                } catch (SocketException e) {
                    System.out.println("Customer disconnected... looking for a new one\n");
                    connectToClient();
                }
            } while (clientSocket.isConnected());
        }

        //Closing the connections after we finish
        private void closeStreams() {
            System.out.println("closing all the streams!!");
            //customer able to select (false)?

            try {
                output.close();
                input.close();
                clientSocket.close();
            } catch (IOException io) {
                io.printStackTrace();
            }
        }

        //sending a message to the client
        private synchronized void sendMessage(Object message) {
            try {
                output.writeObject(message);
                output.flush();
                System.out.println("theServer sent: " + message + "!!");
            } catch (IOException io) {
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

        //interprets the messages from clients
        private synchronized void receiveMessage(Object message) {
            switch (message.getClass().getSimpleName()) {

                case "String":
                    String[] parts = message.toString().split(":");
                    switch (parts[0]) {

                        case "Registering":
                            sendPostCodes();
                            break;

                        case "user":
                            users.add(new User(parts[1], parts[2], parts[3]));
                            /*System.out.println(users.size());
                            for(User i : users){
                                System.out.println(i.getUsername());
                                System.out.println(i.getPassword());
                                System.out.println(i.getPostcode());
                            }*/
                            break;

                        case "check":
                            boolean tof = false;
                            for(User i : users){
                                if(i.getUsername().equals(parts[1]) && i.getPassword().equals(parts[2])){
                                    tof = true;
                                    break;
                                }
                            }
                            if(tof){
                                sendMessage("acc:" + "t");
                            } else {
                                sendMessage("acc:" + "f");
                            }
                            break;

                        case "Dishes":
                            Map<SushiDish, Inventory> temp = theStockManager.getReadyDishes();
                            for (SushiDish i : temp.keySet()) {
                                sendMessage("dish:" + i.getName() + ":" + i.getDescription() + ":" + i.getPrice() + ":"  + temp.get(i).getQuantity());
                            }
                            break;

                    }
                    break;

                case "User": //TODO delete later if not fixed
                    System.out.println("BOYAH!!11");
                    users.add((User) message);
                    break;

                default:
                    System.out.println(message);
                    System.out.println(message.getClass().getSimpleName());
                    break;
            }
        }

        private void sendPostCodes() {
            for(String i : postcodes){
                sendMessage("post:" + i);
            }
//            sendMessage("post:save");
        }
    }
}

