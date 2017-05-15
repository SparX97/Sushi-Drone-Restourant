import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SPAS on 10/05/2017.
 */
public class Menu extends JPanel {

//    private JFrame topFrame;
    private ClientComms comms;
    private List<Dish> dishes;
    private Order basket;
    private double total;
    private List<Order> currentOrders;
    private List<Order> pastOrders;

    private JScrollPane scroll;
    private JPanel vertical;

    Map<Dish,JLabel> stockLabels;

    JTextArea orderInfo;
    JLabel totalPrice;

    JTextArea emptySpace;

    //setting up GUI with options for customer
    public Menu(ClientComms comms) {
//        topFrame = clientFrame;
        this.comms = comms;
        setLayout(new BorderLayout());
        dishes = new ArrayList<>();
        basket = new Order();
        currentOrders = new ArrayList<>();
        pastOrders = new ArrayList<>();
        total = 0;
        stockLabels = new HashMap<>();

        setUpMenu();
    }

    //create all the GUIs in the menu
    private void setUpMenu(){

        updateDishes();

        vertical = new JPanel();
        vertical.setLayout(new BoxLayout(vertical,BoxLayout.PAGE_AXIS));
        /*GridLayout gLayout = new GridLayout(0,5);
        gLayout.setHgap(10);
        vertical.setLayout(gLayout);*/
        scroll = new JScrollPane(vertical);
//        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(scroll,BorderLayout.CENTER);

        //holds right half of the screen
        JPanel infoHolder = new JPanel(new GridLayout(1,2));
        add(infoHolder,BorderLayout.LINE_END);
        //holds past/current order statues, orderInfo, place order
        JPanel userOptions = new JPanel();
        userOptions.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        infoHolder.add(userOptions);
        //for displaying the past/current order status
        emptySpace = new JTextArea();
        emptySpace.setBackground(new Color(240,240,240));
        emptySpace.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        infoHolder.add(new JScrollPane(emptySpace));

        Font textFont = new Font("Dialog", Font.PLAIN, 30);

        JButton currentOrdersB = new JButton("Current Orders");
        currentOrdersB.setFont(textFont);
        currentOrdersB.addActionListener(e -> {
            //TODO display current orders on empty space
            displayCurrentOrders();
        });
        c.gridx = 0;
        c.fill = GridBagConstraints.BOTH;
//        c.fill = GridBagConstraints.VERTICAL;
//        c.weightx = 1;
//        c.ipady = 50;
        c.weightx = 400;
//        c.weighty = 30;
        c.gridheight = 1;
//        c.anchor = GridBagConstraints.CENTER;
//        c.weighty = 200;
//        currentOrdersB.setMinimumSize(new Dimension(100,300));
        userOptions.add(currentOrdersB, c);

        JButton pastOrdersB = new JButton("Previous Orders");
        pastOrdersB.setFont(textFont);
        pastOrdersB.addActionListener(e -> {
            //TODO display previous orders on empty space
        });
//        pastOrdersB.setMinimumSize(new Dimension(500,100));
        c.gridy = 1;
//        c.weighty = 30;
        c.gridheight = 1;
        userOptions.add(pastOrdersB, c);

        orderInfo = new JTextArea();
        orderInfo.setFont(new Font("Serif", Font.BOLD, 12));
        orderInfo.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        orderInfo.setBackground(new Color(240,240,240));
        //TODO set orderInfo text
        c.gridy = 2;
        c.weighty = 50;
        c.gridheight = 1;
//        JPanel orderInfoPanel = new JPanel();

        userOptions.add(new JScrollPane(orderInfo), c);

        totalPrice = new JLabel("", SwingConstants.RIGHT);
        updateTotal();
        totalPrice.setFont(textFont);

        c.gridy = 3;
        c.weighty = 0;
        c.gridwidth = 1;
        userOptions.add(totalPrice, c);


        JButton placeOrderB = new JButton("Place Order");
        placeOrderB.setFont(textFont);
        placeOrderB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeOrder();
                //TODO order products from server
                //TODO change the text above
                //TODO inport order into current orders
            }
        });
//        pastOrdersB.setMinimumSize(new Dimension(500,100));
        c.gridy = 4;
        c.weighty = 0;
        c.ipady = 10;
        c.gridheight = 1;
        userOptions.add(placeOrderB, c);


        List<Dish> temp = comms.getDishList();
        for(Dish i : temp){
            addDish(i);
        }

        /*addDish(new Dish("pasta", "myDescription", 4.20, 7));
        addDish(new Dish("titi", "myDescription", 5.20, 5));
        addDish(new Dish("lala", "myDescription", 3.20, 4));
        addDish(new Dish("nono", "myDescription", 8.20, 12));*/
        /*addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));*/
        revalidate();
        repaint();
    }

    //updates the total price of the basket
    private void updateTotal(){
        total = basket.getTotal();
//        if((total * 100) % 10 == 0) {
            totalPrice.setText("Total: £" + String.format("%.2f",total));
//        }else {
//            totalPrice.setText("Total: " + total + "$");
//        }
    }

    //add new dish information to menu
    private void addDish(Dish newDish) {
        JPanel dishPanel = new JPanel(new GridLayout(1,5));
//        Dish newDish = new Dish(name, price, stock);
        dishes.add(newDish);
        JLabel stock = new JLabel("<" + newDish.getStock() + "> In stock");
        stockLabels.put(newDish, stock);
        dishPanel.add(stock);
        JLabel name = new JLabel(newDish.getName());
        dishPanel.add(name);
        JLabel desc = new JLabel(newDish.getDescription());
        dishPanel.add(desc);
        JLabel price;
//        if((newDish.getPrice() * 100) % 10 == 0){
//            price = new JLabel("Price: " + newDish.getPrice() + "0$");
//        } else {
            price = new JLabel("Price: £" + String.format("%.2f", newDish.getPrice()));
//        }
        dishPanel.add(price);
        dishPanel.add(buttonsFor(newDish));
        dishPanel.setMaximumSize(new Dimension(1200,25));
//        dishHolder.setBorder(BorderFactory.createLineBorder(Color.black));
        vertical.add(dishPanel);
        revalidate();
    }


    //create (add and remove) buttons for each new dish
    private JPanel buttonsFor(Dish dish) {
        JPanel Bpanel = new JPanel(new GridLayout(1,2));
        JButton increaseB = new JButton("▲");
        increaseB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBasket(dish, true);
            }
        });
        Bpanel.add(increaseB);

        JButton decreaseB = new JButton("▼");
        decreaseB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBasket(dish, false);
            }
        });
        Bpanel.add(decreaseB);
        return Bpanel;
    }

    //update the stock values of a dish
    private void updateDishStock(Dish theDish){
        JLabel dishStock = stockLabels.get(theDish);
        dishStock.setText("<" + theDish.getStock() + "> In stock");
    }

    //updates the GUI when an item is added/removed from the basket
    private void updateBasket(Dish theDish, boolean addTheDish ){
        basket.addToOrder(theDish, addTheDish);
        updateDishStock(theDish);
//        total = basket.getTotal();
        updateTotal();
        Map<Dish, Integer> orderedDishes = basket.getOrderedDishes();
//        int dishQuantity = basket.getDishAmount(theDish);

        orderInfo.setText("\nSHOPPING BASKET \n\n");
        for(Dish i : orderedDishes.keySet()){
//            if((i.getPrice() * 100 )%10 == 0){
                orderInfo.append("\n" + i.getName() + "  £" + String.format("%.2f", i.getPrice()) + " x" + orderedDishes.get(i) + "\n");
//            } else {
//                orderInfo.append("\n" + i.getName() + "  " + i.getPrice() + "$  x" + orderedDishes.get(i) + "\n");
//                 Stock left: " + i.getStock() + "
//            }
        }
        revalidate();
    }

    //moves the order into current orders
    private void placeOrder() {

        //TODO send info to server
        basket.setStatus("Order placed / Dispatching");
        Order newOrder = new Order();
        newOrder.setStatus(basket.getStatus());
        newOrder.setOrderedDishes(basket.getOrderedDishes());
        newOrder.setTotal(basket.getTotal());
        currentOrders.add(newOrder);
        basket.removeAll();
        orderInfo.setText("\nSHOPPING BASKET\n\n");
        updateTotal();

    }

    private void updateDishes(){
        comms.sendMessage("Dishes");
    }

    //TODO FIX THISSSSS
    private void displayCurrentOrders(){
        emptySpace.setText("");
        Font tempFont = new Font("Serif", Font.BOLD, 20);
//        Font textFont = new Font("Serif", Font.BOLD, 12);
        for(Order i : currentOrders){
            emptySpace.setFont(tempFont);
            emptySpace.append("\nOrder Total: £" + i.getTotal());
            emptySpace.append("\nStatus: " + i.getStatus());
            /*tempFont.deriveFont(12);*/
            Map<Dish, Integer> temp = i.getOrderedDishes();
            for(Dish j : temp.keySet()){
//                Dish temp = (Dish) j;
                emptySpace.append("\n" + j.getName() + "  £" + String.format("%.2f", j.getPrice()) + " x" + temp.get(j) + "\n");
            }
            /*temp.forEach((dish, value) ->{
                emptySpace.append("\n" + dish.getName() + "  £" + String.format("%.2f", dish.getPrice()) + " x" + value + "\n");
            });*/
        }
    }
}
