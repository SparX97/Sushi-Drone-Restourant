import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by SPAS on 10/05/2017.
 */
public class ClientFrame extends JFrame {

    JPanel mainPane;
    GridBagConstraints gbc;

    public ClientFrame(String title){
        super(title);
    }

    public void setUp(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,400);

        

        mainPane = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        add(mainPane);

        JButton logInB = new JButton("LOG IN");
        logInB.setPreferredSize(new Dimension(200,100));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5,0,10,0);
        logInB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logIn();
            }
        });
        mainPane.add(logInB, gbc);

        JButton regB = new JButton("REGISTER");
        regB.setPreferredSize(new Dimension(200,100));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(5,0,30,0);
        regB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reg();
            }
        });
        mainPane.add(regB, gbc);

        setVisible(true);
    }

    //creating a login panel
    private void logIn(){
        mainPane.removeAll();

        final JLabel text = new JLabel("Please enter your UserInfo:");
        text.setFont(new Font("Comic Sans MS", 1 , 20));
        final JTextField textField = new JTextField(15);
        final JPasswordField passField = new JPasswordField(15);
        final JButton EnterB = new JButton("SIGN IN!");
        EnterB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO check account
                openMenu();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(4,0,6,0);
        mainPane.add(text);

        gbc.gridy = 1;
        gbc.insets = new Insets(6,0,6,0);
        mainPane.add(textField, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0,0,5,0);
        mainPane.add(passField, gbc);

        gbc.gridy = 3;
        mainPane.add(EnterB, gbc);

        mainPane.revalidate();
        mainPane.repaint();
    }

    //creating a panel for registration
    private void reg(){
        String[] postCodes = {"SO18 2NU", "SO12 4SZ", "SO24 5KO"};
        mainPane.removeAll();
        String selected = null;

        final JLabel topText = new JLabel("Register Form");
        topText.setFont(new Font("Comic Sans MS", 1 , 20));
        final JTextField textField = new JTextField(15);
        final JLabel chooseName = new JLabel("Username: ");
        final JPasswordField passField = new JPasswordField(15);
        final JLabel choosePass = new JLabel("Password: ");
        final JButton EnterB = new JButton("SIGN UP!");
        EnterB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO create account
                openMenu();
            }
        });
        JLabel choosePost = new JLabel("Post Code: ");
        JComboBox<String> postBox = new JComboBox<>(postCodes);
        selected = (String) postBox.getSelectedItem();


        /*postBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                selected = (String) cb.getSelectedItem();
            }
        });*/

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(4,0,15,0);
        mainPane.add(topText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPane.add(chooseName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPane.add(choosePass, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(6,0,6,0);
        mainPane.add(textField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(0,0,5,0);
        mainPane.add(passField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPane.add(choosePost, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(0,0,10,0);
        mainPane.add(postBox, gbc);


        gbc.gridx = 1;
        gbc.gridy = 4;
        mainPane.add(EnterB, gbc);

        mainPane.revalidate();
        mainPane.repaint();
    }

    private void openMenu() {
//        mainPane.removeAll();
        remove(mainPane);
        setSize(new Dimension(1600, 900));
        add(new Menu());
        setResizable(false);

        revalidate();
        repaint();
    }
}
