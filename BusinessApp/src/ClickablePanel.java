/*import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

*//**
 * Created by SPAS on 16/05/2017.
 *//*
public class ClickablePanel extends JPanel implements MouseListener {

    private Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
    private Border orangeBorder = BorderFactory.createLineBorder(Color.ORANGE, 5);
    
    private BusinessFrame parentFrame;
    private int index;
    private boolean isPressed;
    
    public ClickablePanel(BusinessFrame parentFrame, int number){
        this.parentFrame = parentFrame;
        this.index = number;
        isPressed = false;
    }
    
    public void setToInactive(){
        isPressed = false;
        setBorder(blackBorder);
    }
    
    public boolean getState(){
        return isPressed;
    }

    public void setToActive(){
        isPressed = true;
        setBorder(orangeBorder);
    }

    public int getIndex(){
        return index;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    
    //deselect all other panels and selects this one
    @Override
    public void mousePressed(MouseEvent e) {
        //TODO I choose you
        System.out.println(index + " is selected");
        parentFrame.changeOthers(index);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}*/
