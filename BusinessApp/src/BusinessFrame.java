import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by SPAS on 16/05/2017.
 */
public class BusinessFrame extends JFrame {
    //Todo view current stock levels (of ingredients and dishes),
    /*change restocking levels,
    add or edit ingredients, suppliers and dishes,
    view the status of customer orders,
    remove specific orders (and cancel them if necessary),
    remove all completed orders,
    view the status of kitchen staff and drones, and
    add or remove kitchen staff and drones.*/
    
    /*private class SelectedElements<T> {

        T type;
        Inventory inventory;
        JLabel location;

        public T getType() {
            return type;
        }

        public void setType(T type) {
            this.type = type;
        }

        public Inventory getInven() {
            return inventory;
        }

        public void setInventory(Inventory inventory) {
            this.inventory = inventory;
        }

        public JLabel getLocation() {
            return location;
        }

        public void setLocation(JLabel location) {
            this.location = location;
        }

    }*/

    /*private class HolderButtonActionListener extends ActionListener{

        boolean isPressed;

        public HolderButtonActionListener(boolean startingState){
            this.isPressed = startingState;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            isPressed = !isPressed;
            if(isPressed){
                selectedIngredients.put(i, map.get(i));
                holder.setBackground(Color.orange);
            } else {
                holder.setBackground(Color.white);
                selectedIngredients.remove(i);
            }
        }
    }*/

    private static StockManager theStockManager;



    private List<Supplier> suppliers;

//    private JPanel pane;
    private JTabbedPane optionTabs;

    private JPanel ingredientWindow;
    private JPanel ingredientData;
    private JPanel ingredientOptions;

    private Map<Ingredient, Inventory> selectedIngredients;
//    private List<Selec> selectedIngredients;
    private List<JLabel> ingNameLabels;
    private List<JLabel> ingStockLabels;
    private List<JLabel> ingSupplierLabels;
    private List<JLabel> ingRestockLabels;
//    private int numOfIngredients;

    private JPanel dishWindow;
    private JPanel dishData;
    private JPanel disOptions;

    private JPanel supplierWindow;
    private JPanel supplierData;
    private JPanel supplierOptions;

    private JPanel orderWindow;
    private JPanel orderData;
    private JPanel orderOptions;

    private JPanel staffWindow;
    private JPanel staffData;
    private JPanel staffOptions;


    public BusinessFrame(String title){
        super(title);
        selectedIngredients = new HashMap<>();
//        ingDatabase = new ArrayList<>();
//        numOfIngredients = 0;
        ingNameLabels = new ArrayList<>();
        ingStockLabels = new ArrayList<>();
        ingSupplierLabels = new ArrayList<>();
        ingRestockLabels = new ArrayList<>();
    }

    public void setUp(StockManager theStockManager){
        this.theStockManager = theStockManager;
        //TODO get suppliers from DataPersistence
        suppliers = theStockManager.getSuppliers();

        setSize(800,500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        pane = new JPanel();
//        add(pane);

        optionTabs = new JTabbedPane();

        ingredientWindow = new JPanel();
        ingredientData = new JPanel();
        ingredientOptions = new JPanel();

//        ingredientWindow.setLayout(new GridLayout(2,1));
        ingredientWindow.setLayout(new BorderLayout(0,10));
//        ingredientData.setLayout(new BoxLayout(ingredientData, BoxLayout.PAGE_AXIS));
        ingredientData.setLayout(new GridLayout(0,1));
//        ingredientOptions.setSize(200,100);

        ingredientWindow.add(new JScrollPane(ingredientData), BorderLayout.CENTER);
        ingredientWindow.add(ingredientOptions, BorderLayout.SOUTH);
        populateIngredients(theStockManager.getIngredients());
        setUpOptions(ingredientData, selectedIngredients);
        optionTabs.add("Ingredients",ingredientWindow);


        dishWindow = new JPanel();
        optionTabs.add("Sushi Dishes",dishWindow);

        supplierWindow = new JPanel();
        optionTabs.add("Suppliers",supplierWindow);

        orderWindow = new JPanel();
        optionTabs.add("Orders",orderWindow);

        staffWindow = new JPanel();
        optionTabs.add("Staff / Drones",staffWindow);

        optionTabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        add(optionTabs);

        setVisible(true);
    }

/*    //creates identical panels for ingredients and dishes
    private <T> void populateEditPanel(JPanel panel, Map<T, Inventory> map){

        //Todo view current stock levels (of ingredients and dishes),
        *//*change restocking levels,
          add or edit ingredients, suppliers and dishes,*//*

//        Map<Ingredient, Inventory> temp = theStockManager.getIngredients();
        for(T i : map.keySet()){
            JPanel holder = new JPanel(new GridLayout(1,5));
            JLabel name = new JLabel();
            name.setText(i.getName());
            holder.add
        }
        JPanel singleIngredient = new JPanel(new )
    }*/

    //creates identical Button-panels for ingredients and dishes
    private void populateIngredients(Map<Ingredient, Inventory> map){
//TODO        numOfIngredients = numOfIngredients + map.keySet().size();
        JPanel titles = new JPanel(new GridLayout(1,4));

        JLabel name = new JLabel();
        JLabel supplier = new JLabel();
        JLabel stock = new JLabel();
        JLabel restock = new JLabel();

        name.setText(" Ingredient name:");
        supplier.setText("Supplier name:");
        stock.setText("In stock:");
        restock.setText("Restock when bellow:");

        titles.setBorder(BorderFactory.createLineBorder(Color.green));
        titles.add(name);
        titles.add(stock);
        titles.add(supplier);
        titles.add(restock);
        ingredientData.add(titles);

        //Todo view current stock levels (of ingredients and dishes),
        /*change restocking levels,
          add or edit ingredients, suppliers and dishes,*/

//        Map<Ingredient, Inventory> temp = theStockManager.getIngredients();
        for(Ingredient i : map.keySet()){
//            numOfIngredients++;
//            ClickablePanel holder = new ClickablePanel(this, numOfIngredients);
            JButton holder = new JButton();
            holder.setMaximumSize(new Dimension(this.getWidth()/2,25));
//            System.out.println(numOfIngredients);
            holder.setLayout(new GridLayout(1,4));

            /*for (Ingredient j : selectedIngredients.keySet()){
                if(j == i){

                }
            }*/

            holder.addActionListener(new ActionListener() {
//                boolean isPressed = false;
                @Override
                public void actionPerformed(ActionEvent e) {
//                    isPressed = !isPressed;
                    if(holder.getBackground() == Color.WHITE){
                        selectedIngredients.put(i, map.get(i));
                        holder.setBackground(Color.orange);
                    } else {
                        holder.setBackground(Color.white);
                        selectedIngredients.remove(i);
                    }
                }
            });
            holder.setBackground(Color.white);
//            holder = new JPanel(new GridLayout(1,4));

            name = new JLabel();
            supplier = new JLabel();
            stock = new JLabel();
            restock = new JLabel();

            name.setText(i.getName());
            Supplier temp = i.getSupplier();
            supplier.setText(temp.getName());
            stock.setText(String.valueOf(map.get(i).getQuantity()));
            restock.setText(String.valueOf(map.get(i).getRestocklvl()));

            ingNameLabels.add(name);
            ingSupplierLabels.add(supplier);
            ingStockLabels.add(stock);
            ingRestockLabels.add(restock);

            holder.add(name);
            holder.add(stock);
            holder.add(supplier);
            holder.add(restock);



//            ingredientPanels.add(holder);
            for(Ingredient j : selectedIngredients.keySet()){
                if(j == i){
                    holder.setBackground(Color.ORANGE);
                    break;
                }
            }


//            holder.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
//            ingredientPanel.put(i,holder);
            ingredientData.add(holder);
        }
    }

    /*public void changeOthers(int selected){
        for (ClickablePanel i : ingredientPanels){
            if(i.getIndex() == selected){
                optionTabs.setBackground(Color.ORANGE);
                System.out.println("background changed");
                i.setToActive();
            } else {
                i.setBackground(Color.gray);
                System.out.println("background changed");
                i.setToInactive();
            }
        }
        repaint();
    }*/

    //create edit, add, remove buttons for the data
    private void setUpOptions(JPanel dataPanel, Map theSelected){
        //TODO change restocking levels,
        //TODO add or edit ingredients, suppliers and dishes,
        JButton chName = new JButton("Change Name of selected to:");
        JButton chStock = new JButton("Change Stock of selected to:");
        JButton chSupplier = new JButton("Change Supplier of selected to:");
        JButton chRestocklvl = new JButton("Change Restock Level of selected to:");
        JButton remove = new JButton("Remove Selected");
        JButton addEntry = new JButton("Add new from Text fields");

        JTextField nameText = new JTextField("", 15);
        JTextField stockText = new JTextField("", 4);
        List<String> nameList = new ArrayList<>();
        for(Supplier i : suppliers){
            nameList.add(i.getName());
        }

        JComboBox supplierBox = new JComboBox(nameList.toArray());
        JTextField rlvlText = new JTextField("", 4);

//        ingredientOptions.setLayout(new GridBagLayout());
        ingredientOptions.setLayout(new BorderLayout());

        JPanel editFields = new JPanel(new GridLayout(4,2));
        JPanel addRemoveButtons = new JPanel(new FlowLayout());

        ingredientOptions.add(editFields, BorderLayout.CENTER);
        ingredientOptions.add(addRemoveButtons, BorderLayout.SOUTH);
//        GridBagConstraints c = new GridBagConstraints();

//        JLabel nameText = new JLabel("Name:");

        editFields.add(chName);
        editFields.add(nameText);

        editFields.add(chStock);
        editFields.add(stockText);

        editFields.add(chSupplier);
        editFields.add(supplierBox);

        editFields.add(chRestocklvl);
        editFields.add(rlvlText);

        addRemoveButtons.add(remove);
        addRemoveButtons.add(addEntry);

        addEntry.addActionListener(e -> {
            //TODO test if works
            Supplier someSupplier = null;
            for(Supplier s : suppliers){
                if(s.getName().equals(supplierBox.getSelectedItem().toString()))someSupplier = s;
            }
            Ingredient ingEntry = new Ingredient(nameText.getText(), someSupplier);
            theStockManager.addIngredient(ingEntry, Integer.parseInt(rlvlText.getText()));
            updateIngDatabase();
        });

        remove.addActionListener(e -> {
            //TODO test if works
            for (Object i : theSelected.keySet()){
                Ingredient selectedIngredient = (Ingredient) i;
                theStockManager.removeIngrdient(selectedIngredient);
            }
            updateIngDatabase();
        });

        chName.addActionListener(e -> {
            for(Object i : theSelected.keySet()){
                if(!nameText.getText().equals("")){
                    Ingredient temp = (Ingredient) i;
                    temp.setName(nameText.getText());
                    System.out.println("name changed i hope");
                    updateIngDatabase();
                }
            }
            updateIngDatabase();
        });

        chStock.addActionListener(e -> {
            for(Object i : theSelected.keySet()){
                if(!stockText.getText().equals("")){
                    Inventory temp = (Inventory) theSelected.get(i);
                    temp.setStock(Integer.parseInt(stockText.getText()));
                    System.out.println("stock changed i hope");
                    updateIngDatabase();
                }
            }
        });

        chSupplier.addActionListener(e -> {
            for(Object i : theSelected.keySet()){
                Ingredient temp = (Ingredient) i;
                for(Supplier j : suppliers){
                    if(j.getName().equals(supplierBox.getSelectedItem().toString())){
                        j.addProduct(temp);
                        System.out.println("supplier changed i hope");
                        updateIngDatabase();
                    }
                }
            }
        });

        chRestocklvl.addActionListener(e -> {
//            if (theSelected.size() == 1){
//                theSelected.get(0) = (Integer)JOptionPane.showInputDialog("select new restock level");
            for(Object i : theSelected.keySet()) {
//                Inventory temp = (Inventory) theSelected.get(theSelected.keySet().toArray()[0]);
                Inventory temp = (Inventory) theSelected.get(i);
                if (!Objects.equals(rlvlText.getText(), "")) {
                    temp.setRestock(Integer.parseInt(rlvlText.getText()));
                    System.out.println(temp.getRestocklvl());
                    updateIngDatabase();
                }
            }
//            }
        });
    }

    //reloads the database with the new data
    public void updateIngDatabase(){
        /*for(JLabel i : ingDatabase) {
            i.revalidate();
            i.repaint();*/
            ingredientData.removeAll();
//        ingredientData.revalidate();
//        ingredientData.repaint();
        populateIngredients(theStockManager.getIngredients());
        ingredientData.revalidate();
        ingredientData.repaint();
        }
    }


