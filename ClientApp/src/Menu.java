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
        scroll = new JScrollPane(vertical);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(scroll,vertical);

        JPanel test = new JPanel();
        add(test);
        addDish("cry", 3.50, 4);
        addDish("cry2", 3.50, 4);
        addDish("cry3", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        addDish("cry4", 3.50, 4);
        revalidate();
        repaint();
//        vertical.setLayout(new GridLayout(1, 3));

//        add(new JPanel());
    }

    //add new dish information to menu
    private void addDish(String name, double price, int stock) {
        JPanel dishHolder = new JPanel(new GridLayout(1,3));
        Dish newDish = new Dish(name, price, stock);
        dishes.add(newDish);
        JLabel temp = new JLabel("In stock: " + newDish.getStock());
        dishHolder.add(temp);
        JLabel nameAndPrice = new JLabel(newDish.getName() + " Price: " + newDish.getPrice());
        dishHolder.add(nameAndPrice);
        dishHolder.add(buttonsFor(newDish));
        dishHolder.setMaximumSize(new Dimension(800,25));
//        dishHolder.setBorder(BorderFactory.createLineBorder(Color.black));
        vertical.add(dishHolder);
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

    public void addDish(String name, double price) {
        addDish(name, price, 0);
    }

    private void placeOrder() {
        currentOrders.add(basket);
        basket.toggleDispatching();
    }


}
