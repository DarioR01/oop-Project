import java.util.ArrayList;
import java.io.*;
import javax.swing.JOptionPane;

public class Boost extends Round implements Serializable
{
    boolean malfunction;
    //Constructor for Boost class
    public Boost(String name, String description,boolean malfunction){
        super(name,description);
        this.malfunction=malfunction;
    }   
    
    ////overrides the action performed in this particular round, the action method in the superclass is called only if the round does not have a malfunction
    public void action(boolean action,int round,Entity ahead,Entity behind,ArrayList<Entity> Positions){
        if(this.malfunction){
            super.action(action,2,ahead,behind,Positions);
        }
        else{
            if(behind instanceof Car){
                JOptionPane.showMessageDialog(null, "The Boost had a failure and you could not overtake in this round", "Info Round", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    
    //get the variable text that should be displayed in the buttons;
    public String getOption1(){
        return "Use Boost";
    }
    
    public String getOption2(){
        return "Do Not Use Boost";
    }
}
