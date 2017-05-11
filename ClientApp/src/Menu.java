import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SPAS on 10/05/2017.
 */
public class Menu extends JPanel {

    private JFrame topFrame;
    private List<Dish> dishes;
    private Order basket;
    private double total;
    private List<Order> currentOrders;
    private List<Order> pastOrders;

    private JScrollPane scroll;
    private JPanel vertical;

    //panel with options for customer
    public Menu() {
        topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        setLayout(new GridLayout(1, 2));
        dishes = new ArrayList<>();
        basket = new Order();
        currentOrders = new ArrayList<>();
        pastOrders = new ArrayList<>();

        vertical = new JPanel();
        vertical.setLayout(new BoxLayout(vertical,BoxLayout.PAGE_AXIS));
        /*GridLayout gLayout = new GridLayout(0,5);
        gLayout.setHgap(10);
        vertical.setLayout(gLayout);*/
        scroll = new JScrollPane(vertical);
//        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(scroll,vertical);

        JPanel test = new JPanel();
        add(test);
        addDish(new Dish("haha", "myDescription", 4.25, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
        addDish(new Dish("haha", "myDescription", 4.20, 7));
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
//        vertical.setLayout(new GridLayout(1, 3));

//        add(new JPanel());
    }

    //add new dish information to menu
    private void addDish(Dish newDish) {
        JPanel dishPanel = new JPanel(new GridLayout(1,5));
//        Dish newDish = new Dish(name, price, stock);
        dishes.add(newDish);
        JLabel stock = new JLabel("<" + newDish.getStock() + "> In stock");
        dishPanel.add(stock);
        JLabel name = new JLabel(newDish.getName());
        dishPanel.add(name);
        JLabel desc = new JLabel(newDish.getDescription());
        dishPanel.add(desc);
        JLabel price;
        if((newDish.getPrice() * 100) % 10 == 0){
            price = new JLabel("Price: " + newDish.getPrice() + "0$");
        } else {
            price = new JLabel("Price: " + newDish.getPrice() + "$");
        }
        dishPanel.add(price);
        dishPanel.add(buttonsFor(newDish));
        dishPanel.setMaximumSize(new Dimension(800,25));
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
                basket.addToOrder(dish.getName(), dish.getPrice(), true);
                total = basket.getTotal();
            }
        });
        Bpanel.add(increaseB);

        JButton decreaseB = new JButton("▼");
        decreaseB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                basket.addToOrder(dish.getName(), dish.getPrice(), false);
                total = basket.getTotal();
            }
        });
        Bpanel.add(decreaseB);
        return Bpanel;
    }

//    public void addDish(String name, double price) {
//        addDish(name, price, 0);
//    }

    private void placeOrder() {
        currentOrders.add(basket);
        basket.toggleDispatching();
    }


}
